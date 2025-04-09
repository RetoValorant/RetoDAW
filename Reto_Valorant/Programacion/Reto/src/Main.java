
import ModeloController.*;

public class Main {

    public static void main(String[] args) {
        ModeloController modeloController = new ModeloController();
        VistaController vistaController = new VistaController(modeloController);
        modeloController.setVistaController(vistaController);
    }
}