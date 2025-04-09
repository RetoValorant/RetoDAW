package ModeloController;

import Modelo.Juego;
import ModeloDAO.JuegoDAO;
import ModeloDAO.RolDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JuegoController {
    private JuegoDAO juegoDAO;
    private RolController rolController;
    private final LocalDate SALIDAPRIMERJUEGO = LocalDate.of(1972, 11, 29);

    public JuegoController(JuegoDAO juegoDAO) {
        //rolController = new RolController();
        this.juegoDAO = juegoDAO;
    }
}
