package Vista;

import ModeloController.VistaController;

import javax.swing.*;
import java.awt.event.*;

public class ActualizarEquipo extends JDialog {
    private JPanel pPrincipal;
    private JTextField tfNombreNuevo;
    private JTextField tfFechaFundNueva;
    private JButton bAceptar;
    private JButton bCancelar;

    public ActualizarEquipo(VistaController vistaController) {
        setTitle("Actualizar Equipo");
        setContentPane(pPrincipal);
        setModal(true);
        setSize(450,550);
        setLocationRelativeTo(pPrincipal.getRootPane());
        setResizable(false); //para que sea de posicion y tamaño fijo

        bCancelar.addActionListener(_ -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });


        tfNombreNuevo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (vistaController.validarEquipo(tfNombreNuevo.getText())){
                        vistaController.rellenarCamposEquipoUpdate(pPrincipal);

                    }else {
                        throw new Exception("No existe ese equipo en la Base de datos");
                    }
                } catch (Exception ex) {
                    tfNombreNuevo.setText(""); tfFechaFundNueva.setText("");
                    JOptionPane.showMessageDialog(pPrincipal,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bAceptar.addActionListener(_ -> {
            try {
                onOk();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pPrincipal,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    /**Utilizo ifs para ver si se ha modificado fecha,nombre o ambos, para hacer la programacion mas simle y dividirla en funciones mas pequñas*/
    private void onOk() {

        //aqui se manda a vista, modelo... y update del equpo
        /*if(tfNombreNuevo.getText().equals(vistaController.getEquipo().getNombre())){
            if (vistaController.actualizarEquipoFecha(tfNombreNuevo.getText(),tfFechaFundNueva.getText())){
                JOptionPane.showMessageDialog(pPrincipal,"Datos actualizados correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(tfFechaFundNueva.getText().equals(vistaController.getEquipo().getFechaFundacion().toString())){
            if (vistaController.actualizarEquipoNombre(tfNombreNuevo.getText(),tfFechaFundNueva.getText())){
                JOptionPane.showMessageDialog(null,vistaController.getEquipo().getNombre(),"Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
        }else {
            if (vistaController.actualizarEQ(tfNombreNuevo.getText())){

            }

         }
         */
        dispose();

}

    public JTextField getTfNombreNuevo() {
        return tfNombreNuevo;
    }

    public JTextField getTfFechaFundNueva() {
        return tfFechaFundNueva;
    }
}
