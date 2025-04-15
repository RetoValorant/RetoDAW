package Vista;

import ModeloController.VistaController;

import javax.swing.*;
import java.awt.event.*;

public class ConsultarEquipo extends JDialog {
    private JPanel pPrincipal;
    private JTextField tfNombreEquipo;
    private JTextField tfFechaFundacion;
    private JTextField tfPuntuacionTotal;
    private JTextField tfCodEquipo;

    private JButton bAtras;

    public ConsultarEquipo(VistaController vistaController) {
        setContentPane(pPrincipal);
        setModal(true);
        setSize(400,450);
        getRootPane().setDefaultButton(bAtras);
        setLocationRelativeTo(pPrincipal.getRootPane());
        setResizable(false); //para que sea de posicion y tamaño fijo

        tfCodEquipo.setEditable(false);
        tfFechaFundacion.setEditable(false);
        tfPuntuacionTotal.setEditable(false);

        bAtras.addActionListener(_ -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        pPrincipal.registerKeyboardAction(_ -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );

        tfNombreEquipo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (vistaController.validarEquipo(tfNombreEquipo.getText())){
                        //rellenar los demas campo
                        vistaController.rellenarCamposEquipo(pPrincipal);
                    }else {
                        tfNombreEquipo.setText(""); tfNombreEquipo.requestFocus();
                        tfCodEquipo.setText("");
                        tfFechaFundacion.setText(""); tfPuntuacionTotal.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pPrincipal,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public JTextField getTfNombreEquipo() {
        return tfNombreEquipo;
    }

    public JTextField getTfFechaFundacion() {
        return tfFechaFundacion;
    }

    public JTextField getTfPuntuacionTotal() {
        return tfPuntuacionTotal;
    }

    public JTextField getTfCodEquipo() {return tfCodEquipo;}
}
