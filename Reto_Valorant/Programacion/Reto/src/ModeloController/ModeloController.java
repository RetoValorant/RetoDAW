package ModeloController;

import BaseDatos.BaseDatos;
import Modelo.Equipo;
import Modelo.Jugador;
import Modelo.Usuario;
import ModeloDAO.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    public ModeloController() {
        try {
            //BD
            Connection c = BaseDatos.abrirCon();

            //dao + conexion a BD
            JuegoDAO jDAO = new JuegoDAO(c);
            CompeticionDAO competicionDAO = new CompeticionDAO(c, jDAO);
            EnfrentamientoDAO enfrentamientoDAO = new EnfrentamientoDAO(c);
            EquipoDAO equipoDAO = new EquipoDAO(c);
            JornadaDAO jornadaDAO = new JornadaDAO(c, competicionDAO);
            JugadorDAO jugadorDAO = new JugadorDAO(c);
            UsuarioDAO usuarioDAO = new UsuarioDAO(c);

            //Controllers
            competicionController = new CompeticionController(competicionDAO);
            enfrentamientoController = new EnfrentamientoController(enfrentamientoDAO);
            equipoController = new EquipoController(equipoDAO);
            jornadaController = new JornadaController(jornadaDAO);
            jugadorController= new JugadorController(jugadorDAO);
            usuarioController = new UsuarioController(usuarioDAO);
        }catch (Exception e) {
            System.out.println("ERROR EN MODELO CONTROLLER "+e.getMessage());
        }
    }
    /**Metodo auxiliar de Main*/
    public void setVistaController(VistaController vistaController) {
        this.vistaController = vistaController;
    }
    /**Metodos para optimizar accesos a BD*/
    public List<Equipo> getEquipos() throws SQLException {
        return equipoController.getEquipos();
    }
    public Equipo getEquipo(){
        return equipo;
    }
    /**Meotos de validacion*/
    public boolean validarUsuario(String nombreUsuario) throws SQLException {
        usuario = usuarioController.validarUsuario(nombreUsuario);
        return usuario != null;
    }
    public boolean validarPassWord(String passWord){
        return usuario.getPaswd().equals(passWord);
    }
    public boolean validarEquipo(String nombreEquipo) throws Exception {
        equipo = equipoController.validarEquipo(nombreEquipo);

        if (equipo != null){
            equipo.setListaJugadores(jugadorController.obtenerJugadores(equipo.getCodEquipo()));
            //para poder aprovechar directamente todos sus atributos lo relleno ya
        }
        return equipo != null;
    }
    public boolean validarJugador(String nickName) throws SQLException {
        jugador = jugadorController.obtnerJugador(nickName);
        return jugador != null;
    }

    /**Metodos de creacion*/
    public boolean crearEquipo(String nombre,String fechaFund) throws Exception {
        return equipoController.crearEquipo(nombre, fechaFund);
    }
    public boolean crearJugador(String nombre, String apellido, String nacionalidad, String fechaNac, String sueldo, String rol, String nickName, int codEquipo) throws SQLException {
        return jugadorController.crearJugador(nombre,apellido,nacionalidad,fechaNac,sueldo,rol,nickName,codEquipo);
    }

    /**Metodos de borrado*/
    public boolean borrarJugador(String nickName) throws SQLException {
        return jugadorController.borrarJugador(nickName);
    }
    public boolean borrarEquipo(String nombreEquipo) throws Exception {
        return equipoController.borrarEquipo(nombreEquipo);
    }

    /**Metodos de actualizacion*/
    public boolean actualizarEquipoFecha(String nombreEquipo, String fechaFund) throws Exception {
        return equipoController.actualizarEquipoFecha(nombreEquipo,fechaFund);
    }
}
