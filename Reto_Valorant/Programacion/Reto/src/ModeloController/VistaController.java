package ModeloController;

import Vista.CrearEquipo;
import Vista.IniciarSesion;

import java.sql.SQLException;

public class VistaController {
    protected ModeloController modeloController;

    public VistaController(ModeloController modeloController) {
        this.modeloController = modeloController;
        setIniciarSesion();
    }
    public void setIniciarSesion() {
        IniciarSesion iniciarSesion = new IniciarSesion(this);
        iniciarSesion.setVisible(true);

    }

    public boolean validarUsuario(String nombreUsuario) throws SQLException {
        return modeloController.validarUsuario(nombreUsuario);
    }
    public boolean validarPassWord(String passWord) throws SQLException {
        return modeloController.validarPassWord(passWord);
    }
    public void setCrearEquipo(VistaController vistaController){
        CrearEquipo crearEquipo = new CrearEquipo(vistaController);
        crearEquipo.setVisible(true);
    }
    public boolean validarEquipo(String nombreEquipo, String fechaFund) throws Exception {
        //falta meter Patron aqui para el nombre y fecha
        return modeloController.validarEquipo(nombreEquipo, fechaFund);
    }

}
