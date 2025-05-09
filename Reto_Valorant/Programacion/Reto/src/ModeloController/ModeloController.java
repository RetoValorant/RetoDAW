package ModeloController;

import BaseDatos.BaseDatos;
import Modelo.*;
import ModeloDAO.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ModeloController {
    protected VistaController vistaController;

    protected CompeticionController competicionController;
    protected EnfrentamientoController enfrentamientoController;
    protected EquipoController equipoController;
    protected JornadaController jornadaController;
    protected JugadorController jugadorController;
    protected UsuarioController usuarioController;

    protected Usuario usuario;
    protected Equipo equipo;
    protected Jugador jugador;

    
    /**
     * Constructor 
     * Inicia  conexión a la base de datos 
     */
    public ModeloController() {
        try {
            //BD
            Connection c = BaseDatos.abrirCon();

            //dao + conexion a BD
            JuegoDAO jDAO = new JuegoDAO(c);
            CompeticionDAO competicionDAO = new CompeticionDAO(c);
            EnfrentamientoDAO enfrentamientoDAO = new EnfrentamientoDAO(c);
            EquipoDAO equipoDAO = new EquipoDAO(c);
            JornadaDAO jornadaDAO = new JornadaDAO(c, competicionDAO);
            JugadorDAO jugadorDAO = new JugadorDAO(c);
            UsuarioDAO usuarioDAO = new UsuarioDAO(c);

            //Controllers
            competicionController = new CompeticionController(competicionDAO);
            enfrentamientoController = new EnfrentamientoController(enfrentamientoDAO,this);
            enfrentamientoDAO.setEnfrentamientoController(enfrentamientoController);
            equipoController = new EquipoController(equipoDAO);
            jornadaController = new JornadaController(jornadaDAO,this);
            jugadorController= new JugadorController(jugadorDAO);
            usuarioController = new UsuarioController(usuarioDAO);
        }catch (Exception e) {
            System.out.println("ERROR EN MODELO CONTROLLER "+e.getMessage());
        }
    }
    //Metodo auxiliar de Main
    /**
     * Establece el controlador de vista
     * @param vistaController 
     */
    public void setVistaController(VistaController vistaController) {
        this.vistaController = vistaController;
    }
    //Metodos para optimizar accesos a BD
    /**
     * Obtiene la lista de equipos 
     * @return equipos
     * @throws SQLException si ocurre un error
     */
    public List<Equipo> getEquipos() throws SQLException {
        return equipoController.getEquipos();
    }

    /**
     * Obtiene los nombres de equipos usando un procedimiento
     * @return equipos
     * @throws SQLException si ocurre un error
     */
    public List<String> getEquiposProcedimiento() throws SQLException {
        return equipoController.getEquiposProcedimiento();
    }
    
    /**
     * Devuelve el equipo 
     * @return equipo actual
     */
    public Equipo getEquipo(){
        return equipo;
    }

    /**
     * Obtiene enfrentamientos
     * @return lista de enfrentamientos
     * @throws Exception si ocurre un error
     */
    public List<Enfrentamiento> getEnfrentamientos() throws Exception{
        return enfrentamientoController.getEnfrentamientos();
    }

    //Metodos de validacion
    /**
     * Valida un usuario
     * @param u usuario a validar
     * @return true si es válido, false en caso contrario
     * @throws SQLException si ocurre un error
     */
    public boolean validarUsuario(Usuario u) throws SQLException {
        usuario = usuarioController.validarUsuario(u);
        return usuario != null;
    }

    /**
     * Verifica si la contraseña ingresada es correcta
     * @param passWord contraseña ingresada
     * @return usuario 
     */
    public boolean validarPassWord(String passWord){
        return usuario.getPaswd().equals(passWord);
    }

    /**
     * Retorna el tipo de usuario 
     * @return tipo de usuario 
     */
    public String tipoUsuario(){
        return usuario.getTipoUsuario();
    }

    /**
     * Obtiene el estado de la competición
     * @return estado
     * @throws SQLException si ocurre un error
     */
    public char estadoCompeticion() throws SQLException{
        return competicionController.getCompeticion().getEstado();
    }

     /**
     * Valida un equipo 
     * @param eq equipo a validar
     * @return equpio valido, y q sea distinto de null
     * @throws Exception si ocurre un error
     */
    public boolean validarEquipo(Equipo eq) throws Exception {
        equipo = equipoController.validarEquipo(eq);
        return equipo != null;
    }
    /**
     * Valida un jugador 
     * @param jugador jugador a validar
     * @return jugador valido, y q sea distinto de null
     * @throws SQLException si ocurre un error
     */

    public boolean validarJugador(Jugador j) throws SQLException {
        jugador = jugadorController.obtnerJugador(j);
        return jugador != null;
    }

    /**
     * Crea las jornadas para la competición.
     * @throws SQLException si ocurre un error
     */
    public void crearJornadas()throws SQLException{
        jornadaController.crearJornada();
    }

    /**
     * Devuelve el jugador 
     * @return jugador 
     */
    public Jugador devolverJugador(){
        return jugador;
    }

    
    /**
     * Crea enfrentamientos entre equipos.
     * @throws Exception si ocurre un error
     */
    public void crearEnfrentamiento() throws Exception {
        enfrentamientoController.crearEnfrentamientos();
    }


        //Metodos de creacion
    /**
     * Crea un nuevo equipo
     * @param equipo equipo a crear
     * @return equipo creado correctamente
     * @throws Exception si ocurre un error
     */
    public boolean crearEquipo(Equipo equipo) throws Exception {
        return equipoController.crearEquipo(equipo);
    }

    /**
     * Crea un nuevo jugador
     * @param jugador jugador a crear
     * @return jugador creado correctamente
     * @throws SQLException si ocurre un error
     */
    public boolean crearJugador(Jugador jugador) throws SQLException {
        return jugadorController.crearJugador(jugador);
    }

    //Metodos de borrado
    
    /**
     * Borra un jugador 
     * @param ju jugador a eliminar
     * @return jugador borrado correctamente
     * @throws SQLException si ocurre un error
     */
    public boolean borrarJugador(Jugador ju) throws SQLException {
        return jugadorController.borrarJugador(ju);
    }

    /**
     * Borra un equipo del sistema
     * @param equipo equipo a eliminar
     * @return equipo borrado correctamente
     * @throws Exception si ocurre un error
     */
    public boolean borrarEquipo(Equipo equipo) throws Exception {
        return equipoController.borrarEquipo(equipo);
    }

    //Metodos de actualizacion
    /**
     * Actualiza la fecha de un equipo
     * @param eq equipo a actualizar
     * @return fecha de equipo actualiza 
     * @throws Exception si ocurre un error
     */
    public boolean actualizarEquipoFecha(Equipo eq) throws Exception {
        return equipoController.actualizarEquipoFecha(eq);
    }

    
    /**
     * Actualiza el nombre de un equipo
     * @param eq equipo a actualizar
     * @return nombre de equipo actualiza 
     * @throws Exception si ocurre un error
     */
    public boolean actualizarEquipoNombre(Equipo eq) throws Exception {
        return equipoController.actualizarEquipoNombre(eq);
    }

    /**
     * Devuelve la lista de jornadas 
     * @return lista de jornadas
     * @throws SQLException si ocurre un error
     */
    public List<Jornada> getJornadas() throws SQLException{
        return jornadaController.getJornadas();
    }

    /**
     * Obtiene la competición 
     * @return competición actual
     * @throws SQLException si ocurre un error
     */
    public Competicion getCompeticion() throws SQLException{
        return competicionController.getCompeticion();
    }

    /**
     * Obtiene una jornada teniendo en cuenta su ID
     * @param id identificador de la jornada
     * @return  Jornada teniendo en cuenta su ID
     * @throws SQLException si ocurre un error
     */
    public Jornada getJornadaPorId(int id) throws SQLException{
        return jornadaController.getJornadaPorId(id);
    }

    /**
     * Guarda el resultado de un enfrentamiento.
     * @param enfrentamiento enfrentamiento con resultados
     * @throws Exception si ocurre un error
     */
    public void guardarResultados(Enfrentamiento enfrentamiento) throws Exception {
        enfrentamientoController.actualizarResultado(enfrentamiento);
    }

    /**
     * Obtiene la lista de enfrentamientos 
     * @param j ID de la jornada
     * @return los enfrentamientos
     * @throws Exception si ocurre un error 
     */
    public List<Enfrentamiento> enfrentamientos(int j) throws Exception {
        return enfrentamientoController.enfrentamientos(j);
    }

    /**
     * Obtiene los enfrentamientos 
     * @param j ID de la jornada
     * @return Lista de enfrentamientos
     * @throws Exception si ocurre un error
     */
    public List<String> enfrentamientosProcedimiento(int j) throws Exception {
        return enfrentamientoController.enfrentamientosProcedimiento(j);
    }
    
    /**
     * Devuelve una lista  los jugadores que pertenecen a un equipo.
     * @param equipo Nombre del equipo
     * @return jugadores que pertenecen a un equipo
     * @throws Exception si ocurre un error 
     */
    public List<String> jugadores(String equipo) throws Exception {
        return jugadorController.jugadores(equipo);
    }

    /**
     * Cambia el estado de la competición a iniciada, empezada
     * @throws SQLException si ocurre un error
     */
    public void empezarCompeticion() throws SQLException {
        competicionController.empezarCompeticion();
    }

    /**
     * Obtiene un  Equipo teniendo en cuenta su ID
     * @param id Identificador  del equipo
     * @return el equipo teniendo en cuenta su ID
     * @throws Exception si ocurre un error 
     */
    public Equipo getEquipoPorId(int id) throws Exception{
        return equipoController.getEquipoPorId(id);
    }
}
