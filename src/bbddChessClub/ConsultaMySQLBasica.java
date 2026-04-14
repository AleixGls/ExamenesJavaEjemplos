package bbddChessClub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaMySQLBasica {
    public static void main(String[] args){
        // Variables de conexion
        String usuari = "root";
        String clau = "D*rmil0n59**";
        String urlDades = "jdbc:mysql://localhost/farmville?serverTimezone=UTC";

        // Programa
        try {
            // Cargar Dirver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver carregat correctament");

            // Establecer conexion
            Connection conn = DriverManager.getConnection(urlDades, usuari, clau);
            System.out.println("conexió creada correctament");

            // Crear consulta
            String query = "SELECT * FROM construcciones WHERE precio > 400";

            // Intanciar objeto de la clase statement
            java.sql.Statement stmnt = conn.createStatement();

            // Ejecutar consulta
            ResultSet rs =  stmnt.executeQuery(query);

            // Mostrar consulta
            while (
                rs.next()
            ) {
                System.out.println(
                    "ID: " + rs.getInt("id") + 
                    ", Nombre: " + rs.getString("nombre") + 
                    ", Precio: " + rs.getDouble("precio")
                );
            }

        // Error de Driver
        } catch (ClassNotFoundException ex) {
            System.out.println("No trobat el Driver MySQL per JDBC.");

        // Error de SQL
        } catch (SQLException e) {
            System.out.println("Excepció del tipus SQL");
            e.printStackTrace();
        }
    }
}