package swingEjemplosExtra;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManejoExcepciones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ejemplo 1: Try-Catch básico
        try {
            System.out.print("Ingrese un número entero: ");
            int numero = scanner.nextInt();
            System.out.println("El número ingresado es: " + numero);
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            scanner.next(); // Limpiar el buffer del scanner
        }
        
        // Ejemplo 2: Múltiples catch
        try {
            System.out.print("\nIngrese el numerador: ");
            int numerador = scanner.nextInt();
            System.out.print("Ingrese el denominador: ");
            int denominador = scanner.nextInt();
            
            double resultado = dividir(numerador, denominador);
            System.out.println("Resultado de la división: " + resultado);
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Debe ingresar números.");
        } catch (ArithmeticException e) {
            System.out.println("Error: División por cero no permitida.");
        }
        
        // Ejemplo 3: Finally
        try {
            System.out.print("\nIngrese su edad: ");
            int edad = scanner.nextInt();
            verificarEdad(edad);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Bloque finally ejecutado. Esto siempre se ejecuta.");
        }
        
        // Ejemplo 4: Excepciones personalizadas
        try {
            System.out.print("\nIngrese un número positivo: ");
            int num = scanner.nextInt();
            if (num < 0) {
                throw new NumeroNegativoException("Números negativos no permitidos");
            }
            System.out.println("Número válido: " + num);
        } catch (NumeroNegativoException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida.");
        }
        
        scanner.close();
    }
    
    // Método que puede lanzar una excepción
    public static double dividir(int numerador, int denominador) {
        if (denominador == 0) {
            throw new ArithmeticException("División por cero");
        }
        return (double) numerador / denominador;
    }
    
    // Método que verifica edad
    public static void verificarEdad(int edad) {
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("Edad inválida. Debe estar entre 0 y 120.");
        }
        System.out.println("Edad válida: " + edad);
    }
}

// Excepción personalizada
class NumeroNegativoException extends Exception {
    public NumeroNegativoException(String mensaje) {
        super(mensaje);
    }
}