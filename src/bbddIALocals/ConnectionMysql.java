package bbddIALocals;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionMysql {

    // Atributs de connexió
    private String url;
    private String password;
    private String user;
    private String driverClassName;

    // Atributs JDBC
    private Connection conn;
    private Statement stm;
    private PreparedStatement pstm;

    // ─────────────────────────────────────────────
    // a) Constructor
    // ─────────────────────────────────────────────
    public ConnectionMysql(String url, String user, String password, String driverClassName) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driverClassName = driverClassName;
    }

    // ─────────────────────────────────────────────
    // a) Mètode connectar
    // ─────────────────────────────────────────────
    public void connectar() {
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexió establerta correctament.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no trobat: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de connexió SQL: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // b) Mètode getValues — executa una SELECT amb Statement
    // ─────────────────────────────────────────────
    public ResultSet getValues(String query) {
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error a getValues: " + e.getMessage());
        }
        return rs;
    }

    // ─────────────────────────────────────────────
    // c) Mètode insertLocals — insereix un ArrayList de locals amb Statement
    // ─────────────────────────────────────────────
    public void insertLocals(ArrayList<Local> locals) {
        try {
            stm = conn.createStatement();
            for (Local l : locals) {
                String sql = "INSERT INTO Local (id, metres_quadrats, preu, tipus) VALUES ("
                        + l.getId() + ", "
                        + l.getMetres_quadrats() + ", "
                        + l.getPreu() + ", '"
                        + l.getTipus() + "')";
                stm.executeUpdate(sql);
            }
            System.out.println(locals.size() + " locals inserits correctament.");
        } catch (SQLException e) {
            System.err.println("Error a insertLocals: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // d) Mètode llistaLocals — mostra tots els locals en format llegible
    // ─────────────────────────────────────────────
    public void llistaLocals() {
        String sql = "SELECT * FROM Local";
        ResultSet rs = getValues(sql);
        int comptador = 1;
        try {
            while (rs != null && rs.next()) {
                System.out.println("LOCAL " + comptador + ":");
                System.out.println("ID:" + rs.getInt("id"));
                System.out.println("Metres quadrats:" + rs.getInt("metres_quadrats"));
                System.out.println("Preu:" + rs.getFloat("preu"));
                System.out.println("Tipus:" + rs.getString("tipus"));
                System.out.println("_".repeat(32));
                comptador++;
            }
        } catch (SQLException e) {
            System.err.println("Error a llistaLocals: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // e) Mètode mostrarLocals — mostra locals amb id > 10 usant ResultSetMetaData
    // ─────────────────────────────────────────────
    public void mostrarLocals() {
        String sql = "SELECT * FROM Local WHERE id > 10";
        ResultSet rs = getValues(sql);
        try {
            if (rs == null) return;

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumnes = metaData.getColumnCount();

            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= numColumnes; i++) {
                    String nomColumna = metaData.getColumnName(i);
                    String valor = rs.getString(i);
                    if (i > 1) sb.append(", ");
                    sb.append(nomColumna).append(" = ").append(valor);
                }
                System.out.println(sb.toString());
            }
        } catch (SQLException e) {
            System.err.println("Error a mostrarLocals: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // f) Mètode preuLocal — usa PreparedStatement (PARAMETRITZAT)
    //    Mostra locals amb preu > minPreu i del tipus indicat.
    //    Després actualitza a "local_comercial" els que tinguin > 100 m².
    // ─────────────────────────────────────────────
    public void preuLocal(float minPreu, String tipusOficina) {
        String sqlSelect = "SELECT * FROM Local WHERE preu > ? AND tipus = ?";
        try {
            pstm = conn.prepareStatement(
                    sqlSelect,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            pstm.setFloat(1, minPreu);
            pstm.setString(2, tipusOficina);

            ResultSet rs = pstm.executeQuery();

            System.out.println("Locals amb preu > " + minPreu + " i tipus = " + tipusOficina + ":");
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("id")
                        + " | Metres:" + rs.getInt("metres_quadrats")
                        + " | Preu:" + rs.getFloat("preu")
                        + " | Tipus:" + rs.getString("tipus"));
            }

            // Tornem al principi del ResultSet per fer l'actualització
            rs.beforeFirst();

            // Actualitzem els locals amb > 100 m² a "local_comercial" (BD + ResultSet)
            String sqlUpdate = "UPDATE Local SET tipus = 'local_comercial' WHERE metres_quadrats > 100";
            PreparedStatement pstmUpdate = conn.prepareStatement(sqlUpdate);
            int files = pstmUpdate.executeUpdate();
            System.out.println(files + " locals actualitzats a 'local_comercial' (metres_quadrats > 100).");
            pstmUpdate.close();

        } catch (SQLException e) {
            System.err.println("Error a preuLocal: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    // g) Mètode eliminarLocal — usa PreparedStatement (PARAMETRITZAT)
    // ─────────────────────────────────────────────
    public void eliminarLocal(int idEliminar) {
        String sql = "DELETE FROM Local WHERE id = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idEliminar);
            int files = pstm.executeUpdate();
            if (files > 0) {
                System.out.println("Local amb id=" + idEliminar + " eliminat correctament.");
            } else {
                System.out.println("No s'ha trobat cap local amb id=" + idEliminar + ".");
            }
        } catch (SQLException e) {
            System.err.println("Error a eliminarLocal: " + e.getMessage());
        }
    }
}
