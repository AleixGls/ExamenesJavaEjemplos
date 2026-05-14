package swingIARobots; // Define el paquete al que pertenece esta clase

// Importaciones necesarias
import javax.swing.*;          // Componentes Swing (JFrame, JPanel, JLabel, etc.)
import java.awt.*;             // Layout managers (BoxLayout) y clases de AWT
import java.awt.event.*;       // ActionListener, ActionEvent para manejar eventos de botón
import java.util.ArrayList;    // Lista dinámica para almacenar los nombres de usuario

/**
 * Primera ventana de la aplicación (Exercise 1).
 *
 * Responsabilidad: permite al usuario introducir y guardar nombres de usuario.
 * Cuando hay al menos un nombre guardado, el botón "Start" abre SecondWindow.
 *
 * Validación: los nombres solo pueden contener letras (a-z, A-Z).
 * Los errores se muestran en un JLabel sin lanzar diálogos emergentes.
 *
 * Extiende JFrame → es una ventana completa del sistema operativo.
 */
public class FirstWindow extends JFrame {

    // -----------------------------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------------------------

    /** Campo de texto donde el usuario escribe el nombre que quiere guardar. */
    private JTextField txtUsername;

    /** Etiqueta de feedback: muestra mensajes de éxito o de error al usuario. */
    private JLabel lblMessage;

    /**
     * Lista dinámica que acumula todos los nombres válidos guardados en esta sesión.
     * Se pasa a SecondWindow para que el usuario elija cuál usar como username.
     */
    private ArrayList<String> namesList;

    // -----------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------

