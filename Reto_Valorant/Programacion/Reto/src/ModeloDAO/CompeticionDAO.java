package ModeloDAO;

import Modelo.Competicion;

import java.sql.Connection;
import java.util.ArrayList;

public class CompeticionDAO {
    protected Connection con;

    public CompeticionDAO(Connection con) {
        this.con = con;
    }

    public boolean anadirCompeticion(Competicion c) {
        return false;
    }
    public ArrayList<Competicion> obtenerTodasCompeticiones() {
        return null;
    }
}
