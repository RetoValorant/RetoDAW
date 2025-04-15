package Vista;

import ModeloController.VistaController;

import javax.swing.*;
import java.sql.SQLException;

public class PanelUsuario extends JFrame{
    private JPanel pPrincipal;
    private JButton bEmpezarComp;
    private JMenuItem iCrearEquipo;
    private JMenuItem iConsultarEquipo;
    private JMenuItem iActualizarEquipo;
    private JMenuItem iBorrarEquipo;
    private JMenuItem iCrearJugador;
    private JMenuItem iConsultarJugador;
    private JMenuItem iActualizarJugador;
    private JMenuItem iBorrarJugador;
    private JMenu mInformes;
    protected VistaController vistaController;

    //constructor ADMIN
    /**
     Todos los listeners MENOS el de Infromes seránmodales, ya qeu son mas 'volatiles' y tienen una informacion que se verá en otro sitio
     */
    public PanelUsuario(VistaController vistaController) {
        setTitle("Iniciar Sesión");
        setContentPane(pPrincipal);
        bEmpezarComp.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);
        setResizable(false); //para que sea de posicion y tamaño fijo

        this.vistaController = vistaController ;

        //crear Listeners
        iCrearEquipo.addActionListener(_ -> vistaController.setCrearEquipo(vistaController));
        iConsultarEquipo.addActionListener(_ -> vistaController.setConsultarEquipo(vistaController));
        iActualizarEquipo.addActionListener(_ -> vistaController.setActualizarEquipo(vistaController));
        iBorrarEquipo.addActionListener(_ -> vistaController.setBorrarEquipo(vistaController));
        iCrearJugador.addActionListener(_ -> {
            try {
                vistaController.setCrearJugador(vistaController);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        iConsultarJugador.addActionListener(_ -> vistaController.setConsultarJugador(vistaController));
        iActualizarJugador.addActionListener(_ -> vistaController.setActualizarJugador(vistaController));

        /*
        iBorrarJugador.addActionListener(_ -> //vistaController.setBorrarJugador(vistaController));

        mInformes.addActionListener(_ -> //vistaController.setInformes(vistaController));

        bEmpezarComp.addActionListener(_ -> {}// Empezar competición);
        */

        }

}
