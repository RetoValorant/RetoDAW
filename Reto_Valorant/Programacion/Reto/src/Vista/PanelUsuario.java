package Vista;

import ModeloController.VistaController;

import javax.swing.*;

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
    public PanelUsuario(VistaController vistaController) {
        setTitle("Iniciar Sesión");
        setContentPane(pPrincipal);
        bEmpezarComp.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
        setResizable(false); //para que sea de posicion y tamaño fijo

        this.vistaController = vistaController ;

        /**
         Todos los listeners MENOS el de Infromes seránmodales, ya qeu son mas 'volatiles' y tienen una informacion que se verá en otro sitio
         */

        //crear Listeners
        iCrearEquipo.addActionListener(e -> {
            vistaController.setCrearEquipo(vistaController);
        });
        iConsultarEquipo.addActionListener(e -> {
            //vistaController.setConsultarEquipo(this);
        });
        iActualizarEquipo.addActionListener(e -> {
            //vistaController.setActualizarEquipo(this);
        });
        iBorrarEquipo.addActionListener(e -> {
            //vistaController.setBorrarEquipo(this);
        });
        iCrearJugador.addActionListener(e -> {
            //vistaController.setCrearJugador(this);
        });
        iConsultarJugador.addActionListener(e -> {
            //vistaController.setConsultarJugador(this);
        });
        iActualizarJugador.addActionListener(e -> {
            //vistaController.setActualizarJugador(this);
        });
        iBorrarJugador.addActionListener(e -> {
            //vistaController.setBorrarJugador(this);
        });

        mInformes.addActionListener(e -> {
              //vistaController.setInformes(this);
        });


        bEmpezarComp.addActionListener(e -> {
            // Empezar competición
        });
    }
    /*
    public PanelUsuario(VistaController vistaController) {
        setTitle("Iniciar Sesión");
        setContentPane(pPrincipal);
        bEmpezarComp.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
        setResizable(false); //para que sea de posicion y tamaño fijo

        this.vistaController = vistaController ;
        //listeners pertinentes a la vista
        mInformes.addActionListener(e -> {
            // Generar informes);
        });
        getJMenuBar().setVisible(false);
    }

     */

}
