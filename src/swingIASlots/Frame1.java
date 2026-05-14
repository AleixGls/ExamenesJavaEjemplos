package swingIASlots; // Define el paquete al que pertenece esta clase

// Importaciones necesarias
import javax.swing.*;      // Componentes Swing (JFrame, JPanel, JLabel, JTextField, JButton)
import java.awt.*;         // BoxLayout, Dimension
import java.awt.event.*;   // ActionListener, ActionEvent

/**
 * Primera ventana de la aplicación Slots: pantalla de login.
 *
 * Responsabilidad: permite al usuario introducir y guardar UN nombre de usuario.
 * A diferencia del ejercicio anterior, aquí solo se guarda UN nombre a la vez
 * (String savedUsername, no ArrayList), y al pulsar "Start" se abre Frame2.
 *
 * Validación: el nombre solo puede contener letras (a-z, A-Z).
 * Los errores y confirmaciones se muestran en un JLabel.
 *
 * Extiende JFrame → es una ventana completa del sistema operativo.
 */
public class Frame1 extends JFrame {

    // -----------------------------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------------------------

    /** Campo de texto donde el usuario escribe el nombre de usuario. */
    private JTextField txtUsername;

    /** Etiqueta de feedback: muestra mensajes de éxito o de error al usuario. */
    private JLabel lblMessage;

    /**
     * Nombre de usuario guardado tras pasar la validación.
     * null si aún no se ha guardado ninguno o si el último intento fue inválido.
     * Solo puede haber UN nombre guardado a la vez (no es una lista).
     */
    private String savedUsername = null;

    // -----------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------

    /**
     * Construye y muestra la ventana de login.
     * Configura la ventana, crea todos los paneles/componentes
     * y registra los listeners de los botones.
     */
    public Frame1() {

        // --- Configuración básica del JFrame ---
        setTitle("Login");                              // Título en la barra de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana = cerrar la JVM
        setSize(450, 300);                              // Tamaño inicial en píxeles
        setLocationRelativeTo(null);                    // Centra la ventana en la pantalla

        // --- Panel principal con distribución vertical ---
        JPanel mainPanel = new JPanel();
        // BoxLayout.Y_AXIS: los componentes se apilan de arriba a abajo
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------
        // PANEL 1: Etiqueta de mensajes de estado/error
        // ---------------------------------------------------------------
        JPanel panel1 = new JPanel();
        lblMessage = new JLabel(" "); // Espacio en blanco inicial para que el panel tenga altura
        panel1.add(lblMessage);

        // ---------------------------------------------------------------
        // PANEL 2: Etiqueta de instrucción al usuario
        // ---------------------------------------------------------------
        JPanel panel2 = new JPanel();
        JLabel lblInstruction = new JLabel("Write your username (only letters are allowed):");
        panel2.add(lblInstruction);

        // ---------------------------------------------------------------
        // PANEL 3: Campo de texto + botón "Save name"
        // ---------------------------------------------------------------
        JPanel panel3 = new JPanel();

        txtUsername = new JTextField();
        // Fija un tamaño máximo y preferido para evitar que el JTextField
        // se expanda horizontalmente ocupando todo el ancho del BoxLayout
        Dimension size = new Dimension(100, 25);
        txtUsername.setMaximumSize(size);   // Límite superior de tamaño
        txtUsername.setPreferredSize(size); // Tamaño que el layout intentará respetar

        JButton btnSave = new JButton("Save name"); // Guarda el nombre introducido
        panel3.add(txtUsername);
        panel3.add(btnSave);

        // ---------------------------------------------------------------
        // PANEL 4: Botón "Start" para pasar al juego
        // ---------------------------------------------------------------
        JPanel panel4 = new JPanel();
        JButton btnStart = new JButton("Start");
        panel4.add(btnStart);

        // ---------------------------------------------------------------
        // PANEL 5: Botón "Exit"
        // ---------------------------------------------------------------
        JPanel panel5 = new JPanel();
        JButton btnExit = new JButton("Exit");
        panel5.add(btnExit);

        // ---------------------------------------------------------------
        // Ensamblar todos los paneles en el panel principal con separaciones
        // ---------------------------------------------------------------
        mainPanel.add(Box.createVerticalStrut(10)); // Margen superior de 10 px
        mainPanel.add(panel1);
        mainPanel.add(Box.createVerticalStrut(5));  // Separación de 5 px
        mainPanel.add(panel2);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(panel3);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(panel4);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(panel5);
        mainPanel.add(Box.createVerticalGlue()); // Espacio flexible que rellena el espacio restante

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
                    String name = txtUsername.getText(); // Lee el texto del campo

                    // Valida que el nombre cumpla las reglas (solo letras, no vacío)
                    checkName(name);

                    // Si la validación pasó, guarda el nombre en el atributo
                    savedUsername = name;

                    // Muestra confirmación en la etiqueta de mensajes
                    lblMessage.setText("User '" + name + "' saved.");

                    // Limpia el campo para facilitar una nueva introducción
                    txtUsername.setText("");

                } catch (MyException ex) {
                    // Si la validación falló, muestra el mensaje de error
                    lblMessage.setText(ex.getMessage());

                    // Invalida cualquier nombre que pudiera haber guardado antes,
                    // para que el usuario no pueda hacer Start con un nombre antiguo
                    // tras haber intentado uno nuevo inválido
                    savedUsername = null;
                }
            }
        });

        // ---------------------------------------------------------------
        // LISTENER del botón "Start"
        // ---------------------------------------------------------------
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se ejecuta cuando el usuario hace clic en "Start"
                try {
                    // Precondición: debe haberse guardado un nombre válido primero
                    if (savedUsername == null) {
                        throw new MyException("Exception: Missing save username");
                    }

                    // Crea Frame2 (ventana del juego) pasándole el nombre guardado
                    Frame2 frame2 = new Frame2(savedUsername);
                    frame2.setVisible(true); // Muestra la ventana del juego

                    // dispose() destruye esta ventana y libera sus recursos,
                    // a diferencia de setVisible(false) que solo la oculta
                    dispose();

                } catch (MyException ex) {
                    // Muestra el error si no había nombre guardado
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

        // Hace visible la ventana (el constructor la muestra directamente)
        setVisible(true);
    }

    // -----------------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // -----------------------------------------------------------------------

    /**
     * Valida que la cadena proporcionada cumpla las reglas del nombre de usuario:
     *   1. No puede estar vacía.
     *   2. Solo puede contener letras del alfabeto (a-z o A-Z).
     *
     * @param s Cadena a validar (texto leído del campo txtUsername).
     * @throws MyException Si la cadena está vacía o contiene caracteres no permitidos.
     */
    private void checkName(String s) throws MyException {
        // Regla 1: el campo no puede estar vacío
        if (s.equals("")) {
            throw new MyException("Exception: Empty name not allowed");
        }

        // Recorre la cadena carácter a carácter
        for (int i = 0; i < s.length(); i++) {
            char t = s.charAt(i); // Obtiene el carácter en la posición i

            // Regla 2: solo se permiten letras minúsculas (a-z) o mayúsculas (A-Z)
            // Si el carácter NO cumple ninguna condición, lanza la excepción
            if (!((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z'))) {
                throw new MyException("Exception: The name entered is not valid");
            }
        }
        // Si el bucle termina sin excepción → el nombre es válido
    }
}