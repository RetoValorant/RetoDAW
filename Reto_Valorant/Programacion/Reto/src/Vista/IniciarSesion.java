package Vista;

import ModeloController.VistaController;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

public class IniciarSesion extends JFrame{
    private JPanel pPricipal;
    private JTextField tfUsuario;
    private JPasswordField tfPassword;
    private JButton aceptarButton;
    protected VistaController vistaController;
    public IniciarSesion(VistaController vistaController) {
        setTitle("Iniciar Sesión");
        setContentPane(pPricipal);
        tfPassword.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 220);
        setLocationRelativeTo(null);
        setResizable(false); //para que sea de posicion y tamaño fijo
        this.vistaController = vistaController;


        //crear Listeners
        tfUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e); //aqui hara el la petiocion a BD para saber si existe el usuario
                try {
                    if (vistaController.validarUsuario(tfUsuario.getText())){
                        tfPassword.setVisible(true);
                        if (vistaController.validarPassWord(tfPassword.getText())){
                            //se puede obtener el tipoUsuario para el contructor de PanelUsuario
                            PanelUsuario panelUsuario = new PanelUsuario(vistaController);
                            panelUsuario.setVisible(true);
                        }
                    }
                } catch (SQLException ex) { //falta configurar o lanzar a una clase EX propia
                    throw new RuntimeException(ex);
                }
            }
        });

    }



    public JPanel getpPricipal() {
        return pPricipal;
    }

    public JTextField getTfUsuario() {
        return tfUsuario;
    }

    public JTextField getTfPassword() {
        return tfPassword;
    }

    public JButton getAceptarButton() {
        return aceptarButton;
    }

    public VistaController getVistaController() {
        return vistaController;
    }
}
