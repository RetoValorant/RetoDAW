package Vista;

import Modelo.Equipo;
import ModeloController.VistaController;
import Nacionalidades.Country;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.Arrays;

public class CrearJugador extends  JDialog{
    private JTextField tfNombreJugador;
    private JTextField tfApellidoJugador;
    private JButton bAceptar;
    private JButton bCancelar;
    private JTextField tfFechaNaci;
    private JTextField tfSueldo;
    private JTextField tfRol;
    private JPanel pPrincipal;
    private JComboBox cbPaises;
    private JComboBox cbEquiposDisp;
    private JTextField tfNickName;
    private final VistaController vistaController;

    /**
     en el bAcepat, obtengo el codigo OSI del pais seleccionaldo de la ComboBox, para que se siga un Standar(GER,ESP,etc...)
     */
    public CrearJugador(VistaController vistaController) throws SQLException {
        this.vistaController = vistaController;
        nacionalidades();
        equiposDisp();

        setContentPane(pPrincipal);
        setModal(true);
        setSize(450,550);
        setLocationRelativeTo(pPrincipal.getRootPane());
        setResizable(false); //para que sea de posicion y tamaño fijo
        //listeners
        tfNombreJugador.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (vistaController.validarNomYAp(tfNombreJugador.getText())) {
                        tfNombreJugador.requestFocus();
                        JOptionPane.showMessageDialog(pPrincipal,"El campo debe seguir un formato correcto(2 letras como minimo 20max.");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });

        tfApellidoJugador.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (vistaController.validarNomYAp(tfApellidoJugador.getText())) {
                        tfApellidoJugador.requestFocus();
                        JOptionPane.showMessageDialog(pPrincipal,"El campo debe seguir un formato correcto(2 letras como minimo 20max.");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });

        tfFechaNaci.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (vistaController.validarFechaNac(tfFechaNaci.getText())) {
                        tfFechaNaci.requestFocus();
                        JOptionPane.showMessageDialog(pPrincipal,"La el jugador debe tener entre 16 y 65 años");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        tfSueldo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarSueldo(tfSueldo.getText())) {
                        tfSueldo.requestFocus();
                        JOptionPane.showMessageDialog(pPrincipal,"El sueldo del jugador debe ser como minimo 1184€.");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        tfNickName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try {
                    if (!vistaController.validarNik(tfSueldo.getText())) {
                        tfNickName.requestFocus();
                        JOptionPane.showMessageDialog(pPrincipal,"NickName no soportado");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });

        bAceptar.addActionListener(_ -> {
            try {
                if (vistaController.crearJugador(
                        tfNombreJugador.getText(),
                        tfApellidoJugador.getText(),
                        obtenerCod3(),
                        tfFechaNaci.getText(),
                        tfSueldo.getText(),
                        tfRol.getText(),
                        tfNickName.getText(),
                        obtenerCodEqipo())){
                    JOptionPane.showMessageDialog(pPrincipal,"El jugador ha creado con exito");
                }else
                    throw new Exception();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(pPrincipal,"ERROR: "+ex.getMessage());
            }
        });
        bCancelar.addActionListener(_-> dispose());
    }

    private void nacionalidades(){
        //cbPaises.addItem(Country.values()); ¿? porbar
        for (Country c : Country.values()) cbPaises.addItem(c.getName());
    }
    private void equiposDisp() throws SQLException {
        for (Equipo eq : vistaController.getEquipos()) cbEquiposDisp.addItem(eq.getNombre());
    }

    private String obtenerCod3() {
        Object selectedItem = cbPaises.getSelectedItem();
        if (selectedItem == null) return "No seleccionado";

        String nombrePais = selectedItem.toString();
        return Arrays.stream(Country.values())
                .filter(e -> e.getName().equalsIgnoreCase(nombrePais))
                .map(Country::getThreeDigitsCode)
                .findFirst()
                .orElse("No encontrado");
    }

    /**
     * No hace falta el Object.requireNonNull ya que el stream si no devuelve null, a lo que el sql lanza excepcion si algo va mal
     * */
    private int obtenerCodEqipo() throws SQLException {
        Equipo eq = vistaController.getEquipos().stream()
                .filter(e -> e.getNombre().equals(cbEquiposDisp.getSelectedItem()))
                .findFirst()
                .orElse(null);

        return eq.getCodEquipo();
    }
}

