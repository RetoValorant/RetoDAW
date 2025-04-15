package Vista;

import ModeloController.VistaController;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BorrarEquipo extends JDialog{
    private JPanel pPrincipal;
    private JButton bCancelar;
    private JButton bAceptar;
    private JTextField tfNombreEquipo;
    protected VistaController vistaController;
    public BorrarEquipo(VistaController vistaController) {
        setContentPane(pPrincipal);
        setModal(true);
        setSize(450,550);
        setLocationRelativeTo(pPrincipal.getRootPane());
        setResizable(false); //para que sea de posicion y tamaño fijo
        bAceptar.setEnabled(false);
        this.vistaController = vistaController;

        tfNombreEquipo.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (vistaController.validarEquipo(tfNombreEquipo.getText())) {
                        bAceptar.setEnabled(true);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pPrincipal,ex.getMessage());
                }
            }
        });

        bCancelar.addActionListener(_ -> onCancel());
        bAceptar.addActionListener(_ -> {
            try {
                onOk();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pPrincipal, ex.getMessage());
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    private void onOk() throws Exception {
        //metodo que devuelve bool?
        if (vistaController.borrarEquipo(tfNombreEquipo.getText())){
            JOptionPane.showMessageDialog(pPrincipal,"Equipo Borrado con exito");
        }
        dispose();
    }


}
