package ModeloController;

import Modelo.Competicion;
import Modelo.Jornada;
import Modelo.Juego;
import ModeloDAO.CompeticionDAO;
import ModeloDAO.JornadaDAO;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class CompeticionController {
    private final CompeticionDAO competicionDAO;

    public CompeticionController(CompeticionDAO competicionDAO) {
        this.competicionDAO = competicionDAO;
    }

}
