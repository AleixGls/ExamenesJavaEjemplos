package swingIASlots; // Define el paquete al que pertenece esta clase

// Importaciones necesarias
import javax.swing.*;                 // Componentes Swing (JFrame, JPanel, JLabel, JButton)
import javax.imageio.ImageIO;         // Lee archivos de imagen desde disco
import java.awt.*;                    // BoxLayout, Dimension
import java.awt.event.*;             // ActionListener, ActionEvent
import java.awt.image.BufferedImage;  // Imagen cargada en memoria
import java.io.File;                  // Manejo de rutas del sistema de archivos
import java.io.IOException;           // Excepción de lectura de archivo

/**
 * Segunda (y principal) ventana de la aplicación: pantalla del juego de tragaperras.
 *
 * Responsabilidad: simula una máquina de slots con 5 paneles de imagen.
 * Cada "tirada" (clic en "Play") asigna imágenes aleatorias a los 5 paneles
 * y calcula ganancias/pérdidas según cuántas imágenes iguales salgan.
 *
 * REGLAS DEL JUEGO:
 *   - 5 iguales → gana 3 monedas
 *   - 4 iguales → gana 2 monedas
 *   - 3 iguales → gana 1 moneda
 *   - 2 iguales → pierde 3 monedas
 *   - Ninguna igual → pierde 5 monedas
 *   - Si las monedas llegan a 0, el juego termina y aparece el botón "Finish game".
 *
 * Extiende JFrame → es una ventana completa del sistema operativo.
 */
public class Frame2 extends JFrame {

    // -----------------------------------------------------------------------
    // CONSTANTES
    // -----------------------------------------------------------------------

    /** Número total de imágenes disponibles en la carpeta de recursos (Imagen1.JPG … Imagen9.JPG). */
    private static final int TOTAL_IMAGES = 9;

    /** Ruta relativa a la carpeta de imágenes (relativa al directorio raíz del proyecto). */
    private static final String IMAGES_PATH = "src/swingIASlots/images/";

    /** Número de paneles (ranuras) que componen la máquina tragaperras. */
    private static final int NUM_PANELS = 5;

    // -----------------------------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------------------------

    /** Contador de monedas del jugador. Comienza en 5 y varía según las tiradas. */
    private int coins = 5;

    /** Etiqueta con el saludo personalizado al usuario ("Hello <username>"). */
    private JLabel lblHello;

    /** Etiqueta que muestra el número de monedas actuales ("Coins: X"). */
    private JLabel lblCoins;

    /** Etiqueta que muestra el resultado de la última tirada ("You win/loose X coins"). */
    private JLabel lblGameMessage;

    /** Botón para realizar una tirada. Se oculta cuando el jugador se queda sin monedas. */
    private JButton btnPlay;

    /**
     * Botón para terminar la partida y volver a Frame1.
     * Inicialmente invisible; se muestra solo cuando coins llega a 0.
     */
    private JButton btnFinish;

    /** Array con los 5 paneles personalizados que muestran las imágenes de la tirada. */
    private PanelPersonalitzat[] panels;

    /**
     * Array que almacena en memoria las 9 imágenes disponibles.
     * Se cargan una sola vez al construir Frame2 y se reutilizan en cada tirada,
     * evitando releer el disco repetidamente.
     */
    private BufferedImage[] images;

    // -----------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------

