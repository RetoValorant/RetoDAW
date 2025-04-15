package Vista;

import ModeloController.VistaController;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class BorrarJugador extends JDialog {
    private JPanel pPrincipal;
    private JTextField tfNickName;
    private JButton bAceptar;
    private JButton bAtras;
    private final VistaController vistaController;

    public BorrarJugador(VistaController vistaController) {
        setContentPane(pPrincipal);
        setModal(true);
        setSize(450,550);
        setLocationRelativeTo(pPrincipal.getRootPane());
        setResizable(false); //para que sea de posicion y tamaño fijo
        bAceptar.setEnabled(false);
        this.vistaController = vistaController;

        bAceptar.addActionListener(_ -> onOK());

        bAtras.addActionListener(_ -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        pPrincipal.registerKeyboardAction(_ -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        tfNickName.addFocusListener(new FocusAdapter() {
           @Override
           public void focusGained(FocusEvent e) {
               super.focusGained(e);
               try {
                   if (vistaController.validarJugador(tfNickName.getText())) {
                       bAceptar.setEnabled(true);
                   }else{
                       JOptionPane.showMessageDialog(pPrincipal,"No hay jugadores con ese NickName");
                   }
               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }
           }
           public void focusLost(FocusEvent e) {
               super.focusLost(e);

           }
        });
    }

    private void onOK() {
        try {
            if (vistaController.borrarJugador(tfNickName.getText().trim())){
                JOptionPane.showMessageDialog(pPrincipal,"Jugador Borrado");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(pPrincipal,e.getMessage());
        }

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
