package Vista;

import ModeloController.VistaController;

import javax.swing.*;

public class CrearJugador extends  JDialog{
    private JTextField tfNombreJugador;
    private JTextField tfApellidoJugador;
    private JButton bAceptar;
    private JButton bCancelar;
    private JTextField tfFechaNaci;
    private JTextField tfSueldo;
    private JTextField tfRol;
    private JLabel lNombreJugador;
    private JLabel lApellidoJugador;
    private JLabel lNacionalidad;
    private JLabel lFechaNaci;
    private JLabel lSueldo;
    private JLabel lRol;
    private JLabel lEquipo;
    private JPanel pPrincipal;
    private JComboBox cbEquiposDisp;
    private JComboBox cmNacionalidades;
    private VistaController vistaController;

    public CrearJugador(VistaController vistaController) {
        setContentPane(pPrincipal);
        setModal(true);
        setSize(450,550);
        setLocationRelativeTo(pPrincipal.getRootPane());
        setResizable(false); //para que sea de posicion y tamaño fijo
    }
}
