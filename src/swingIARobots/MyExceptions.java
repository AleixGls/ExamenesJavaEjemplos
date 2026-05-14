package swingIARobots; // Define el paquete al que pertenece esta clase

/**
 * Excepción personalizada genérica utilizada en el Exercise 1 (ventanas Swing).
 *
 * Extiende Exception (excepción COMPROBADA / checked), lo que significa que
 * cualquier método que la lance debe declararla con "throws MyExceptions"
 * o capturarla con un bloque try-catch.
 *
 * Se usa principalmente en FirstWindow para validar los nombres de usuario
 * introducidos por el usuario.
 */
public class MyExceptions extends Exception {

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     *
     * @param message Texto que describe la causa de la excepción.
     *                Se puede recuperar después con getMessage().
     */
    public MyExceptions(String message) {
        // Llama al constructor de la clase padre (Exception)
        // para que almacene internamente el mensaje de error.
        super(message);
    }
}