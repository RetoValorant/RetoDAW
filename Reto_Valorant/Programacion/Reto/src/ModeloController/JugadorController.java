package ModeloController;

import Modelo.Equipo;
import Modelo.Jugador;
import ModeloDAO.EquipoDAO;
import ModeloDAO.JugadorDAO;
import Nacionalidades.Country;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class JugadorController {

    private JugadorDAO jugadorDAO;
    private EquipoDAO equipoDAO;
    private ArrayList<Equipo> equipos;

    public JugadorController(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
    }

}