    /**
     * Construye y muestra la primera ventana.
     * Configura la ventana, crea todos los paneles/componentes,
     * y registra los listeners de los botones.
     */
    public FirstWindow() {
        // Inicializa la lista vacía donde se irán acumulando los nombres guardados
        namesList = new ArrayList<>();

        // --- Configuración básica del JFrame ---
        setTitle("User Registration");             // Título en la barra de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Al cerrar la ventana, termina la JVM
        setSize(400, 300);                         // Tamaño inicial de la ventana en píxeles
        setLocationRelativeTo(null);               // Centra la ventana en la pantalla

        // --- Panel principal con distribución vertical ---
        JPanel mainPanel = new JPanel();
        // BoxLayout.Y_AXIS apila los componentes de arriba a abajo
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------
        // PANEL 1: Etiqueta + campo de texto para el nombre de usuario
        // ---------------------------------------------------------------
        JPanel panel1 = new JPanel(); // FlowLayout por defecto (componentes en fila)
        JLabel lblUsername = new JLabel("Add some username"); // Instrucción al usuario
        txtUsername = new JTextField(15); // Campo de texto con capacidad visible de 15 caracteres
        panel1.add(lblUsername);   // Añade la etiqueta al panel
        panel1.add(txtUsername);   // Añade el campo de texto al panel

        // ---------------------------------------------------------------
        // PANEL 2: Etiqueta de mensajes de estado / error
        // ---------------------------------------------------------------
        JPanel panel2 = new JPanel();
        // Mensaje inicial genérico; se sobrescribe dinámicamente con feedback real
        lblMessage = new JLabel("messages about the username");
        panel2.add(lblMessage);

        // ---------------------------------------------------------------
        // PANEL 3: Botones "Save name" y "Start"
        // ---------------------------------------------------------------
        JPanel panel3 = new JPanel();
        JButton btnSave  = new JButton("Save name"); // Guarda el nombre actual en la lista
        JButton btnStart = new JButton("Start");      // Avanza a SecondWindow
        panel3.add(btnSave);
        panel3.add(btnStart);

        // ---------------------------------------------------------------
        // PANEL 4: Botón "Exit"
        // ---------------------------------------------------------------
        JPanel panel4 = new JPanel();
        JButton btnExit = new JButton("Exit"); // Cierra la aplicación
        panel4.add(btnExit);

        // ---------------------------------------------------------------
        // Añadir todos los paneles al panel principal con espacio vertical
        // ---------------------------------------------------------------
        mainPanel.add(Box.createVerticalGlue());    // Espacio flexible superior (centra el contenido)
        mainPanel.add(panel1);
        mainPanel.add(Box.createVerticalStrut(10)); // Separación fija de 10 px entre panel1 y panel2
        mainPanel.add(panel2);
        mainPanel.add(Box.createVerticalStrut(10)); // Separación fija de 10 px entre panel2 y panel3
        mainPanel.add(panel3);
        mainPanel.add(Box.createVerticalStrut(10)); // Separación fija de 10 px entre panel3 y panel4
        mainPanel.add(panel4);
        mainPanel.add(Box.createVerticalGlue());    // Espacio flexible inferior

        // Añade el panel principal como contenido de este JFrame
        add(mainPanel);

        // ---------------------------------------------------------------
        // LISTENER del botón "Save name"
        // ---------------------------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se ejecuta cada vez que el usuario hace clic en "Save name"
                try {
                    String name = txtUsername.getText(); // Lee el texto del campo de entrada

                    // Valida que el nombre solo contenga letras (lanza MyExceptions si no)
                    checkAlphabet(name);

                    // Si la validación pasó, añade el nombre a la lista acumulada
                    namesList.add(name);

                    // Muestra mensaje de éxito en lblMessage
                    lblMessage.setText("User '" + name + "' saved successfully.");

                    // Limpia el campo de texto para facilitar la introducción del siguiente nombre
                    txtUsername.setText("");

                } catch (MyExceptions ex) {
                    // Si checkAlphabet lanzó una excepción, muestra su mensaje en lblMessage
                    // (p.ej. "Exception: Missing save username" o "Exception: The name entered is not valid")
                    lblMessage.setText(ex.getMessage());
                }
            }
        });

        // ---------------------------------------------------------------
        // LISTENER del botón "Start"
        // ---------------------------------------------------------------
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se ejecuta cada vez que el usuario hace clic en "Start"
                try {
                    // Precondición: debe haber al menos un nombre guardado
                    if (namesList.isEmpty()) {
                        // Si la lista está vacía, lanza la excepción personalizada
                        throw new MyExceptions("names list is empty");
                    }

                    // Crea y muestra la segunda ventana pasándole la lista de nombres
                    SecondWindow secondWindow = new SecondWindow(namesList);
                    secondWindow.setVisible(true); // Hace visible SecondWindow

                    // Oculta esta ventana (sin destruirla) para que no se solapen
                    setVisible(false);

                } catch (MyExceptions ex) {
                    // Muestra el error si la lista estaba vacía
                    lblMessage.setText(ex.getMessage());
                }
            }
        });

        // ---------------------------------------------------------------
        // LISTENER del botón "Exit"
        // ---------------------------------------------------------------
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Termina la JVM con código de salida 0 (sin error)
                System.exit(0);
            }
        });

        // Hace visible la ventana (el constructor ya la muestra directamente)
        setVisible(true);
    }

    // -----------------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // -----------------------------------------------------------------------

    /**
     * Valida que la cadena proporcionada cumpla las reglas de nombre de usuario:
     *   1. No puede estar vacía.
     *   2. Solo puede contener letras del alfabeto (a-z o A-Z), sin números ni símbolos.
     *
     * @param s Cadena a validar (texto leído del campo txtUsername).
     * @throws MyExceptions Si la cadena está vacía o contiene caracteres no permitidos.
     */
    private void checkAlphabet(String s) throws MyExceptions {
        // Regla 1: el campo no puede estar vacío
        if (s.equals("")) {
            throw new MyExceptions("Exception: Missing save username");
        }

        char t; // Variable auxiliar para analizar cada carácter uno a uno

        // Itera sobre cada carácter de la cadena
        for (int i = 0; i < s.length(); i++) {
            t = s.charAt(i); // Obtiene el carácter en la posición i

            // Regla 2: el carácter debe ser una letra minúscula (a-z) o mayúscula (A-Z)
            // Si NO cumple ninguna de las dos condiciones, lanza la excepción
            if (!((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z'))) {
                throw new MyExceptions("Exception: The name entered is not valid");
            }
        }
        // Si el bucle termina sin lanzar excepción → la cadena es válida
    }
}