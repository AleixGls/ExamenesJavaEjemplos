package bbddIALocals;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ── Paràmetres de connexió ──────────────────────────────────────
        String password       = "1234";
        String user           = "root";
        String url            = "jdbc:mysql://localhost/ejercicios_examen?serverTimezone=UTC";
        String driverClassName = "com.mysql.cj.jdbc.Driver";

        // ── i. Crear ArrayList amb mínim 4 instàncies de Local ──────────
        ArrayList<Local> locals = new ArrayList<>();
        locals.add(new Local(1,  150,  100456.0f, "oficina"));
        locals.add(new Local(2,   75,  237456.0f, "traster"));
        locals.add(new Local(3,  100,  115656.0f, "local_comercial"));
        locals.add(new Local(4,   90,  300456.0f, "traster"));
        locals.add(new Local(11, 110,  150000.0f, "oficina"));
        locals.add(new Local(15, 150,   75000.0f, "local_comercial"));
        locals.add(new Local(23, 130,  220000.0f, "traster"));
        locals.add(new Local(45, 150,  200000.0f, "oficina"));

        // ── ii. Crear l'objecte ConnectionMysql i connectar ────────────
        ConnectionMysql cm = new ConnectionMysql(url, user, password, driverClassName);
        cm.connectar();

        // ── iii. Inserir els locals a la taula (apartat c) ─────────────
        System.out.println("\n=== Inserint locals a la BDD ===");
        cm.insertLocals(locals);

        // ── iv. Mostrar tots els locals (apartat e) ────────────────────
        System.out.println("\n=== Locals amb id > 10 (mostrarLocals) ===");
        cm.mostrarLocals();

        // ── v. Mostrar locals preu > 100000 i tipus "oficina" (apartat f)
        //       + actualitzar a local_comercial els de > 100 m² ──────────
        System.out.println("\n=== preuLocal: preu > 100000 i tipus 'oficina' ===");
        cm.preuLocal(100000.0f, "oficina");

        // ── vi. Comprovar que s'han actualitzat (apartat e) ───────────
        System.out.println("\n=== Verificació: locals amb id > 10 després de l'actualització ===");
        cm.mostrarLocals();

        // ── vii. Demanar id a eliminar i esborrar-lo (apartat g) ───────
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIntrodueix l'id del local a eliminar: ");
        int idEliminar = scanner.nextInt();
        cm.eliminarLocal(idEliminar);
        scanner.close();

        // ── viii. Comprovar que s'ha eliminat (apartat e) ─────────────
        System.out.println("\n=== Verificació: locals amb id > 10 després d'eliminar ===");
        cm.mostrarLocals();
    }
}