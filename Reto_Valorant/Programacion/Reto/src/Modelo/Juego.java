package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Juego {

    private int codjuego;
    private String nombre;
    private LocalDate fechaSalida;
    private ArrayList<Rol> listaRoles;

    public Juego() {
    }

    public Juego(int codjuego, String nombre, LocalDate fechaSalida, ArrayList<Rol> listaRoles) {
        this.codjuego = codjuego;
        this.nombre = nombre;
        this.fechaSalida = fechaSalida;
        this.listaRoles = listaRoles;
    }

    public Juego(int codjuego, String nombre, LocalDate fechaSalida) {
        this.codjuego = codjuego;
        this.nombre = nombre;
        this.fechaSalida = fechaSalida;
    }

    public ArrayList<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(ArrayList<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public int getCodjuego() {
        return codjuego;
    }

    public void setCodjuego(int codjuego) {
        this.codjuego = codjuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: " + getNombre()+"\n" +
                "Codigo de equipo: " + getCodjuego() + "\n" +
                "Fecha de fundacion: " + getFechaSalida());
        if (listaRoles != null) {
            sb.append("\nRoles: ");
            for (Rol rol : listaRoles) {
                sb.append("\n" + rol.getRol() + ", ");
            }
        }

        return sb.toString();
    }
}
