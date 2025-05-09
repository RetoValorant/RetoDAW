package ModeloController;

import Modelo.Equipo;
import Modelo.Jornada;
import ModeloDAO.CompeticionDAO;
import ModeloDAO.EquipoDAO;
import ModeloDAO.JornadaDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Equipo tres
 * @version (2.0)
 * @see Jornada
 * @see JornadaDAO
 */
public class JornadaController {
    private final JornadaDAO jornadaDAO;
    private final ModeloController modeloController;

    private static final int[] meses31 = {1,3,5,7,8,10,12};

    /**
     * Constructor 
     * @param jornadaDAO   
     * @param modeloController  
     */
    public JornadaController(JornadaDAO jornadaDAO, ModeloController modeloController) {
        this.jornadaDAO = jornadaDAO;
        this.modeloController = modeloController;
    }

    /**
     * Crea una jornada para cada equipo y Asigna una fecha aleatoria a cada jornada 
     * @throws SQLException Si ocurre un error 
     */
    public void crearJornada() throws SQLException{
        List<Equipo> equipos = modeloController.getEquipos();
        for (int i = 0; i < equipos.size(); i++){
            Jornada jornada = new Jornada();
                jornada.setFechaInicio(elegirFecha());
                jornada.setCompeticion(modeloController.getCompeticion());
            jornadaDAO.anadirJornada(jornada);
        }
    }

    
    /**
     * Elige una fecha aleatoria para asignar a una jornada.
     * @return Fecha seleccionada
     * @throws SQLException Si ocurre un error al obtener fechas
     */
    public LocalDate elegirFecha() throws SQLException{
        int mes;
        int dia;
        int year;
        try {
            LocalDate fecha = elegirDia();
            mes = fecha.getMonthValue();
            dia = fecha.getDayOfMonth();
            year = fecha.getYear();
        }catch (NullPointerException | NoSuchElementException e){
            mes = elegirMes();
            dia = elegirDiaInicial(mes);
            year = 2025;
        }
        return LocalDate.of(year,mes,dia);
    }

    /**
     * Selecciona aleatoriamente un mes del año 
     * @return Mes aleatorio
     */
    public int elegirMes(){
        Random random = new Random();
        return random.nextInt(11)+1;
    }

    /**
     * Selecciona aleatoriamente un día válido según el mes y tine en cuenta meses con 31 días, 30 días y 28
     * @param mes Mes del año
     * @return Día válido aleatorio, random
     */
    public int elegirDiaInicial(int mes){
        Random random = new Random();
        int randomDia = 0;
        if (mes == 2)
            randomDia = random.nextInt(27)+1;
        else{
            for (int i : meses31) {
                if (mes == i)
                    randomDia = random.nextInt(30)+1;
                else
                    randomDia = random.nextInt(29)+1;
            }
        }
        return randomDia;
    }

     /**
     * Calcula una fecha y lo ajusta al próximo domingo.
     * @return Fecha generada para la jornada 
     * @throws SQLException Si ocurre un error 
     */
    public LocalDate elegirDia() throws SQLException {
        Random random = new Random();
        int randomDia;
        randomDia = random.nextInt(7);
        DayOfWeek diaJornada = jornadaDAO.getJornadas().getLast().getFechaInicio().getDayOfWeek();
        int diasHastaDomingo = DayOfWeek.SUNDAY.getValue() - diaJornada.getValue();
        // Si el día actual no es domingo, suma los días necesarios
        if (diasHastaDomingo < 0) {
            diasHastaDomingo += 7;
        }
        return jornadaDAO.getJornadas().getLast().getFechaInicio().plusDays(diasHastaDomingo).plusDays(randomDia);
    }

    /**
     * Obtiene todas las jornadas registradas
     * @return jornadas
     * @throws SQLException Si ocurre un error 
     */
    public List<Jornada> getJornadas() throws SQLException{
        return jornadaDAO.getJornadas();
    }

    /**
     * Busca una jornada específica teniendo en cuenta su ID
     * @param id Identificador de la jornada
     * @return Jornada correspondiente al ID
     * @throws SQLException Si ocurre un error 
     */
    public Jornada getJornadaPorId(int id) throws SQLException{
        return jornadaDAO.getJornadaPorId(id);
    }
}
