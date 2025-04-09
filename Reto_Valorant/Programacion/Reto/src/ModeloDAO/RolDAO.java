package ModeloDAO;

import Modelo.Juego;
import Modelo.Rol;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

public class RolDAO {

    private final Juego juego;
    private static ArrayList<Rol> listaRoles = new ArrayList<>();
    protected Connection con;
    public RolDAO(Connection c) {
        this.con = c;
        this.juego = new Juego();
    }

    // 📌 Crear (Agregar roles a un juego)
    public void guardarRolesEnJuego(ArrayList<Rol> roles) {
        if (juego.getListaRoles() == null) {
            juego.setListaRoles(new ArrayList<>()); // Inicializa la lista si está vacía
        }
        juego.getListaRoles().addAll(roles);
        System.out.println("Roles guardados en el juego.");
    }

    // 📌 Leer (Obtener lista de roles)
    public ArrayList<Rol> obtenerRoles() {
        listaRoles = juego.getListaRoles();
        return listaRoles;
    }

    // 📌 Actualizar (Modificar un rol por su código)
    public boolean actualizarRol(int codRol, String nuevoNombre) {
        for (Rol rol : juego.getListaRoles()) {
            if (rol.getCodRol() == codRol) {
                rol.setRol(nuevoNombre);
                System.out.println("Rol actualizado correctamente.");
                return true;
            }
        }
        System.out.println("Rol no encontrado.");
        return false;
    }

    // 📌 Eliminar (Eliminar un rol por su código)
    public boolean eliminarRol(int codRol) {
        Iterator<Rol> iterador = juego.getListaRoles().iterator();
        while (iterador.hasNext()) {
            Rol rol = iterador.next();
            if (rol.getCodRol() == codRol) {
                iterador.remove();
                System.out.println("Rol eliminado correctamente.");
                return true;
            }
        }
        System.out.println("Rol no encontrado.");
        return false;
    }

    // 📌 Mostrar todos los roles
    public void mostrarRoles() {
        if (juego.getListaRoles().isEmpty()) {
            System.out.println("No hay roles asignados.");
        } else {
            System.out.println("Lista de Roles en el Juego:");
            for (Rol rol : juego.getListaRoles()) {
                System.out.println("Código: " + rol.getCodRol() + " - Nombre: " + rol.getRol());
            }
        }
    }
}
