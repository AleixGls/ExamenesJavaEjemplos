package swingIARobots; // Define el paquete al que pertenece esta clase

import java.util.Scanner; // Permite leer datos introducidos por el usuario desde la consola

/**
 * Clase del Exercise 2: demuestra el manejo y propagación de excepciones personalizadas
 * a través de una cadena de llamadas a métodos.
 *
 * JERARQUÍA DE LLAMADAS:
 *   main() → method1() → method2() → method3()
 *
 * MAPA DE EXCEPCIONES:
 * ┌─────────────────┬──────────────┬────────────────────────────────────────┐
 * │ Excepción        │ Se lanza en  │ Se captura en                          │
 * ├─────────────────┼──────────────┼────────────────────────────────────────┤
 * │ MyException1    │ method3()    │ Dentro del propio method3() → NO sube  │
 * │ MyException2    │ method3()    │ main() (pasa por method2 y method1)    │
 * │ MyException3    │ method3()    │ method2()                              │
 * │ ArithmeticEx.   │ (externa)    │ method2() (nunca se lanza aquí)         │
 * └─────────────────┴──────────────┴────────────────────────────────────────┘
 */
public class MainExercise2 {

    // -----------------------------------------------------------------------
    // method3: lógica principal de entrada y validación del número
    // -----------------------------------------------------------------------

    /**
     * Pide al usuario un número por consola y aplica tres reglas de validación:
     *   1. El valor introducido debe ser un entero (si no → MyException1, capturada aquí).
     *   2. El número no puede ser negativo (si no → MyException2, propagada hacia arriba).
     *   3. El número no puede ser mayor que 10 (si no → MyException3, propagada hacia arriba).
     *
     * @throws MyException2 Si el número es negativo (< 0).
     *                      No se captura aquí: sube a method2() y luego a main().
     * @throws MyException3 Si el número es mayor que 10 (> 10).
     *                      No se captura aquí: sube a method2() donde se captura.
     */
    public void method3() throws MyException2, MyException3 {

        // Scanner vinculado a la entrada estándar (teclado)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: "); // Mensaje de solicitud al usuario

        try {
            // --- Comprobación 1: ¿el siguiente token es un entero? ---
            if (!scanner.hasNextInt()) {
                // Si NO es un entero, consume el token inválido para no bloquear el Scanner
                scanner.next();
                // Lanza MyException1 indicando que el input no es un número
                throw new MyException1("Not a Number");
            }

            // Lee el entero introducido por el usuario
            int number = scanner.nextInt();

            // --- Comprobación 2: ¿el número es negativo? ---
            if (number < 0) {
                // Lanza MyException2; NO se captura en este método → sube
                throw new MyException2("Not a positive number");
            }

            // --- Comprobación 3: ¿el número supera el límite de 10? ---
            if (number > 10) {
                // Lanza MyException3; NO se captura en este método → sube
                throw new MyException3("Number bigger than 10");
            }

            // Si llega aquí, el número es válido (entre 0 y 10 inclusive)
            System.out.println("Valid number: " + number);

        } catch (MyException1 e) {
            // MyException1 se captura y gestiona AQUÍ MISMO.
            // Muestra el mensaje de error por consola.
            // Al capturarse, NO se propaga hacia method2() ni más arriba.
            System.out.println(e.getMessage());
        }
        // NOTA: MyException2 y MyException3 NO tienen catch aquí,
        // por lo que si se lanzan, atraviesan este método y suben al llamador (method2).
    }

    // -----------------------------------------------------------------------
    // method2: intermediario que captura MyException3 y ArithmeticException
    // -----------------------------------------------------------------------

    /**
     * Llama a method3() y gestiona dos tipos de excepción específicos:
     *   - ArithmeticException (excepción de Java estándar, p.ej. división por cero).
     *   - MyException3 (número mayor que 10).
     *
     * MyException2 NO se captura aquí → se propaga hacia method1().
     *
     * @throws MyException2 Si method3() lanzó MyException2 (número negativo).
     *                      Este método la deja pasar sin capturarla.
     */
    public void method2() throws MyException2 {
        try {
            method3(); // Llama a method3(); cualquier excepción no capturada allí llega aquí

        } catch (ArithmeticException e) {
            // Captura excepciones aritméticas estándar de Java (p.ej. dividir por cero).
            // En este programa nunca se generan, pero el catch demuestra que se puede
            // mezclar excepciones de Java con las personalizadas.
            System.out.println("ArithmeticException caught in method2: " + e.getMessage());

        } catch (MyException3 e) {
            // Captura MyException3 (número > 10) y muestra su mensaje.
            // Al capturarse aquí, NO sigue propagándose hacia method1() ni main().
            System.out.println("MyException3 caught in method2: " + e.getMessage());
        }
        // MyException2 NO tiene catch en este bloque → si llega, sube a method1()
    }

    // -----------------------------------------------------------------------
    // method1: solo reenvía la llamada sin añadir lógica de captura
    // -----------------------------------------------------------------------

    /**
     * Llama a method2() sin capturar ninguna excepción.
     * Actúa como intermediario puro que deja que MyException2 siga subiendo.
     *
     * @throws MyException2 Si method2() propagó MyException2 (número negativo).
     *                      Este método la deja pasar sin capturarla.
     */
    public void method1() throws MyException2 {
        method2(); // Delega la llamada; si method2 propaga MyException2, pasa directamente a main
    }

    // -----------------------------------------------------------------------
    // main: punto de entrada y captura final de MyException2
    // -----------------------------------------------------------------------

    /**
     * Punto de entrada del Exercise 2.
     * Crea una instancia de MainExercise2, inicia la cadena de llamadas
     * y captura la única excepción que puede llegar hasta aquí: MyException2.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        MainExercise2 obj = new MainExercise2(); // Crea la instancia para poder llamar métodos de instancia

        try {
            obj.method1(); // Inicia la cadena: main → method1 → method2 → method3

        } catch (MyException2 e) {
            // MyException2 llega aquí si el usuario introdujo un número negativo.
            // Es la única excepción que se propaga hasta main.
            System.out.println("MyException2 caught in main: " + e.getMessage());
        }
        // MyException1 nunca llega aquí (se captura en method3).
        // MyException3 nunca llega aquí (se captura en method2).
    }
}