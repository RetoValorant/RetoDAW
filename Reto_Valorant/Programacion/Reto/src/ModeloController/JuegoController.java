package ModeloController;

import ModeloDAO.JuegoDAO;

import java.time.LocalDate;

public class JuegoController {
    private JuegoDAO juegoDAO;
    private final LocalDate SALIDAPRIMERJUEGO = LocalDate.of(1972, 11, 29);

    public JuegoController(JuegoDAO juegoDAO) {
        //rolController = new RolController();
        this.juegoDAO = juegoDAO;
    }
}
