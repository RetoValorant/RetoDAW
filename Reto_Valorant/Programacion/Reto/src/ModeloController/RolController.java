package ModeloController;

import Modelo.Rol;
import ModeloDAO.RolDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RolController {
    private final RolDAO rolDAO;

    public RolController(RolDAO rolDAO) {
        this.rolDAO = rolDAO;
    }

    public ArrayList<Rol> conseguirRoles() {

        ArrayList<Rol> listaRoles = new ArrayList<>();
        Rol nuevoRol = new Rol();
        try {
            listaRoles = rolDAO.obtenerRoles();
            if (listaRoles == null) {
                listaRoles = new ArrayList<>();
            }
            int siTieneRoles = JOptionPane.showConfirmDialog(null, "¿El juego tiene algun rol?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (siTieneRoles == JOptionPane.YES_OPTION){

                String rolIngresaUsuario;
                int masRoles;

                do {
                    nuevoRol.setCodRol(generarCodigoRol());
                    nuevoRol.setRol(this.validarNombreRol("Nombre", "Ingresa el nombre del Rol.", "^[A-ZÁÉÍÓÚÑÄËÏÖÜ][a-záéíóúñäëïöü\\s]*$"));
                    listaRoles.add(nuevoRol);

                    masRoles = JOptionPane.showConfirmDialog(null, "¿Hay algun otro rol?", "Confirmación", JOptionPane.YES_NO_OPTION);

                }while (masRoles == JOptionPane.YES_OPTION);

            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "No se admite ese numero " +e.getMessage() +"\n");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "La opcion es nula, aconsejamos crear antes para despues modificar\n");
        }

        return listaRoles;
    }
    private int generarCodigoRol() {
        int codRol = 0;
        try {
            Set<Integer> codigosRol = rolDAO.obtenerRoles()
                    .stream().map(Rol::getCodRol)
                    .collect(Collectors.toSet());
            while (codigosRol.contains(codRol)) {
                codRol++;
                //hasta que no encuentra un nuevo codigo no sale del loop
            }
        }catch (NullPointerException e){
            codRol = 1;
        }

        return codRol;
    }
    private String validarNombreRol(String dato,String mensaje,String patron){
        boolean isValid = false;
        Pattern pattern = Pattern.compile(patron);
        String var="";
        do {
            try {
                var = JOptionPane.showInputDialog(null,mensaje);
                Matcher matcher = pattern.matcher(var);

                if (matcher.matches()) {
                    isValid = true;
                }else {
                    System.out.println(dato + " no utiliza un formato valido");
                }

            }catch (NullPointerException e){
                System.out.println("No se puede ingresar el " + dato + " vacio");
            }
        }while (!isValid);
        return var;
    }

}
