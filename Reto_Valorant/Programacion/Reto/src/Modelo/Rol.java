package Modelo;

public class Rol {

    private int codRol;
    private String rol;

    public Rol() {
    }

    public Rol(int codRol, String rol) {
        this.codRol = codRol;
        this.rol = rol;
    }

    public int getCodRol() {
        return codRol;
    }

    public void setCodRol(int codRol) {
        this.codRol = codRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