    /**
     * Construye la ventana del juego, carga las imágenes, crea los componentes
     * y registra los listeners.
     *
     * @param username Nombre de usuario elegido en Frame1. Se muestra como saludo.
     */
    public Frame2(String username) {

        // --- Configuración básica del JFrame ---
        setTitle("Game");                               // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana = cerrar la JVM
        setSize(600, 400);                              // Tamaño inicial en píxeles
        setLocationRelativeTo(null);                    // Centra la ventana en la pantalla

        // ---------------------------------------------------------------
        // PRE-CARGA DE IMÁGENES
        // Carga las 9 imágenes del disco una sola vez y las guarda en el array 'images'.
        // Así las tiradas son instantáneas (no hay I/O en cada tirada).
        // ---------------------------------------------------------------
        images = new BufferedImage[TOTAL_IMAGES]; // Array de 9 posiciones (índices 0..8)
        for (int i = 0; i < TOTAL_IMAGES; i++) {
            // loadImage(id) espera ids de 1 a 9, por eso sumamos 1 al índice
            images[i] = loadImage(i + 1);
            // Si el archivo no existe, images[i] quedará null;
            // los paneles simplemente mostrarán fondo vacío para ese id
        }

        // --- Panel principal con distribución vertical ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ---------------------------------------------------------------
        // PANEL 1: Saludo al usuario
        // ---------------------------------------------------------------
        JPanel panel1 = new JPanel();
        lblHello = new JLabel("Hello " + username); // Saludo personalizado
        panel1.add(lblHello);

        // ---------------------------------------------------------------
        // PANEL 2: Contador de monedas
        // ---------------------------------------------------------------
        JPanel panel2 = new JPanel();
        lblCoins = new JLabel("Coins: " + coins); // Muestra el saldo inicial (5 monedas)
        panel2.add(lblCoins);

        // ---------------------------------------------------------------
        // PANEL 3: Los 5 paneles de imagen (ranuras del slot) en fila horizontal
        // ---------------------------------------------------------------
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS)); // Disposición horizontal

        // Limita la altura máxima del panel a 100 px para que no crezca verticalmente
        // Short.MAX_VALUE como ancho permite que se expanda horizontalmente todo lo posible
        Dimension panelSize = new Dimension(Short.MAX_VALUE, 100);
        panel3.setMaximumSize(panelSize);

        panels = new PanelPersonalitzat[NUM_PANELS]; // Array de 5 paneles

        for (int i = 0; i < NUM_PANELS; i++) {
            panels[i] = new PanelPersonalitzat(); // Crea cada ranura

            int randId = randomImageId();          // Elige un id aleatorio entre 1 y 9
            panels[i].setIdImage(randId);          // Guarda el id en el panel

            // Solo asigna la imagen si se cargó correctamente (no es null)
            if (images[randId - 1] != null) {
                // images[randId-1]: el array es base-0, los ids son base-1
                panels[i].setImatge(images[randId - 1]);
            }

            panel3.add(panels[i]); // Añade el panel al contenedor horizontal
        }

        // ---------------------------------------------------------------
        // PANEL 4: Mensaje de resultado de la última tirada
        // ---------------------------------------------------------------
        JPanel panel4 = new JPanel();
        lblGameMessage = new JLabel(" "); // Espacio en blanco para reservar altura desde el inicio
        panel4.add(lblGameMessage);

        // ---------------------------------------------------------------
        // PANEL 5: Botones "Play" y "Finish game"
        // ---------------------------------------------------------------
        JPanel panel5 = new JPanel();
        btnPlay   = new JButton("play");
        btnFinish = new JButton("Finish game");
        btnFinish.setVisible(false); // Oculto al inicio; solo aparece cuando coins = 0
        panel5.add(btnPlay);
        panel5.add(btnFinish);

        // ---------------------------------------------------------------
        // Ensamblar todos los paneles en el panel principal
        // ---------------------------------------------------------------
        mainPanel.add(Box.createVerticalStrut(10)); // Margen superior
        mainPanel.add(panel1);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(panel2);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(panel3);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(panel4);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(panel5);
        mainPanel.add(Box.createVerticalGlue()); // Espacio flexible inferior

        add(mainPanel); // Añade el panel principal al JFrame

        // ---------------------------------------------------------------
        // LISTENER del botón "Play"
        // ---------------------------------------------------------------
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delega toda la lógica de la tirada al método playRound()
                playRound(username);
            }
        });

        // ---------------------------------------------------------------
        // LISTENER del botón "Finish game"
        // ---------------------------------------------------------------
        btnFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vuelve a la pantalla de login creando una nueva instancia de Frame1
                Frame1 frame1 = new Frame1();
                frame1.setVisible(true);

                // Destruye esta ventana y libera sus recursos
                dispose();
            }
        });
    }

    // -----------------------------------------------------------------------
    // LÓGICA DEL JUEGO
    // -----------------------------------------------------------------------

    /**
     * Ejecuta una tirada completa de la máquina tragaperras:
     *   1. Asigna imágenes aleatorias a los 5 paneles.
     *   2. Calcula cuántas imágenes iguales hay como máximo.
     *   3. Actualiza las monedas y muestra el mensaje de resultado.
     *   4. Comprueba si el juego ha terminado (coins ≤ 0).
     *
     * @param username Nombre del usuario (no se usa en la lógica, pero se podría
     *                 usar para personalizar mensajes futuros).
     */
    private void playRound(String username) {

        // --- PASO 1: Asignar imágenes aleatorias a todos los paneles ---
        int[] ids = new int[NUM_PANELS]; // Array temporal para guardar los ids de esta tirada

        for (int i = 0; i < NUM_PANELS; i++) {
            int randId = randomImageId();       // Nuevo id aleatorio (1..9)
            ids[i] = randId;                    // Guarda el id para contar repeticiones después
            panels[i].setIdImage(randId);       // Actualiza el id guardado en el panel

            if (images[randId - 1] != null) {
                // Actualiza la imagen visible del panel (repaint() se llama internamente en setImatge)
                panels[i].setImatge(images[randId - 1]);
            }
        }

        // --- PASO 2: Calcular el máximo de imágenes iguales en esta tirada ---
        int maxRepeticions = 1; // Mínimo 1 (cualquier id aparece al menos una vez)

        // Algoritmo de conteo: para cada id, cuenta cuántas veces aparece en el array
        for (int i = 0; i < ids.length; i++) {
            int contador = 1; // El propio elemento cuenta como 1

            for (int j = i + 1; j < ids.length; j++) {
                if (ids[i] == ids[j]) { // Compara el elemento i con todos los que le siguen
                    contador++;
                }
            }

            // Actualiza el máximo si esta id aparece más veces que el registro actual
            if (contador > maxRepeticions) {
                maxRepeticions = contador;
            }
        }

        // --- PASO 3: Aplicar ganancias/pérdidas según el número máximo de iguales ---
        String message = ""; // Mensaje de resultado que se mostrará en lblGameMessage

        switch (maxRepeticions) {
            case 5:
                // ¡Todas iguales! Máximo premio
                coins += 3;
                message = "You win 3 coins";
                break;

            case 4:
                // Cuatro iguales → premio medio
                coins += 2;
                message = "You win 2 coins";
                break;

            case 3:
                // Tres iguales → premio pequeño
                coins += 1;
                message = "You win 1 coin";
                break;

            case 2:
                // Solo dos iguales → penalización moderada
                coins -= 3;
                message = "You loose 3 coins";
                break;

            default: // maxRepeticions == 1: ninguna imagen igual
                coins -= 5;
                if (coins <= 0) {
                    // Las monedas han llegado a 0 o menos → se ajusta a 0 y mensaje especial
                    coins = 0;
                    message = "You loose 5 coins. You have run out of coins";
                } else {
                    message = "You loose 5 coins";
                }
                break;
        }

        // Actualiza los componentes de la interfaz con los nuevos valores
        lblGameMessage.setText(message);       // Muestra el resultado de la tirada
        lblCoins.setText("Coins: " + coins);   // Actualiza el contador de monedas

        // --- PASO 4: Comprobar fin de juego ---
        if (coins <= 0) {
            coins = 0;                            // Asegura que nunca sea negativo
            lblCoins.setText("Coins: 0");         // Fuerza la etiqueta a "Coins: 0"
            btnPlay.setVisible(false);            // Oculta "Play" para impedir más tiradas
            btnFinish.setVisible(true);           // Muestra "Finish game" para que el usuario vuelva al login
        }
    }

    // -----------------------------------------------------------------------
    // MÉTODOS AUXILIARES
    // -----------------------------------------------------------------------

    /**
     * Genera un número entero aleatorio entre 1 y TOTAL_IMAGES (ambos inclusive).
     * Utiliza Math.random() en lugar de java.util.Random, ambos válidos.
     *
     * @return Entero aleatorio en el rango [1, TOTAL_IMAGES].
     */
    private int randomImageId() {
        // Math.random() devuelve un double en [0.0, 1.0).
        // Multiplicar por TOTAL_IMAGES → [0.0, 9.0).
        // Casting a int trunca → [0, 8].
        // Sumando 1 → [1, 9].
        return (int) (Math.random() * TOTAL_IMAGES) + 1;
    }

    /**
     * Carga desde disco la imagen correspondiente al ID dado.
     * El nombre del archivo sigue el patrón: "Imagen{id}.JPG"
     *
     * Las imágenes se cargan UNA SOLA VEZ en el constructor y se almacenan en
     * el array 'images', por lo que este método no se llama durante las tiradas.
     *
     * @param id Identificador numérico de la imagen (1..TOTAL_IMAGES).
     * @return BufferedImage con la imagen en memoria,
     *         o null si el archivo no existe o hay un error de E/S.
     */
    private BufferedImage loadImage(int id) {
        try {
            // Construye la ruta relativa al archivo de imagen
            File file = new File(IMAGES_PATH + "Imagen" + id + ".JPG");

            if (file.exists()) {
                // ImageIO.read() decodifica el archivo JPEG y lo devuelve como BufferedImage
                return ImageIO.read(file);
            } else {
                // El archivo no existe: informa por consola y devuelve null
                System.out.println("Image not found: " + file.getPath());
                return null;
            }
        } catch (IOException e) {
            // Error de lectura (permisos, disco dañado, etc.)
            e.printStackTrace(); // Imprime la traza en la consola de errores
            return null;         // Devuelve null para que el panel muestre fondo vacío
        }
    }
}