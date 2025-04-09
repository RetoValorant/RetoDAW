package ModeloDAO;

import Modelo.Equipo;
import Modelo.Jugador;

import java.awt.desktop.AppReopenedEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EquipoDAO {

    private static ArrayList<Equipo> listaEquipos = new ArrayList<>();
    protected Connection con;
    protected String sql;
    public EquipoDAO(Connection c) {
        this.con = c;
    }

    public void crearEquipo(Equipo e) {
        listaEquipos.add(e);
    }

    public ArrayList<Equipo> obtenerTodosLosEquipos() {
        return listaEquipos;
        //Devuelve un ArrayList nuevo para la seguridad de datos
    }

    public Equipo obtenerEquipoPorCodigo(int codEquipo) {
        return new Equipo();
    }
    public void actualizarEquipo(Equipo nuevoEquipo) {

    }
    //hechos asi para que no afecten a nada en especial, queda hacer las SELECT, UPDATE o DELETE
    public boolean eliminarEquipo(int codEquipo) {
        return false;
    }
    public boolean anadirJugador(Equipo eq, Jugador j) {
        return false;
    }
    public Equipo validarEquipo(String nombreEquipo, String fechaFund) throws Exception {
        sql = "SELECT * FROM equipos WHERE nombre = ? AND fecha_fund = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombreEquipo);
        ps.setString(2, fechaFund);
        ResultSet re = ps.executeQuery();
        if (re.next()) {
            return new Equipo(re.getInt("cod_equipo"),
                    re.getString("nombre"),
                    re.getDate("fecha_fund").toLocalDate(),
                    re.getInt("puntuacion"));
        } else {
            throw new Exception("El equipo no existe");
        }
    }
    public java.sql.Date validarFecha(String fechaFund) {
        return java.sql.Date.valueOf(fechaFund);
    }
}