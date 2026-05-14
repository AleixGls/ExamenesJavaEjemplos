package swingIARobots; // Define el paquete al que pertenece esta clase

// Importaciones necesarias
import javax.swing.*;                  // Componentes Swing (JFrame, JPanel, JLabel, JButton, etc.)
import javax.imageio.ImageIO;          // Lee archivos de imagen desde disco
import java.awt.*;                     // BoxLayout, Graphics
import java.awt.event.*;              // MouseAdapter, MouseEvent, ActionListener, ActionEvent
import java.awt.image.BufferedImage;   // Representa una imagen cargada en memoria
import java.io.File;                   // Manejo de rutas de archivo del sistema operativo
import java.io.IOException;            // Excepción lanzada si falla la lectura de la imagen
import java.util.Random;               // Generación de números aleatorios para elegir imágenes

/**
 * Tercera (y principal) ventana de la aplicación (Exercise 1).
 *
 * Responsabilidad: muestra 3 paneles (MyPane) con imágenes de robots cargadas
 * aleatoriamente desde disco. Cada panel tiene:
 *   - Un MouseListener que muestra el ID de la imagen al hacer clic.
 *   - Un JLabel debajo indicando el número de imagen.
 *   - Un JButton que reemplaza la imagen por otra aleatoria.
 * También incluye un botón "Exit" global.
 *
 * El título de la ventana es el nombre de usuario elegido en SecondWindow.
 *
 * Extiende JFrame → es una ventana completa del sistema operativo.
 */
public class ThirdWindow extends JFrame {

    // -----------------------------------------------------------------------
    // CONSTANTES
    // -----------------------------------------------------------------------

    /**
     * Número total de imágenes disponibles en la carpeta de recursos.
     * Las imágenes deben llamarse Imagen1.JPG, Imagen2.JPG … Imagen9.JPG.
     */
    private static final int TOTAL_IMAGES = 9;

    /**
     * Ruta relativa a la carpeta donde se guardan las imágenes.
     * Es relativa al directorio de trabajo del proyecto (normalmente la raíz del proyecto).
     */
    private static final String IMAGES_PATH = "src/swingIARobots/images/";

    // -----------------------------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------------------------

    /** Array con los 3 paneles personalizados que muestran las imágenes. */
    private MyPane[] panes;

    /** Array con las 3 etiquetas que indican el número de imagen de cada panel. */
    private JLabel[] labels;

    /** Generador de números aleatorios para elegir imágenes al azar. */
    private Random random;

    // -----------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------

    /**
     * Construye la ventana principal con los paneles de imagen, etiquetas y botones.
     *
     * @param username Nombre de usuario seleccionado en SecondWindow.
     *                 Se usa como título de la ventana.
     */
    public ThirdWindow(String username) {

        // --- Configuración básica del JFrame ---
        setTitle(username);                             // El título es el nombre del usuario elegido
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana = cerrar la aplicación
        setSize(550, 450);                              // Tamaño inicial en píxeles
        setLocationRelativeTo(null);                    // Centra la ventana en la pantalla

        // Inicializa el generador de números aleatorios
        random = new Random();

        // Inicializa los arrays para 3 paneles y 3 etiquetas
        panes  = new MyPane[3];
        labels = new JLabel[3];

        // --- Panel principal con distribución vertical ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------
        // PANEL DE IMÁGENES: 3 MyPane en fila horizontal
        // ---------------------------------------------------------------
        JPanel panelImages = new JPanel();
        // BoxLayout.X_AXIS: los componentes se colocan de izquierda a derecha
        panelImages.setLayout(new BoxLayout(panelImages, BoxLayout.X_AXIS));

        for (int i = 0; i < 3; i++) {
            panes[i] = new MyPane(); // Crea un nuevo panel de imagen personalizado

            int randId = randomImageId();   // Genera un ID aleatorio entre 1 y TOTAL_IMAGES
            panes[i].setImageId(randId);    // Asigna el ID al panel (para mostrarlo al hacer clic)
            panes[i].setImage(loadImage(randId)); // Carga la imagen del disco y la asigna al panel

            // 'final' es necesario para poder usar 'i' dentro de la clase anónima
            final int index = i;

            // -------------------------------------------------------
            // MouseListener: al hacer clic sobre el panel muestra un diálogo
            // -------------------------------------------------------
            panes[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Muestra un diálogo modal con el ID de la imagen del panel pulsado
                    JOptionPane.showMessageDialog(null,
                        "ID Image = " + panes[index].getImageId());
                }
            });

