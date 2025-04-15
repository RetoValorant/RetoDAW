package ModeloController;

import Modelo.Equipo;
import Vista.*;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VistaController {
    protected ModeloController modeloController;
    private ConsultarEquipo consultarEquipo;
    private ActualizarEquipo actualizarEquipo;
    private ConsultarJugador consultarJugador;

    public VistaController(ModeloController modeloController) {
        this.modeloController = modeloController;
        setIniciarSesion();
    }

    /**Metodos de construccion de ventanas*/
    public void setIniciarSesion() {
        IniciarSesion iniciarSesion = new IniciarSesion(this);
        iniciarSesion.setVisible(true);
    }
    public void setConsultarEquipo(VistaController vistaController){
        consultarEquipo = new ConsultarEquipo(this);
        consultarEquipo.setVisible(true);
    }
    public void setBorrarEquipo(VistaController vistaController){
        BorrarEquipo borrarEquipo = new BorrarEquipo(this);
        borrarEquipo.setVisible(true);
    }
    public void setConsultarJugador(VistaController vistaController){
        consultarJugador = new ConsultarJugador(this);
        consultarJugador.setVisible(true);
    }
    public void setActualizarJugador(VistaController vistaController){
        ActualizarJugador actualizarJugador = new ActualizarJugador(this);
        actualizarJugador.setVisible(true);
    }
    public void setCrearJugador(VistaController vistaController) throws SQLException {
        CrearJugador crearJugador = new CrearJugador(vistaController);
        crearJugador.setVisible(true);
    }
    public void setCrearEquipo(VistaController vistaController){
        CrearEquipo crearEquipo = new CrearEquipo(vistaController);
        crearEquipo.setVisible(true);
    }
    public void setActualizarEquipo(VistaController vistaController){
        actualizarEquipo = new ActualizarEquipo(this);
        actualizarEquipo.setVisible(true);
    }

    public ConsultarEquipo getIniciarSesion() {
        return consultarEquipo;
    }
    public Equipo getEquipo() {
        return modeloController.getEquipo();
    }

    /**Metodos de validacion*/
    public List<Equipo> getEquipos() throws SQLException {
        return modeloController.getEquipos();
    }
    public boolean validarUsuario(String nombreUsuario) throws SQLException {
        return modeloController.validarUsuario(nombreUsuario);
    }
    public boolean validarPassWord(String passWord) throws SQLException {
        return modeloController.validarPassWord(passWord);
    }
    public boolean validarEquipo(String nombreEquipo) throws Exception {
        //Hecho con toLowerCase para consultas y transacciones con BD
        return modeloController.validarEquipo(nombreEquipo.toLowerCase());
    }
    public boolean validarJugador(String nickName) throws SQLException {
        if(validarNik(nickName)){
            return modeloController.validarJugador(nickName);
        }
        return false;
    }
    public boolean validarSueldo(String sueldo){
        return Double.parseDouble(sueldo) >= 1184.00;
    }
    public boolean validarNik(String nickName){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,20}$");
        Matcher matcher = pattern.matcher(nickName);
        return matcher.matches();
    }
    public boolean validarNomYAp(String nombreJugador) {
        final Pattern pattern = Pattern.compile("^[a-z ]{2,20}+$");
        final Matcher matcher = pattern.matcher(nombreJugador);
        return !matcher.matches();
    }
    public  boolean validarFechaNac(String fechaNaci) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return LocalDate.parse(fechaNaci, formatter).isAfter(LocalDate.now());
    }

    /**Metodos de creacion*/
    public boolean crearEquipo(String nombre,String fechaFund) throws Exception {
        return modeloController.crearEquipo(nombre,fechaFund);
    }
    public boolean crearJugador(String nombre, String apellido, String nacionalidad, String fechaNac, String sueldo, String rol, String nickName, int codEquipo) throws SQLException {
        return modeloController.crearJugador(nombre,apellido,nacionalidad,fechaNac,sueldo,rol,nickName,codEquipo);
    }
    /**Metodos de borrado*/
    public boolean borrarJugador(String nickName) throws SQLException {
        return modeloController.borrarJugador(nickName);
    }
    public boolean borrarEquipo(String nombreEquipo) throws Exception {
        return modeloController.borrarEquipo(nombreEquipo);
    }

    /**Metodos de consulta*/
    public void rellenarCamposEquipo(JPanel pPrincipal) {
        //entre otros
        consultarEquipo.getTfNombreEquipo().setText(modeloController.equipo.getNombre());
        consultarEquipo.getTfCodEquipo().setText(String.valueOf(modeloController.equipo.getCodEquipo()));
        consultarEquipo.getTfFechaFundacion().setText(modeloController.equipo.getFechaFundacion().toString());
        consultarEquipo.getTfPuntuacionTotal().setText(String.valueOf(modeloController.equipo.getPuntuacion()));
        //Comentadfo por que se pide en otro metodo(de BD)
        pPrincipal.revalidate();
        pPrincipal.repaint();
    }
    public void rellenarCamposJugador(JPanel pPrincipal){
        consultarJugador.getTfNombre().setText(modeloController.jugador.getNombre());
        consultarJugador.getTfApellido().setText(modeloController.jugador.getApellido());
        consultarJugador.getTfRol().setText(modeloController.jugador.getRol());
        consultarJugador.getTfFechaNaci().setText(modeloController.jugador.getFechaNacimiento().toString());
        consultarJugador.getTfNacionalidad().setText(modeloController.jugador.getNacionalidad());
        consultarJugador.getTfSueldo().setText(String.valueOf(modeloController.jugador.getSueldo()));
        consultarJugador.getTfEquipo().setText(modeloController.jugador.getEquipo().getNombre());
        //se omite el nickname por que si ha llegado aqui es por que es correcto
        pPrincipal.revalidate();
        pPrincipal.repaint();
    }
    public void rellenarCamposEquipoUpdate(JPanel pPrincipal) {
        actualizarEquipo.getTfNombreNuevo().setText(modeloController.equipo.getNombre());
        actualizarEquipo.getTfFechaFundNueva().setText(modeloController.equipo.getFechaFundacion().toString());
        pPrincipal.revalidate(); pPrincipal.repaint();
    }

    /**Metodos de Actualizacion de datos*/
    public boolean actualizarEquipoFecha(String nombreEquipo, String fechaFund) throws Exception {
        return modeloController.actualizarEquipoFecha(nombreEquipo,fechaFund);
    }


}