package swingIASlots; // Define el paquete al que pertenece esta clase

/**
 * Excepción personalizada utilizada en todo el proyecto Slots.
 *
 * Extiende Exception (excepción COMPROBADA / checked), lo que obliga a que
 * cualquier método que la lance la declare con "throws MyException"
 * o la capture con un bloque try-catch.
 *
 * Se usa en Frame1 para dos situaciones de validación:
 *   1. El usuario intenta guardar un nombre vacío o con caracteres inválidos.
 *   2. El usuario pulsa "Start" sin haber guardado previamente un nombre válido.
 */
public class MyException extends Exception {

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     *
     * @param message Texto que describe la causa de la excepción.
     *                Recuperable después con getMessage().
     */
    public MyException(String message) {
        // Llama al constructor de la clase padre (Exception) para que
        // almacene internamente el mensaje de error.
        super(message);
    }
}