            panelImages.add(panes[i]);                        // Añade el panel de imagen
            panelImages.add(Box.createHorizontalGlue());      // Espacio flexible entre paneles
        }

        // ---------------------------------------------------------------
        // PANEL DE ETIQUETAS: 3 JLabel con el número de imagen de cada panel
        // ---------------------------------------------------------------
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.X_AXIS));

        for (int i = 0; i < 3; i++) {
            // Crea una etiqueta con el texto "Image X" donde X es el ID actual del panel i
            labels[i] = new JLabel("Image " + panes[i].getImageId());

            // Alineación central del texto dentro del JLabel
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);

            panelLabels.add(labels[i]);                       // Añade la etiqueta
            panelLabels.add(Box.createHorizontalGlue());      // Espacio flexible
        }

        // ---------------------------------------------------------------
        // PANEL DE BOTONES: 3 botones, uno por cada panel de imagen
        // ---------------------------------------------------------------
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));

        for (int i = 0; i < 3; i++) {
            final int index = i; // Necesario para la clase anónima interna

            // Crea un botón etiquetado "button1", "button2" o "button3"
            JButton btn = new JButton("button" + (i + 1));

            // ---------------------------------------------------
            // ActionListener: al pulsar el botón cambia la imagen del panel asociado
            // ---------------------------------------------------
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int newId = randomImageId();         // Genera un nuevo ID aleatorio
                    panes[index].setImageId(newId);      // Actualiza el ID guardado en el panel
                    panes[index].setImage(loadImage(newId)); // Carga la nueva imagen y repinta
                    labels[index].setText("Image " + newId); // Actualiza el JLabel correspondiente
                }
            });

            panelButtons.add(btn);                        // Añade el botón al panel
            panelButtons.add(Box.createHorizontalGlue()); // Espacio flexible
        }

        // ---------------------------------------------------------------
        // PANEL DE SALIDA: botón "Exit"
        // ---------------------------------------------------------------
        JPanel panelExit = new JPanel();
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Termina la JVM con código 0 (sin error)
            }
        });
        panelExit.add(btnExit);

        // ---------------------------------------------------------------
        // Ensamblar todos los sub-paneles dentro del panel principal
        // ---------------------------------------------------------------
        mainPanel.add(Box.createVerticalStrut(10));  // Margen superior de 10 px
        mainPanel.add(panelImages);                  // Fila de imágenes
        mainPanel.add(Box.createVerticalStrut(5));   // Separación de 5 px
        mainPanel.add(panelLabels);                  // Fila de etiquetas de ID
        mainPanel.add(Box.createVerticalStrut(5));   // Separación de 5 px
        mainPanel.add(panelButtons);                 // Fila de botones de cambio
        mainPanel.add(Box.createVerticalGlue());     // Espacio flexible que empuja el Exit hacia abajo
        mainPanel.add(panelExit);                    // Botón de salida al final
        mainPanel.add(Box.createVerticalStrut(10));  // Margen inferior de 10 px

        // Añade el panel principal como contenido de este JFrame
        add(mainPanel);
    }

    // -----------------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // -----------------------------------------------------------------------

    /**
     * Genera un número entero aleatorio entre 1 y TOTAL_IMAGES (ambos inclusive).
     * Se usa para elegir qué imagen cargar en un panel.
     *
     * @return Entero aleatorio en el rango [1, TOTAL_IMAGES].
     */
    private int randomImageId() {
        // random.nextInt(N) devuelve un valor en [0, N-1].
        // Sumando 1 obtenemos [1, TOTAL_IMAGES].
        return random.nextInt(TOTAL_IMAGES) + 1;
    }

    /**
     * Carga desde disco la imagen correspondiente al ID dado.
     * El nombre del archivo sigue el patrón: "Imagen{id}.JPG"
     *
     * @param id Identificador numérico de la imagen a cargar (1..TOTAL_IMAGES).
     * @return BufferedImage con la imagen cargada en memoria,
     *         o null si el archivo no existe o se produce un error de E/S.
     */
    private BufferedImage loadImage(int id) {
        try {
            // Construye la ruta absoluta/relativa al archivo de imagen
            File file = new File(IMAGES_PATH + "Imagen" + id + ".JPG");

            // Mensajes de depuración: ayudan a localizar problemas de ruta en tiempo de ejecución
            System.out.println("Buscando en: " + file.getAbsolutePath());
            System.out.println("Existe: "      + file.exists());

            if (file.exists()) {
                // ImageIO.read() decodifica el archivo JPEG y lo devuelve como BufferedImage
                return ImageIO.read(file);
            } else {
                // Si el archivo no existe, informa por consola y devuelve null
                // (MyPane.paintComponent() ignora valores null, así que el panel quedará vacío)
                System.out.println("Image not found: " + file.getPath());
                return null;
            }
        } catch (IOException e) {
            // IOException cubre errores de lectura del archivo (permisos, disco, etc.)
            e.printStackTrace(); // Imprime la traza del error en la consola de errores
            return null;         // Devuelve null para que el panel muestre un fondo vacío
        }
    }
}