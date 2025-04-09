package ModeloController;

import BaseDatos.BaseDatos;
import Modelo.Equipo;
import Modelo.Usuario;
import ModeloDAO.*;

import java.sql.Connection;
import java.sql.SQLException;

public class ModeloController {
    protected VistaController vistaController;

    protected CompeticionController competicionController;
    protected EnfrentamientoController enfrentamientoController;
    protected EquipoController equipoController;
    protected JornadaController jornadaController;
    protected JuegoController juegoController;
    protected JugadorController jugadorController;
    protected RolController rolController;
    private UsuarioController usuarioController;

    protected Usuario usuario;
    protected Equipo equipo;
    public ModeloController() {
        try {
            //BD
            Connection c = BaseDatos.abrirCon();

            //dao + conexion a BD
            CompeticionDAO competicionDAO = new CompeticionDAO(c);
            EnfrentamientoDAO enfrentamientoDAO = new EnfrentamientoDAO(c);
            EquipoDAO equipoDAO = new EquipoDAO(c);
            JornadaDAO jornadaDAO = new JornadaDAO(c);
            JuegoDAO juegoDAO = new JuegoDAO(c);
            JugadorDAO jugadorDAO = new JugadorDAO(c);
            RolDAO rolDAO = new RolDAO(c);
            UsuarioDAO usuarioDAO = new UsuarioDAO(c);

            //Controllers
            competicionController = new CompeticionController(competicionDAO);
            enfrentamientoController = new EnfrentamientoController(enfrentamientoDAO);
            equipoController = new EquipoController(equipoDAO);
            jornadaController = new JornadaController(jornadaDAO);
            juegoController = new JuegoController(juegoDAO);
            jugadorController= new JugadorController(jugadorDAO);
            rolController = new RolController(rolDAO);
            usuarioController = new UsuarioController(usuarioDAO);
        }catch (Exception e) {
            System.out.println("ERROR EN MODELO CONTROLLER "+e.getMessage());
        }
    }

    public void setVistaController(VistaController vistaController) {
        this.vistaController = vistaController;
    }

    public boolean validarUsuario(String nombreUsuario) throws SQLException {
        usuario = usuarioController.validarUsuario(nombreUsuario);
        return usuario != null;
    }
    public boolean validarPassWord(String passWord){
        return usuario.getPaswd().equals(passWord);
    }
    public boolean validarEquipo(String nombreEquipo, String fechaFund) throws Exception {
        //falta meter Patron aqui para el nombre y fecha
        equipo = equipoController.validarEquipo(nombreEquipo, fechaFund);
        return equipo!= null;
    }
}
