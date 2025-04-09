package ModeloController;

import Modelo.Usuario;
import ModeloDAO.UsuarioDAO;

import java.sql.SQLException;

public class UsuarioController {
    protected Usuario usuario;
    protected UsuarioDAO usuarioDAO;

    public UsuarioController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario validarUsuario(String nombreUsuario) throws SQLException {
        return usuario = usuarioDAO.validarUsuario(nombreUsuario);
    }

}
