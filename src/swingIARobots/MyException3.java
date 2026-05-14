package swingIARobots; // Define el paquete al que pertenece esta clase

/**
 * Excepción personalizada número 3, usada en el Exercise 2 (consola con Scanner).
 *
 * Se lanza cuando el número introducido es MAYOR QUE 10 (> 10).
 * Extiende Exception (checked).
 *
 * FLUJO DE PROPAGACIÓN:
 *   method3() la lanza  →  method2() la CAPTURA y muestra por consola.
 *   No llega a method1() ni a main().
 */
public class MyException3 extends Exception {

    /**
     * Constructor que recibe el mensaje de error.
     *
     * @param message Descripción del error (p.ej. "Number bigger than 10").
     */
    public MyException3(String message) {
        // Delega el almacenamiento del mensaje al constructor de Exception.
        super(message);
    }
}