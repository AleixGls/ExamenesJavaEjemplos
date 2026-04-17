package bbddIAChessClub;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // ── Paràmetres de connexió ──────────────────────────────────────
        String url            = "jdbc:mysql://localhost/ChessClub?serverTimezone=UTC";
        String user           = "root";   // <-- canvia al teu usuari
        String password       = "1234";  // <-- canvia al teu password
        String driverClassName = "com.mysql.cj.jdbc.Driver";

        Connection conn = null;
        Statement stm   = null;

        try {
            // ══════════════════════════════════════════════════════════════
            // a) Connexió amb la base de dades ChessClub
            // ══════════════════════════════════════════════════════════════
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("a) Connexió establerta correctament.\n");

            // ══════════════════════════════════════════════════════════════
            // b) Inserir registres a la taula countries amb Statement
            // ══════════════════════════════════════════════════════════════
            stm = conn.createStatement();

            stm.executeUpdate("INSERT INTO countries (id, countryName) VALUES (177, 'Finland')");
            stm.executeUpdate("INSERT INTO countries (id, countryName) VALUES (241, 'Portugal')");
            stm.executeUpdate("INSERT INTO countries (id, countryName) VALUES (352, 'United Kingdom')");
            stm.executeUpdate("INSERT INTO countries (id, countryName) VALUES (128, 'Spain')");
            stm.executeUpdate("INSERT INTO countries (id, countryName) VALUES (265, 'France')");
            System.out.println("b) Registres inserits a countries correctament.\n");

            // ══════════════════════════════════════════════════════════════
            // c) Crear ArrayList de Players (mínim 5) i inserir a la BBDD
            //    amb Statement
            // ══════════════════════════════════════════════════════════════
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player(125, "22222222A", "Sonia",  2300,  177));
            players.add(new Player(138, "33333333B", "Alba",  47000,  352));
            players.add(new Player(152, "44444444R", "Amaya",  7500,  128));
            players.add(new Player(241, "55555555T", "Nando", 14000,  265));
            players.add(new Player(312, "66666666S", "Andrea",25000,  241));

            for (Player p : players) {
                String sql = "INSERT INTO players (id, dni, playerName, points, idCountry) VALUES ("
                        + p.getId() + ", '"
                        + p.getDni() + "', '"
                        + p.getName() + "', "
                        + p.getPoints() + ", "
                        + p.getIdPais() + ")";
                stm.executeUpdate(sql);
            }
            System.out.println("c) " + players.size() + " players inserits a la BBDD.\n");

            // ══════════════════════════════════════════════════════════════
            // d) Consultar tots els registres usant ResultSetMetaData
            //    per obtenir els noms de les columnes
            // ══════════════════════════════════════════════════════════════
            System.out.println("d) Tots els registres de la taula players:");
            ResultSet rsAll = stm.executeQuery("SELECT * FROM players");
            ResultSetMetaData meta = rsAll.getMetaData();
            int numCols = meta.getColumnCount();

            while (rsAll.next()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= numCols; i++) {
                    if (i > 1) sb.append(", ");
                    sb.append(meta.getColumnName(i))
                      .append(" = ")
                      .append(rsAll.getString(i));
                }
                System.out.println(sb.toString());
            }
            System.out.println();

            // ══════════════════════════════════════════════════════════════
            // e) Sense nova consulta, mostrar el nom dels jugadors amb id > 130
            //    reutilitzant el ResultSet anterior
            // ══════════════════════════════════════════════════════════════
            System.out.println("e) Noms dels jugadors amb id > 130 (ResultSet anterior):");

            // Tornem al principi del ResultSet (requereix TYPE_SCROLL_INSENSITIVE)
            // Nota: com stm és un Statement normal, cal recrear-lo com scrollable
            Statement stmScroll = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rsScroll = stmScroll.executeQuery("SELECT * FROM players");

            rsScroll.beforeFirst();
            while (rsScroll.next()) {
                if (rsScroll.getInt("id") > 130) {
                    System.out.println("playerName = " + rsScroll.getString("playerName"));
                }
            }
            System.out.println();

            // ══════════════════════════════════════════════════════════════
            // f) Afegir dos nous jugadors amb PreparedStatement
            //    (es reseteja l'ArrayList primer)
            // ══════════════════════════════════════════════════════════════
            players.clear();
            players.add(new Player(400, "77777777X", "Magnus",  32000, 177));  // Finland
            players.add(new Player(450, "88888888Z", "Fabiano", 29000, 241));  // Portugal

            PreparedStatement pstm = conn.prepareStatement(
                "INSERT INTO players (id, dni, playerName, points, idCountry) VALUES (?, ?, ?, ?, ?)");

            for (Player p : players) {
                pstm.setInt(1, p.getId());
                pstm.setString(2, p.getDni());
                pstm.setString(3, p.getName());
                pstm.setInt(4, p.getPoints());
                pstm.setInt(5, p.getIdPais());
                pstm.executeUpdate();
            }
            System.out.println("f) Dos nous jugadors inserits amb PreparedStatement.\n");
            pstm.close();

            // ══════════════════════════════════════════════════════════════
            // g) PreparedStatement: mostrar jugadors amb > 10000 punts
            //    i que siguin de Portugal (id=241)
            //    ResultSet SCROLLABLE i UPDATABLE per als apartats h) i i)
            // ══════════════════════════════════════════════════════════════
            System.out.println("g) Jugadors amb > 10000 punts i de Portugal:");
            PreparedStatement pstmG = conn.prepareStatement(
                "SELECT * FROM players WHERE points > ? AND idCountry = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
            pstmG.setInt(1, 10000);
            pstmG.setInt(2, 241);   // Portugal

            ResultSet rsG = pstmG.executeQuery();
            while (rsG.next()) {
                System.out.println("id=" + rsG.getInt("id")
                        + ", playerName=" + rsG.getString("playerName")
                        + ", points=" + rsG.getInt("points")
                        + ", idCountry=" + rsG.getInt("idCountry"));
            }
            System.out.println();

            // ══════════════════════════════════════════════════════════════
            // h) Inserir un nou registre al ResultSet de g) que també
            //    quedi inserit a la base de dades
            // ══════════════════════════════════════════════════════════════
            rsG.moveToInsertRow();
            rsG.updateInt("id", 500);
            rsG.updateString("dni", "99999999P");
            rsG.updateString("playerName", "Hikaru");
            rsG.updateInt("points", 31000);
            rsG.updateInt("idCountry", 241);  // Portugal
            rsG.insertRow();
            rsG.moveToCurrentRow();
            System.out.println("h) Nou jugador 'Hikaru' inserit via ResultSet (i a la BBDD).\n");

            // ══════════════════════════════════════════════════════════════
            // i) Reutilitzant el ResultSet de g), modificar tots els jugadors
            //    amb > 10000 punts i establir points = 10000.
            //    La modificació es reflecteix a la base de dades.
            // ══════════════════════════════════════════════════════════════
            System.out.println("i) Actualitzant jugadors amb > 10000 punts -> points = 10000:");
            rsG.beforeFirst();
            while (rsG.next()) {
                if (rsG.getInt("points") > 10000) {
                    System.out.println("  Actualitzant: " + rsG.getString("playerName")
                            + " (" + rsG.getInt("points") + " -> 10000)");
                    rsG.updateInt("points", 10000);
                    rsG.updateRow();   // propaga el canvi a la BBDD
                }
            }
            System.out.println("i) Actualització completada.\n");

            rsG.close();
            pstmG.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Driver no trobat: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stm  != null) stm.close();
                if (conn != null) conn.close();
                System.out.println("Connexió tancada.");
            } catch (SQLException e) {
                System.err.println("Error tancant connexió: " + e.getMessage());
            }
        }
    }
}