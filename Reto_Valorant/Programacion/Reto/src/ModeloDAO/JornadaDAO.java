package ModeloDAO;


import Modelo.Jornada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JornadaDAO {

    private static final ArrayList<Jornada> listaJornada = new ArrayList<>();
    protected Connection con;
    protected CompeticionDAO cDAO;
    public JornadaDAO(Connection c, CompeticionDAO cDAO) {
        this.con = c;
        this.cDAO = cDAO;
    }

    public void anadirJornada(Jornada j) throws SQLException {
        String sql = "INSERT INTO jornadas(num_jornada,fecha_inicio,cod_comp) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, j.getNumJornada());
        ps.setDate(2, validarFecha(j.getFechaInicio().toString()));
        ps.setInt(3, j.getCompeticion().getCodCompeticion());
    }
    public java.sql.Date validarFecha(String fechaIni) {
        return java.sql.Date.valueOf(fechaIni);
    }
    public ArrayList<Jornada> getJornadas() throws SQLException {
        String sql = "SELECT * FROM jornadas";
        ArrayList<Jornada> jornadas = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Jornada j = new Jornada();
            j.setNumJornada(rs.getInt("num_jornada"));
            j.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            j.setCompeticion(cDAO.conseguirCompeticion(rs.getInt("cod_comp")));
            jornadas.add(j);
        }
        return jornadas;
    }
}
