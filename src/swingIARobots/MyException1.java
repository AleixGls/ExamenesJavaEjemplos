package swingIARobots; // Define el paquete al que pertenece esta clase

/**
 * Excepción personalizada número 1, usada en el Exercise 2 (consola con Scanner).
 *
 * Se lanza cuando el usuario introduce un valor que NO es un número entero.
 * Extiende Exception (checked), así que obliga a quien la use a declarar
 * "throws MyException1" o a capturarla.
 *
 * IMPORTANTE: en el flujo del Exercise 2, MyException1 se captura y gestiona
 * DENTRO del propio método3(), por lo que NUNCA llega a propagarse hacia arriba.
 */
public class MyException1 extends Exception {

    /**
     * Constructor que recibe el mensaje de error.
     *
     * @param message Descripción del error (p.ej. "Not a Number").
     */
    public MyException1(String message) {
        // Delega el almacenamiento del mensaje al constructor de Exception.
        super(message);
    }
}