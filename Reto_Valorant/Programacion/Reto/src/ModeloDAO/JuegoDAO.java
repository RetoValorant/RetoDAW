package ModeloDAO;

import Modelo.Equipo;
import Modelo.Juego;

import java.sql.Connection;
import java.util.ArrayList;

public class JuegoDAO {
    private final ArrayList<Juego> listaJuegos = new ArrayList<>();
    protected Connection con;
    public JuegoDAO(Connection c) {
        this.con = c;
    }

    public void crearJuego(Juego j) {
        listaJuegos.add(j);
    }


}
