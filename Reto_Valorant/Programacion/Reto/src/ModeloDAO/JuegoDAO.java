package ModeloDAO;

import Modelo.Equipo;
import Modelo.Juego;

import java.sql.Connection;
import java.util.ArrayList;

public class JuegoDAO {
    protected Connection con;
    public JuegoDAO(Connection c) {
        this.con = c;
    }



}
