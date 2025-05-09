package ModeloController;

import Modelo.*;
import Vista.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VistaController {
    protected ModeloController modeloController;
    private ConsultarEquipo consultarEquipo;
    private ActualizarEquipo actualizarEquipo;
    private ConsultarJugador consultarJugador;
    private ConsultarJugadoresPorEquipo consultarJugadoresPorEquipo;
    private GestionarEnfrentamientos gestionarEnfrentamientos;
    private VerEnfrentamientos verEnfrentamientos;
    private VerTodosEquipos verTodosEquipos;
    private final LocalDate muyPeque = LocalDate.now().minusYears(16);
    private final LocalDate muyMayor = LocalDate.now().plusYears(65);
    private List<Enfrentamiento> enfrentamientos;
    private Enfrentamiento enfrentamientoElegido;
    private Jugador jugador;
    protected JugadorController jugadorController;

    /**
     * Constructor 
     * @param modeloController 
     */
    public VistaController(ModeloController modeloController) {
        this.modeloController = modeloController;
        setIniciarSesion();
    }

    //Metodos de construccion de ventanas
    /**
     * Lanza la ventana de inicio de sesión.
     */
    public void setIniciarSesion() {
        IniciarSesion iniciarSesion = new IniciarSesion(this);
        iniciarSesion.setVisible(true);
    }

    /**
     * Abre la vista para consultar un equipo.
     * @param vistaController 
     */
    public void setConsultarEquipo(VistaController vistaController){
        consultarEquipo = new ConsultarEquipo(vistaController);
        consultarEquipo.setVisible(true);
    }

    /**
     * Crea y muestra la ventana que permite ver todos los equipos
     * @param vistaController 
     */
    public void setVerTodosEquipos(VistaController vistaController){
        verTodosEquipos = new VerTodosEquipos(vistaController);
        verTodosEquipos.setVisible(true);
    }

     /**
     * Crea y muestra la ventana para borrar un equipo
     * @param vistaController 
     */
    public void setBorrarEquipo(VistaController vistaController){
        BorrarEquipo borrarEquipo = new BorrarEquipo(vistaController);
        borrarEquipo.setVisible(true);
    }

    /**
     * Crea y muestra la ventana para borrar un jugador
     * @param vistaController 
     */
    public void setBorrarJugador(VistaController vistaController){
        BorrarJugador borrarJugador = new BorrarJugador(vistaController);
        borrarJugador.setVisible(true);
    }

    
    /**
     * Crea y muestra la ventana para consultar un jugador
     * @param vistaController 
     */
    public void setConsultarJugador(VistaController vistaController){
        consultarJugador = new ConsultarJugador(vistaController);
        consultarJugador.setVisible(true);
    }

    
    /**
     * Crea y muestra la ventana para actualizar los datos de un jugador
     * @param vistaController 
     */
    public void setActualizarJugador(VistaController vistaController){
        ActualizarJugador actualizarJugador = new ActualizarJugador(vistaController);
        actualizarJugador.setVisible(true);
    }

     /**
     * Crea y muestra la ventana para crear un nuevo jugador
     * @param vistaController 
     * @throws SQLException Si ocurre un error 
     */
    public void setCrearJugador(VistaController vistaController) throws SQLException {
        CrearJugador crearJugador = new CrearJugador(vistaController);
        crearJugador.setVisible(true);
    }

    
    /**
     * Crea y muestra la ventana para crear un nuevo equipo
     * @param vistaController 
     */
    public void setCrearEquipo(VistaController vistaController){
        CrearEquipo crearEquipo = new CrearEquipo(vistaController);
        crearEquipo.setVisible(true);
    }

    /**
     * Crea y muestra la ventana para actualizar los datos de un equipo
     * @param vistaController 
     */
    public void setActualizarEquipo(VistaController vistaController){
        actualizarEquipo = new ActualizarEquipo(vistaController);
        actualizarEquipo.setVisible(true);
    }

    /**
     * Crea y muestra la ventana para gestionar enfrentamientos
     * @param vistaController
     */
    public void setGestionarEnfrentamientos(VistaController vistaController){
        gestionarEnfrentamientos = new GestionarEnfrentamientos(vistaController);
        gestionarEnfrentamientos.obtenerJornadas();
        //gestionarEnfrentamientos.rellenarConEquipos();
        gestionarEnfrentamientos.setVisible(true);
    }

    /**
     * Crea y muestra la ventana para ver enfrentamientos 
     * @param vistaController 
     * @param tipoUsuario Tipo de usuario que accede a la vista
     */
    public void setVerEnfrentamientos(VistaController vistaController, String tipoUsuario){
        verEnfrentamientos = new VerEnfrentamientos(vistaController, tipoUsuario);
        verEnfrentamientos.obtenerJornadas();
        //gestionarEnfrentamientos.rellenarConEquipos();
        verEnfrentamientos.setVisible(true);
    }
    
    /**
     * Crea y muestra la ventana para consultar los jugadores de un equipo
     * @param vistaController 
     */
    public void setConsultarJugadoresPorEquipo(VistaController vistaController){
        consultarJugadoresPorEquipo = new ConsultarJugadoresPorEquipo(vistaController);
        consultarJugadoresPorEquipo.obtenerEquipos();
        consultarJugadoresPorEquipo.setVisible(true);
    }

     /**
     * Crea una nueva jornada 
     * @throws SQLException si hay un error 
     */
    public void crearJornadas()throws SQLException{
        modeloController.crearJornadas();
    }

     /**
     * Crea una nuevo enfrentamiento 
     * @throws SQLException si hay un error 
     */
    public void crearEnfrentamiento() throws Exception {
        modeloController.crearEnfrentamiento();
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
        Usuario usuario = new Usuario(nombreUsuario);
        return modeloController.validarUsuario(usuario);
    }
    public boolean validarPassWord(String passWord) throws SQLException {
        return modeloController.validarPassWord(passWord);
    }
    public String tipoUsuario(){
        return modeloController.tipoUsuario();
    }
    public char estadoCompeticion() throws SQLException{
        return modeloController.estadoCompeticion();
    }
    public boolean validarEquipo(String nombreEquipo) throws Exception {
        //Hecho con toLowerCase para consultas y transacciones con BD
        Equipo equipo = new Equipo(nombreEquipo.toLowerCase());
        return modeloController.validarEquipo(equipo);
    }
    public boolean validarJugador(String nickName) throws SQLException {
        if(validarNik(nickName)){
            Jugador jugador = new Jugador(nickName);
            return modeloController.validarJugador(jugador);
        }
        return false;
    }
    public boolean validarSueldo(String sueldo){
        return Double.parseDouble(sueldo) >= 1184.00;
    }

    /**
     * Valida si un nickname es correcto según el patrón definido
     * @param nickName Nickname del jugador
     * @return nickname valido
     */
    public boolean validarNik(String nickName){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,20}$");
        Matcher matcher = pattern.matcher(nickName);
        return matcher.matches();
    }

    
    /**
     * Valida si el nombre del jugador cumple el formato definido
     * @param nombreJugador Nombre del jugador
     * @return false 
     * @throws Exception si no cumple con lo definido
     */
    public boolean validarNomYAp(String nombreJugador)throws Exception {
        final Pattern pattern = Pattern.compile("^[A-Za-zñÑáéíóúÁÉÍÓÚüÜ ]{2,20}$");
        final Matcher matcher = pattern.matcher(nombreJugador);
        if (!matcher.matches()) {
            throw new Exception("El campo rellenado debe ser minimo de 2 a 20 caracteres");
        }
        return false;
    }

     /**
     * Valida la fecha de nacimiento del jugador
     * @param fechaNaci Fecha de nacimiento en formato dd-MM-yyyy
     * @return fecha si está en el rango válido de edad 16-65 años
     * @throws Exception si la fecha está fuera del rango 16-65
     */
    public  boolean validarFechaNac(String fechaNaci) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaNaci, formatter);

        if (fecha.isAfter(muyPeque) || fecha.isBefore(muyMayor)) {
            return fecha.isBefore(LocalDate.now());
        }
        throw new Exception("La edad debe estar comprendida entre los 16 y 65 años");
    }

    //Metodos de creacion
    /**
     * para la cracion de equipo, y su fecha de fundacion
     * @param nombre
     * @param fechaFund
     * @return equipo
     * @throws Exception si salta un error
     */
    public boolean crearEquipo(String nombre,String fechaFund) throws Exception {
        Equipo equipo = new Equipo(nombre,validarFecha(fechaFund));
        return modeloController.crearEquipo(equipo);
    }

    
    /**
     * para la cracion de equipo, y su fecha de fundacion
     * @param nombre
     * @param apellido
     * @param nacionalidad
     * @param fechaNac
     * @param sueldo
     * @return rol
     * @param equipo
     * @return jugador
     * @throws Exception si salta un error
     */
    public boolean crearJugador(String nombre, String apellido, String nacionalidad, String fechaNac, String sueldo, String rol, String nickName, Equipo equipo) throws Exception {
        Jugador jugador = new Jugador(nombre,apellido,nacionalidad,fechaNac,sueldo,rol,nickName,equipo);
        return modeloController.crearJugador(jugador);
    }

    //Metodos de borrado
     /**
     * Borrar jugadores 
     * @param nickName
     * @return jugador
     * @throws Exception si salta un error
     */
    public boolean borrarJugador(String nickName) throws SQLException {
        Jugador jugador = new Jugador(nickName);
        return modeloController.borrarJugador(jugador);
    }

     /**
     * Borrar los equipos
     * @param nombreEquipo
     * @return equipo
     * @throws Exception si salta un error
     */
    public boolean borrarEquipo(String nombreEquipo) throws Exception {
        Equipo equipo = new Equipo(nombreEquipo.toLowerCase());
        return modeloController.borrarEquipo(equipo);
    }

    /**
     * Valida el formato de una fecha 
     * @param fecha Fecha en formato dd-MM-yyyy
     * @return Fecha válida 
     * @throws Exception si la fecha está en un formato inválido
     */
    public LocalDate validarFecha(String fecha) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate fechaLocalDate = LocalDate.parse(fecha.trim(), formatter);
            if (fechaLocalDate.isAfter(LocalDate.now())) {
                throw new Exception("La fecha de fundacion no puede ser de anterior a la del juego");
            }
            return fechaLocalDate;
        }catch (DateTimeParseException e){
            throw new DateTimeParseException("La fecha no sigue un formato valido (dd-mm-aaaa)", fecha,0);
        }
    }

    /**Metodos de consulta*/
    public void rellenarCamposEquipo(JPanel pPrincipal) {
        //entre otros
        consultarEquipo.getTfNombreEquipo().setText(modeloController.equipo.getNombre());
        consultarEquipo.getTfCodEquipo().setText(String.valueOf(modeloController.equipo.getCodEquipo()));
        consultarEquipo.getTfFechaFundacion().setText(modeloController.equipo.getFechaFundacion().toString());
        consultarEquipo.getTfPuntuacionTotal().setText(String.valueOf(modeloController.equipo.getPuntuacion()));
        pPrincipal.revalidate();
        pPrincipal.repaint();
    }

    /**El objetivo es que con cada Jornada el TA cambie tambien*/
    /*
    public void rellenarCamposGestionarEnfrentamientos(JPanel pPrincipal) {
        List<Enfrentamiento> enfrentamientos = modeloController.getEnfrentamientos();

        pPrincipal.removeAll();
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS)); // Example layout

        // Para el TextArea
        JTextArea taEnfrentamientos = gestionarEnfrentamientos.getTaEnfrentamientos();
        taEnfrentamientos.setText("");
        for (Enfrentamiento e : enfrentamientos) {
            taEnfrentamientos.append(e.toString() + "\n");
        }
        pPrincipal.add(new JScrollPane(taEnfrentamientos));

        // Add matchup selection components
        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            // Create a panel for each matchup
            JPanel matchupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            matchupPanel.setBorder(BorderFactory.createTitledBorder("Enfrentamiento: " + enfrentamiento.getIdEnfrentamiento()));

            ButtonGroup bg = new ButtonGroup();
            JRadioButton rb1 = new JRadioButton(enfrentamiento.getEquipo1().getNombre());
            JRadioButton rb2 = new JRadioButton(enfrentamiento.getEquipo2().getNombre());

            bg.add(rb1);
            bg.add(rb2);

            //guarda la seleccion del boton en caso de necesitarlo luego
            rb1.setActionCommand(enfrentamiento.getIdEnfrentamiento() + "_team1");
            rb2.setActionCommand(enfrentamiento.getIdEnfrentamiento() + "_team2");

            matchupPanel.add(rb1);
            matchupPanel.add(rb2);

            pPrincipal.add(matchupPanel);
        }

        pPrincipal.revalidate();
        pPrincipal.repaint();
    }
     */

    public void rellenarCamposJugador(JPanel pPrincipal){
        Jugador jugador  = modeloController.devolverJugador();
        consultarJugador.getTfNombre().setText(jugador.getNombre());
        consultarJugador.getTfApellido().setText(jugador.getApellido());
        consultarJugador.getTfRol().setText(jugador.getRol());
        consultarJugador.getTfFechaNaci().setText(jugador.getFechaNacimiento().toString());
        consultarJugador.getTfNacionalidad().setText(jugador.getNacionalidad());
        consultarJugador.getTfSueldo().setText(String.valueOf(jugador.getSueldo()));
        consultarJugador.getTfEquipo().setText(jugador.getEquipo().getNombre());
        //se omite el nickname por que si ha llegado aqui es por que es correcto
        pPrincipal.revalidate();
        pPrincipal.repaint();
    }

     /**
     * Rellena los campos del formulario de actualización de equipo 
     * @param pPrincipal 
     */
    public void rellenarCamposEquipoUpdate(JPanel pPrincipal) {
        actualizarEquipo.getTfNombreNuevo().setText(modeloController.equipo.getNombre());
        actualizarEquipo.getTfFechaFundNueva().setText(modeloController.equipo.getFechaFundacion().toString());
        pPrincipal.revalidate(); pPrincipal.repaint();
    }

    /**
     * Recupera una lista con los nombres de los equipos
     * @return lista de equipos.
     * @throws SQLException Si ocurre un error 
     */
    public List<String> rellenarEquipos() throws SQLException {
        return modeloController.getEquiposProcedimiento();
    }

    //Metodos de Actualizacion de datos

     /**
     * Actualiza la fecha de fundación de un equipo
     * @param nombreEquipo El nombre del equipo a actualizar
     * @param fechaFund La nueva fecha de fundación
     * @return equipo validado
     * @throws Exception Si ocurre un error 
     */
    public boolean actualizarEquipoFecha(String nombreEquipo, LocalDate fechaFund) throws Exception {
        Equipo equipo = new Equipo(nombreEquipo, validarFecha(String.valueOf(fechaFund)));
        return modeloController.actualizarEquipoFecha(equipo);
    }

     /**
     * Actualiza el nombre de un equipo manteniendo su fecha de fundación
     * @param nombreEquipo El nuevo nombre del equipo
     * @param fechaFund La fecha de fundación del equipo
     * @return equipo correctamente
     * @throws Exception Si ocurre un error
     */
    public boolean actualizarEquipoNombre(String nombreEquipo, LocalDate fechaFund) throws Exception {
        Equipo equipo = new Equipo(nombreEquipo, validarFecha(String.valueOf(fechaFund)));
        return modeloController.actualizarEquipoNombre(equipo);
    }

    /**
     * Obtiene la lista de jornadas 
     * @return jornadas 
     * @throws Exception Si ocurre un error 
     */
    public List<Jornada> obtenerJornadas() throws Exception {
        return modeloController.jornadaController.getJornadas();
    }

    public void empezarCompeticion() throws SQLException {
        modeloController.empezarCompeticion();
    }

    /**
     * Guarda los resultados de un enfrentamiento 
     * @param res1 Resultado del equipo 1
     * @param res2 Resultado del equipo 2
     * @throws Exception si ocurre un error al guardar los resultados
     */
    public void guardarResultados(String res1, String res2) throws Exception {
        if (res1 == null)
            enfrentamientoElegido.setResultadosEq1(0);
        else
            enfrentamientoElegido.setResultadosEq2(Integer.parseInt(res1));
        if (res2 == null)
            enfrentamientoElegido.setResultadosEq2(0);
        else
            enfrentamientoElegido.setResultadosEq1(Integer.parseInt(res2));
        modeloController.guardarResultados(enfrentamientoElegido);
    }

    
    public List<String> enfrentamientos(String jornada) throws Exception {
        enfrentamientos = modeloController.enfrentamientos(Integer.parseInt(jornada));
        List<String> resultados = new ArrayList<>();
        for (Enfrentamiento e : enfrentamientos) {
            resultados.add(enfrentamiento(e.getEquipo1().getNombre(),e.getEquipo2().getNombre()));
        }
        return resultados;
    }
    public void enfrentamientosProcedimiento(String jornada, JPanel pPrincipal) throws Exception {
        List<String> enfrentamientosProcedimientos = modeloController.enfrentamientosProcedimiento(Integer.parseInt(jornada));
        for (String enfrentamiento : enfrentamientosProcedimientos) {
            JLabel label = new JLabel(enfrentamiento);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            pPrincipal.add(label);
        }
    }
    public List<String> jugadores(String equipo, JPanel pPrincipal) throws Exception {
        return modeloController.jugadores(equipo);
    }
    public void rellenarCamposJugadorUpdate(JPanel pPrincipal) throws SQLException {
        // Obtener el jugador actual desde el atributo del controlador
        Jugador jugador = modeloController.devolverJugador();
        if (jugador == null) {
            throw new SQLException("No se pudo obtener el jugador.");
        }

        // Iterar por cada componente del panel
        for (Component comp : pPrincipal.getComponents()) {
            if (comp instanceof JTextField textField) {
                // Usamos los nombres asignados para identificar cada campo
                switch (textField.getName()) {
                    case "tfNombre" -> textField.setText(jugador.getNombre());
                    case "tfApellido" -> textField.setText(jugador.getApellido());
                    case "tfNacionalidad" -> textField.setText(jugador.getNacionalidad());
                    case "tfFechaNacimiento" -> textField.setText(jugador.getFechaNacimiento().toString());
                    case "tfSueldo" -> textField.setText(String.valueOf(jugador.getSueldo()));
                    case "tfRol" -> textField.setText(jugador.getRol());
                }
            } else if (comp instanceof JComboBox<?> comboBox && "cbEquipos".equals(comboBox.getName())) {
                comboBox.setSelectedItem(jugador.getEquipo());
            }
        }
    }
    public boolean actualizarJugador(String apellido, String nombre, String rol, String nacionalidad, LocalDate fecha, Double sueldo) throws Exception {
        Jugador jugadorActualizado = modeloController.devolverJugador();
        jugadorActualizado.setApellido(apellido);
        jugadorActualizado.setNombre(nombre);
        jugadorActualizado.setRol(rol);
        jugadorActualizado.setNacionalidad(nacionalidad);
        jugadorActualizado.setFechaNacimiento(fecha);
        jugadorActualizado.setSueldo(sueldo);
        return jugadorController.actualizarJugador(jugadorActualizado);
    }

    public void cargarEquiposEnComboBox(JComboBox<Equipo> comboBox) throws SQLException {
        comboBox.removeAllItems(); // Limpia el ComboBox
        List<Equipo> equipos = getEquipos(); // Método que obtiene la lista de equipos desde la base de datos
        for (Equipo equipo : equipos) {
            comboBox.addItem(equipo); // Agrega cada equipo al ComboBox
        }
    }
    public void enfrentamientoElegido(String enfrentamiento) throws Exception {
        for (Enfrentamiento e : enfrentamientos) {
            if (enfrentamiento.equals(enfrentamiento(e.getEquipo1().getNombre(),e.getEquipo2().getNombre()))) {
                enfrentamientoElegido = e;
                break;
            }
        }
    }
    public String enfrentamiento(String eq1, String eq2) {
        StringBuilder sb = new StringBuilder();
        sb.append(eq1);
        sb.append(" - ");
        sb.append(eq2);
        return sb.toString();
    }
    public String equipo1() {
        return enfrentamientoElegido.getEquipo1().getNombre();
    }
    public String equipo2() {
        return enfrentamientoElegido.getEquipo2().getNombre();
    }
    public int equipo1Res() {
        return enfrentamientoElegido.getResultadosEq1();
    }
    public int equipo2Res() {
        return enfrentamientoElegido.getResultadosEq2();
    }
}
