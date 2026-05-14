package swingIARobots; // Define el paquete al que pertenece esta clase

/**
 * Excepción personalizada número 2, usada en el Exercise 2 (consola con Scanner).
 *
 * Se lanza cuando el número introducido por el usuario es NEGATIVO (< 0).
 * Extiende Exception (checked).
 *
 * FLUJO DE PROPAGACIÓN:
 *   method3() la lanza  →  method2() NO la captura (la propaga)
 *   →  method1() NO la captura (la propaga)  →  main() la captura y muestra.
 */
public class MyException2 extends Exception {

    /**
     * Constructor que recibe el mensaje de error.
     *
     * @param message Descripción del error (p.ej. "Not a positive number").
     */
    public MyException2(String message) {
        // Delega el almacenamiento del mensaje al constructor de Exception.
        super(message);
    }
}
