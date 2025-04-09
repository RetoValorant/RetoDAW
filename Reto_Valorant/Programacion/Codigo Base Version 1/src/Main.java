import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static boolean okey=true;
    public static boolean dotry=true;
    public static StringBuilder playerString=new StringBuilder();
    public static StringBuilder groupString=new StringBuilder();
    public static int i=0;
    public static void main(String[] args) {
        do {
            System.out.println("¿Deseas añadir información al jugador o al equipo?");
            String option = sc.nextLine();
            switch (option.toLowerCase()) {
                case "jugador":
                    playerInfo();
                    okey=false;
                    break;
                case "equipo":
                    groupInfo();
                    okey=false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while(okey);
    }
    public static void playerInfo() {
        playerString.delete(0,playerString.length());
        playerName();
        nationality();
        bornDate();
        nickname();
        rol();
        salary();
        System.out.println(playerString);
    }
    public static void playerName() {
        java.util.regex.Pattern namePattern = java.util.regex.Pattern.compile("^[A-ZÁÉÍÓÚÑÄËÏÖÜ][a-záéíóúñäëïöü\\s]*$");
        do {
            System.out.println("Nombre del jugador: ");
            String name = sc.nextLine();
            java.util.regex.Matcher matcher = namePattern.matcher(name);
            if (matcher.matches()) {
                playerString.append("Nombre del jugador: ").append(name).append("\n");
                okey=false;
            }
            else {
                System.out.println("El nombre debe empezar por mayusculas y no puede contener numeros");
                okey = true;
            }
        }while(okey);
        do{
            System.out.println("Apellido del jugador: ");
            String lastName = sc.nextLine();
            java.util.regex.Matcher matcher = namePattern.matcher(lastName);
            if (matcher.matches()) {
                playerString.append("Apellido del jugador: ").append(lastName).append("\n");
                okey=false;
            }
            else {
                System.out.println("El apellido debe empezar por mayusculas y no puede contener numeros");
                okey = true;
            }
        }while(okey);
    }
    public static void nationality(){
        new Locale("es", "ES");
        do{
            boolean nationalityNotValid = true;
            System.out.println("En que país nació el jugador: ");
            String nationality = sc.nextLine();
            for (Locale locale : Locale.getAvailableLocales())
            {
                if (nationality.equals(locale.getDisplayCountry())) {
                    playerString.append("Nacionalidad del jugador: ").append(nationality).append("\n");
                    nationalityNotValid = false;
                    okey = false;
                    break;
                }
                else {
                    okey = true;
                }
            }
            if (nationalityNotValid){
                System.out.println("La nacionalidad del jugador no es valida");
            }
        }while(okey);
    }
    public static void bornDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate oldEnough = LocalDate.now().minusYears(18);
        LocalDate tooOld = LocalDate.now().minusYears(65);
        dotry=true;
        do {
            try {
                do {
                    System.out.println("Fecha de nacimiento del jugador: ");
                    LocalDate bornDate = LocalDate.parse(sc.nextLine(), formatter);
                    if (bornDate.isBefore(oldEnough) && bornDate.isAfter(tooOld)) {
                        playerString.append("Fecha de nacimiento del jugador: ").append(bornDate).append("\n");
                        okey = false;
                    } else {
                        System.out.println("La fecha debe estar en formato \"DD/MM/YYYY\" y el jugador no puede tener mas de 65 años ni menos de 18 años");
                        okey = true;
                    }
                } while (okey);
                dotry=false;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha debe estar escrita en el formato \"DD/MM/YYYY\"");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error, " + e.getMessage() + " vuelva a poner la fecha ");
            }
        }while(dotry);
    }
    public static void nickname(){
        do{
            java.util.regex.Pattern nicknamePattern = java.util.regex.Pattern.compile("\\S{3,16}");
            System.out.println("Nickname del jugador: ");
            String nickname = sc.nextLine();
            java.util.regex.Matcher matcher = nicknamePattern.matcher(nickname);
            if (matcher.matches()) {
                playerString.append("Nickname del jugador: ").append(nickname).append("\n");
                if (groupString!=null){
                    groupString.append("Nickname del jugador ").append(i).append(": ").append(nickname).append("\n");
                }
                okey=false;
            }
            else {
                System.out.println("El nickname debe tener entre 3 y 16 caracteres y no puede tener espacios en blanco.");
                okey = true;
            }
        }while(okey);
    }
    public static void rol(){
        String rol;
        do{
            System.out.println("Rol del jugador: ");
            rol = sc.nextLine();
            switch (rol.toLowerCase()){
                case "centinela":
                    okey=false;
                    break;
                case "iniciador":
                    okey=false;
                    break;
                case "controlador":
                    okey=false;
                    break;
                case "duelista":
                    okey=false;
                    break;
                default:
                    okey=true;
                    System.out.println("Opcion no valida, el rol debe ser centinela, iniciador, controlador o duelista.");
                    break;
            }
        }while(okey);
        playerString.append("Rol del jugador: ").append(rol.substring(0, 1).toUpperCase()).append(rol.substring(1)).append("\n");
    }
    public static void salary(){
        final double SMI = 1134;
        dotry=true;
        do {
            try {
                do {
                    System.out.println("Sueldo del jugador: ");
                    double salary = sc.nextDouble();
                    if (salary > SMI) {
                        playerString.append("Sueldo del jugador: ").append(salary).append("\n");
                        okey = false;
                    } else {
                        System.out.println("El sueldo debe ser mayor al SMI : " + SMI);
                        okey = true;
                    }
                } while (okey);
                dotry=false;
            } catch (InputMismatchException e) {
                System.out.println("Debes poner un numero real.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error, " + e.getMessage());
            }
        }while(dotry);
    }
    public static void groupInfo() {
        groupName();
        groupFundation();
        groupsPlayers();
        System.out.println(groupString);
    }
    public static void groupName() {
        do {
            System.out.println("Nombre del equipo: ");
            String group = sc.nextLine();
            java.util.regex.Pattern groupPattern = java.util.regex.Pattern.compile("^[A-Za-z0-9 _-]+$");
            java.util.regex.Matcher matcher = groupPattern.matcher(group);
            if (matcher.matches()) {
                groupString.append("Nombre del equipo: ").append(group).append("\n");
                okey=false;
            }
            else {
                System.out.println("El nombre solo puede contener carácteres especiales como '-' y '_'");
                okey = true;
            }
        }while(okey);
    }
    public static void groupFundation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate newest = LocalDate.now();
        LocalDate oldest = LocalDate.of(2020,6,2);
        do {
            try {
                do {
                    System.out.println("¿Cuando se fundó el equipo?");
                    LocalDate groupFundation = LocalDate.parse(sc.nextLine(),formatter);
                    if (groupFundation.isBefore(newest) && groupFundation.isAfter(oldest)){
                        groupString.append("Fecha de fundación del equipo: ").append(groupFundation).append("\n");
                        okey=false;
                    }
                    else {
                        System.out.println("La fecha debe tener el formato \"DD/MM/YYYY\" y el equipo no puede haber sido fundado antes de que el juego saliera: " + oldest);
                        okey = true;
                    }
                }while (okey);
                dotry=false;
            } catch (DateTimeParseException e) {
                System.out.println("La fecha debe estar escrita en el formato \"DD/MM/YYYY\"");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error, " + e.getMessage() + " vuelva a poner la fecha ");
            }
        }while(dotry);
    }
    public static void groupsPlayers() {
        String playerCounter;
        for (i=1;i<=6;i++){
            if (i!=1) {
                playerCounter = morePlayers();
                if (playerCounter.equalsIgnoreCase("no"))
                    break;
            }
            playerInfo();
        }
    }
    public static String morePlayers() {
        sc.nextLine();
        String playerCounter;
        do{
            System.out.println("¿Hay más jugadores?");
            playerCounter = sc.nextLine();
            if (playerCounter.equalsIgnoreCase("si") || playerCounter.equalsIgnoreCase("no")) {
                okey = false;
                if (playerCounter.equalsIgnoreCase("no") && i<=2) {
                    System.out.println("El equipo debe tener al menos 2 jugadores");
                    okey = true;
                }
            }
            else {
                System.out.println("No es valido, tienes que poner \"si\" o \"no\"");
                okey = true;
            }
        }while (okey);
        return playerCounter;
    }
}