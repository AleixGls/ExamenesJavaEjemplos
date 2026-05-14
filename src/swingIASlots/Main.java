package swingIASlots; // Define el paquete al que pertenece esta clase

/**
 * Clase principal del programa (Slots - máquina tragaperras con Swing).
 * Su única responsabilidad es arrancar la aplicación creando la ventana de login.
 */
public class Main {

    /**
     * Punto de entrada del programa.
     * La JVM llama automáticamente a este método al ejecutar la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        // Crea una nueva instancia de Frame1 (ventana de login).
        // El propio constructor de Frame1 llama a setVisible(true),
        // por lo que la ventana aparece en pantalla inmediatamente.
        new Frame1();
    }
}