package swingIARobots; // Define el paquete al que pertenece esta clase

/**
 * Clase principal del programa (Exercise 1 - Swing con imágenes de robots).
 * Su única responsabilidad es arrancar la aplicación creando la primera ventana.
 */
public class Main {

    /**
     * Punto de entrada del programa.
     * La JVM llama automáticamente a este método al ejecutar la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        // Crea una nueva instancia de FirstWindow.
        // El propio constructor de FirstWindow llama a setVisible(true),
        // por lo que la ventana aparece en pantalla inmediatamente.
        new FirstWindow();
    }
}