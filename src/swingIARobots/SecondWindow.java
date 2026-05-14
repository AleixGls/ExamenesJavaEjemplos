package swingIARobots; // Define el paquete al que pertenece esta clase

// Importaciones necesarias
import javax.swing.*;      // Componentes Swing (JFrame, JPanel, JLabel, JComboBox, JButton)
import java.awt.*;         // Font, BoxLayout
import java.awt.event.*;   // ActionListener, ActionEvent
import java.util.ArrayList; // Lista dinámica de nombres recibida desde FirstWindow

/**
 * Segunda ventana de la aplicación (Exercise 1).
 *
 * Responsabilidad: muestra un desplegable (JComboBox) con todos los nombres
 * que el usuario guardó en FirstWindow, y le permite elegir uno como username
 * antes de pasar a la pantalla principal (ThirdWindow).
 *
 * Extiende JFrame → es una ventana completa del sistema operativo.
 */
public class SecondWindow extends JFrame {

    /**
     * Constructor que recibe la lista de nombres guardados y construye la ventana.
     *
     * @param names Lista de strings con los nombres de usuario válidos
     *              acumulados en FirstWindow.
     */
    public SecondWindow(ArrayList<String> names) {

        // --- Configuración básica del JFrame ---
        setTitle("Choose Username");                    // Título en la barra de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana = cerrar aplicación
        setSize(400, 300);                              // Tamaño inicial en píxeles
        setLocationRelativeTo(null);                    // Centra la ventana en la pantalla

        // --- Panel principal con distribución vertical ---
        JPanel mainPanel = new JPanel();
        // BoxLayout.Y_AXIS: los componentes se apilan de arriba a abajo
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------
        // PANEL 1: Etiqueta instructiva ("Elige un nombre de usuario")
        // ---------------------------------------------------------------
        JPanel panel1 = new JPanel(); // FlowLayout por defecto
        // Nota: "Coose" es un typo en el original; se mantiene para no alterar el comportamiento
        JLabel lblChoose = new JLabel("Coose some username");
        panel1.add(lblChoose);

        // ---------------------------------------------------------------
        // PANEL 2: JComboBox con todos los nombres guardados
        // ---------------------------------------------------------------
        JPanel panel2 = new JPanel();

        // JComboBox<String>: desplegable que muestra una lista de cadenas de texto
        JComboBox<String> cb = new JComboBox<>();
        // Aumenta el tamaño de la fuente para mejorar la legibilidad
        cb.setFont(new Font("Arial", Font.PLAIN, 20));

        // Rellena el JComboBox con cada nombre de la lista recibida
        for (String n : names) {
            cb.addItem(n); // Añade cada nombre como una opción seleccionable
        }

        panel2.add(cb); // Añade el desplegable al panel

        // ---------------------------------------------------------------
        // PANEL 3: Botón "Start" para confirmar la selección
        // ---------------------------------------------------------------
        JPanel panel3 = new JPanel();
        JButton btnStart = new JButton("Start"); // Botón para avanzar a ThirdWindow
        panel3.add(btnStart);

        // ---------------------------------------------------------------
        // Añadir los paneles al panel principal con espacio vertical
        // ---------------------------------------------------------------
        mainPanel.add(Box.createVerticalGlue());    // Espacio flexible superior
        mainPanel.add(panel1);
        mainPanel.add(Box.createVerticalStrut(10)); // Separación fija de 10 px
        mainPanel.add(panel2);
        mainPanel.add(Box.createVerticalStrut(10)); // Separación fija de 10 px
        mainPanel.add(panel3);
        mainPanel.add(Box.createVerticalGlue());    // Espacio flexible inferior

        // Añade el panel principal como contenido de este JFrame
        add(mainPanel);

        // ---------------------------------------------------------------
        // LISTENER del botón "Start"
        // ---------------------------------------------------------------
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene el elemento actualmente seleccionado en el JComboBox.
                // cb.getSelectedItem() devuelve Object, así que se convierte a String.
                String selectedName = String.valueOf(cb.getSelectedItem());

                // Crea la tercera ventana pasándole el nombre elegido como título/username
                ThirdWindow thirdWindow = new ThirdWindow(selectedName);
                thirdWindow.setVisible(true); // Muestra ThirdWindow

                // Oculta esta ventana (sin destruirla) para evitar solapamiento visual
                setVisible(false);
            }
        });
    }
}