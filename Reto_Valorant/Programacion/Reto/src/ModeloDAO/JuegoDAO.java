package ModeloDAO;

import Modelo.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JuegoDAO {
    private final ArrayList<Juego> listaJuegos = new ArrayList<>();
    protected Connection con;
    public JuegoDAO(Connection c) {
        this.con = c;
    }

    public Juego conseguirJuego(int codJuego) throws SQLException{
        String sql = "SELECT * FROM juegos WHERE cod_juego = ?";
        Juego j = new Juego();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,codJuego);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            j.setCodJuego(codJuego);
            j.setNombre(rs.getString("nombre"));
            j.setFechaSalida(rs.getDate("fecha_salida").toLocalDate());
            return j;
        } else {
            return null; //lo que interesa por que es boolean, para que se pase a false
        }
    }

}
