package ModeloDAO;


import Modelo.Jornada;

import java.sql.Connection;
import java.util.ArrayList;

public class JornadaDAO {

    private static final ArrayList<Jornada> listaJornada = new ArrayList<>();
    protected Connection con;
    public JornadaDAO(Connection c) {
        this.con = c;
    }

    public void anadirJornada(Jornada j) {
        listaJornada.add(j);
    }

    public ArrayList<Jornada> getJornadas() {
        return listaJornada;
    }
}
