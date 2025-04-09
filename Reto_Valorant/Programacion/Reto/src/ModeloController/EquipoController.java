

package ModeloController;

import Modelo.Equipo;
import Modelo.Juego;
import Modelo.Jugador;
import ModeloDAO.EquipoDAO;

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
import javax.swing.JOptionPane;

public class EquipoController {

    private EquipoDAO eDAO;
    private LocalDate FECHAFUNDACION;
    //fecha fundacion hay que meterla en Juego

    public EquipoController(EquipoDAO eDAO) {
        this.eDAO = eDAO;
    }

    public void definirFechaFundacion(Juego j) {
        FECHAFUNDACION = j.getFechaSalida();
    }

    public Equipo validarEquipo(String nombre, String fechaFundacion) throws Exception {
        return eDAO.validarEquipo(nombre,fechaFundacion);
    }
    public boolean crearEquipo(String nombre, String fechaFundacion) throws Exception {
        Equipo equ = new Equipo();
        equ.setNombre(this.validarNombre(nombre));
        equ.setFechaFundacion(this.validarFecha(fechaFundacion));
        //return eDAO.crearEquipo(equ);
        return false;
    }
    private String validarNombre(String nombre) throws Exception {
        Pattern p = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 _-]{3,15}$"); //15 como mucho como en MER/MR
        Matcher matcher = p.matcher(nombre);
        if (!matcher.matches()) {
            throw new Exception("El nombre del equipo no es valido, el patron es de 3 a 15 carac. con espacios y guiones permitidos");
        }
        return nombre;
    }
    public LocalDate validarFecha(String fecha) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaLocalDate = LocalDate.parse(fecha.trim(), formatter);
            if (fechaLocalDate.isAfter(LocalDate.now())) {
                throw new Exception("La fecha de fundacion no puede ser de anterior a la del juego");
            }
        return fechaLocalDate;
    }
    //se parsea a java.sql.date en DAO
}
