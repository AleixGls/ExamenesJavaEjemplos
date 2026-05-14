package swingIARobots; // Define el paquete al que pertenece esta clase

// Importaciones necesarias para el panel Swing y el manejo de imágenes
import javax.swing.*;          // JPanel y componentes Swing
import java.awt.*;             // Graphics, Dimension
import java.awt.image.BufferedImage; // Representa una imagen en memoria (píxeles accesibles)

/**
 * Panel personalizado que extiende JPanel.
 * Sirve como "celda" visual que muestra una imagen escalada al tamaño del panel.
 * Cada instancia almacena:
 *   - Un identificador numérico de la imagen (imageId).
 *   - La propia imagen en memoria (BufferedImage).
 *
 * Se usa en ThirdWindow para mostrar las 3 imágenes de robots en paralelo.
 */
public class MyPane extends JPanel {

    // -----------------------------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------------------------

    /** Identificador numérico de la imagen cargada (1..TOTAL_IMAGES). */
    private int imageId;

    /**
     * Imagen almacenada en memoria lista para dibujar.
     * BufferedImage permite acceder a los píxeles directamente,
     * lo que es necesario para escalarla al pintarla.
     */
    private BufferedImage image;

    // -----------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------

    /**
     * Crea un nuevo panel y establece su tamaño preferido a 150×150 píxeles.
     * El LayoutManager del contenedor padre puede ignorar este tamaño,
     * pero sirve de referencia inicial para el layout.
     */
    public MyPane() {
        // setPreferredSize indica al layout manager cuánto espacio preferimos.
        // Dimension(ancho, alto) en píxeles.
        setPreferredSize(new Dimension(150, 150));
    }

    // -----------------------------------------------------------------------
    // GETTERS Y SETTERS
    // -----------------------------------------------------------------------

    /**
     * Devuelve el identificador numérico de la imagen actualmente cargada.
     *
     * @return Entero con el id de la imagen (p.ej. 3 → "Imagen3.JPG").
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Establece el identificador numérico de la imagen.
     * No recarga la imagen; hay que llamar también a setImage().
     *
     * @param imageId Nuevo identificador de imagen.
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Devuelve la imagen actualmente almacenada en este panel.
     *
     * @return BufferedImage cargada, o null si todavía no se ha asignado ninguna.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Asigna una nueva imagen al panel y solicita su redibujado inmediato.
     *
     * @param image Nueva imagen a mostrar. Puede ser null para limpiar el panel.
     */
    public void setImage(BufferedImage image) {
        this.image = image; // Almacena la referencia a la nueva imagen en memoria

        // repaint() le indica a Swing que este componente necesita redibujarse.
        // Swing encola la petición y llama a paintComponent() en el hilo de eventos (EDT).
        repaint();
    }

    // -----------------------------------------------------------------------
    // PINTADO DEL COMPONENTE
    // -----------------------------------------------------------------------

    /**
     * Método que Swing llama automáticamente cada vez que el panel
     * necesita ser (re)dibujado (al mostrarse por primera vez, al redimensionar,
     * o al llamar a repaint()).
     *
     * @param g Objeto Graphics que proporciona el contexto de dibujo 2D.
     *          Swing lo crea y gestiona internamente; no hay que cerrar ni liberar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // SIEMPRE llamamos al método padre primero.
        // super.paintComponent() limpia el fondo del panel (lo rellena con el color
        // de fondo) antes de que dibujemos encima. Sin esto podría haber artefactos.
        super.paintComponent(g);

        // Solo dibujamos si realmente hay una imagen asignada
        if (image != null) {
            // Obtenemos el tamaño ACTUAL del panel en píxeles.
            // Esto permite que la imagen se escale correctamente si el usuario
            // redimensiona la ventana.
            int panelW = getWidth();  // Anchura actual del panel en píxeles
            int panelH = getHeight(); // Altura actual del panel en píxeles

            // drawImage dibuja la imagen 'image' dentro del panel.
            // Parámetros: imagen, x_destino, y_destino, ancho_destino, alto_destino, observer.
            // Al pasar panelW y panelH como destino, la imagen se estira/encoge para
            // rellenar exactamente el panel (sin mantener proporciones).
            // 'this' es el ImageObserver: avisa a Swing si la imagen carga de forma asíncrona.
            g.drawImage(image, 0, 0, panelW, panelH, this);
        }
    }
}