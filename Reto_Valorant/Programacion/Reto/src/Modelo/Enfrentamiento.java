package Modelo;

import java.time.LocalTime;

public class Enfrentamiento {
    private int resultadosEq1;
    private int resultadosEq2;
    private LocalTime hora;
    private Jornada jornada;
    private Equipo equipo1;
    private Equipo equipo2;
    private int idEnfrentamiento;

    public Enfrentamiento(int resultadosEq1, int resultadosEq2, LocalTime hora, Jornada jornada, Equipo equipo1, Equipo equipo2,int idEnfrentamiento) {
        this.resultadosEq1 = resultadosEq1;
        this.resultadosEq2 = resultadosEq2;
        this.hora = hora;
        this.jornada = jornada;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.idEnfrentamiento = idEnfrentamiento;
    }

    public int getIdEnfrentamiento() {
        return idEnfrentamiento;
    }

    public void setIdEnfrentamiento(int idEnfrentamiento) {
        this.idEnfrentamiento = idEnfrentamiento;
    }

    public Enfrentamiento() {
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getResultadosEq1() {
        return resultadosEq1;
    }

    public void setResultadosEq1(int resultadosEq1) {
        this.resultadosEq1 = resultadosEq1;
    }

    public int getResultadosEq2() {
        return resultadosEq2;
    }

    public void setResultadosEq2(int resultadosEq2) {
        this.resultadosEq2 = resultadosEq2;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    @Override
    public String toString() {
        return  "\n Id del enfrentamiento: " + idEnfrentamiento +
                "\n Equipo 1: " + equipo1.getNombre() +
                "\n Equipo 2: " + equipo2.getNombre() +
                "\n Resultados Equipo 1: " + resultadosEq1 +
                "\n Resultados Equipo 2:" + resultadosEq2 +
                "\n Hora del enfrentamiento: " + hora +
                "\n Jornada: " + jornada.getNumJornada();
    }
}
