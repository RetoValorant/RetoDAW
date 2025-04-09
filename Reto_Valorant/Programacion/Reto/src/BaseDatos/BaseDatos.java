package BaseDatos;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {

    public BaseDatos(){}

    public static Connection abrirCon() throws SQLException{
        Connection c = null;
        try {
                Class.forName("oracle.jdbc.driver.OracleDriver");

                String url = "jdbc:oracle:thin:@172.20.225.114:1521:orcl";
                String user = "eqdaw03";
                String pass = "eqdaw03";

                c = DriverManager.getConnection(url, user, pass);
            }catch (Exception e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        return c;
    }
}
