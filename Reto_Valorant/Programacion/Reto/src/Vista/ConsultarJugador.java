package Vista;

import ModeloController.VistaController;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ConsultarJugador extends JDialog {
    private JPanel pPrincipal;
    private JButton bAceptar;
    private JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfNacionalidad;
    private JTextField tfFechaNaci;
    private JTextField tfSueldo;
    private JTextField tfRol;
    private JTextField tfEquipo;
    private JTextField tfNickName;

    public ConsultarJugador(VistaController vistaController) {
        setContentPane(pPrincipal);
        setModal(true);
        setSize(450,550);
        setLocationRelativeTo(pPrincipal.getRootPane());
        setResizable(false);
        //para que sea de posicion y tamaño fijo

        bAceptar.addActionListener(_ -> dispose());

        tfNickName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (vistaController.validarJugador(tfNickName.getText())){
                        //rellenar los demas campo
                        vistaController.rellenarCamposJugador(pPrincipal);
                    }else {
                        JOptionPane.showMessageDialog(pPrincipal,"No hay jugadores con ese nik");
                        tfApellido.setText("");tfNombre.setText("");
                        tfNacionalidad.setText(""); tfFechaNaci.setText("");
                        tfSueldo.setText(""); tfRol.setText("");
                        tfEquipo.setText("");
                        tfNickName.requestFocus();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pPrincipal,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JTextField getTfNombre() {
        return tfNombre;
    }
    public JTextField getTfApellido() {
        return tfApellido;
    }
    public JTextField getTfNacionalidad() {
        return tfNacionalidad;
    }
    public JTextField getTfFechaNaci() {
        return tfFechaNaci;
    }
    public JTextField getTfSueldo() {
        return tfSueldo;
    }
    public JTextField getTfRol() {
        return tfRol;
    }
    public JTextField getTfEquipo() {
        return tfEquipo;
    }

}
