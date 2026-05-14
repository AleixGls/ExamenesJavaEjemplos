package swingIASlots; // Define el paquete al que pertenece esta clase

// Importaciones necesarias
import javax.swing.*;          // JPanel y componentes Swing
import java.awt.*;             // Graphics, Dimension
import java.awt.image.BufferedImage; // Representa una imagen cargada en memoria con acceso a píxeles

/**
 * Panel personalizado que extiende JPanel.
 * Actúa como una "ranura" (slot) visual que muestra una imagen escalada
 * al tamaño del panel.
 *
 * Cada instancia almacena:
 *   - Un identificador numérico de la imagen actualmente visible (idImage).
 *   - La propia imagen en memoria (BufferedImage imatge).
 *
 * Se usa en Frame2 para mostrar los 5 paneles de la máquina tragaperras.
 */
public class PanelPersonalitzat extends JPanel {

    // -----------------------------------------------------------------------
    // ATRIBUTOS
    // -----------------------------------------------------------------------

    /**
     * Identificador numérico de la imagen actualmente cargada en este panel (1..TOTAL_IMAGES).
     * Se usa en Frame2 para contar cuántos paneles tienen el mismo id tras cada tirada.
     */
    private int idImage;

    /**
     * Imagen almacenada en memoria lista para pintarse.
     * BufferedImage permite acceder y manipular los píxeles directamente,
     * aunque aquí solo se usa para dibujarla escalada.
     */
    private BufferedImage imatge;

    // -----------------------------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------------------------

    /**
     * Crea un nuevo panel y establece su tamaño preferido a 100×100 píxeles.
     * El LayoutManager del contenedor padre puede ignorar este valor,
     * pero sirve de referencia inicial para el layout.
     */
    public PanelPersonalitzat() {
        // Indica al layout manager cuánto espacio prefiere este componente.
        // Dimension(ancho, alto) en píxeles.
        setPreferredSize(new Dimension(100, 100));
    }

    // -----------------------------------------------------------------------
    // GETTERS Y SETTERS
    // -----------------------------------------------------------------------

    /**
     * Devuelve el identificador numérico de la imagen actualmente cargada.
     *
     * @return Entero con el id de imagen (p.ej. 3 → "Imagen3.JPG").
     */
    public int getIdImage() {
        return idImage;
    }

    /**
     * Establece el identificador numérico de la imagen.
     * No recarga la imagen en sí; hay que llamar también a setImatge().
     *
     * @param idImage Nuevo identificador de imagen.
     */
    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    /**
     * Devuelve la imagen actualmente almacenada en este panel.
     *
     * @return BufferedImage cargada, o null si aún no se ha asignado ninguna.
     */
    public BufferedImage getImatge() {
        return imatge;
    }

    /**
     * Asigna una nueva imagen al panel y solicita su redibujado inmediato.
     *
     * @param imatge Nueva imagen a mostrar. Puede ser null para dejar el panel vacío.
     */
    public void setImatge(BufferedImage imatge) {
        this.imatge = imatge; // Almacena la referencia a la nueva imagen en memoria

        // repaint() notifica a Swing que este componente necesita redibujarse.
        // Swing encola la petición y llama a paint() en el hilo de eventos (EDT).
        repaint();
    }

    // -----------------------------------------------------------------------
    // PINTADO DEL COMPONENTE
    // -----------------------------------------------------------------------

    /**
     * Método que Swing llama automáticamente cada vez que el panel
     * necesita ser (re)dibujado: al mostrarse por primera vez, al redimensionar
     * la ventana, o al invocar repaint().
     *
     * NOTA: se sobreescribe paint() en lugar de paintComponent().
     * paint() también repinta los bordes y los componentes hijos,
     * mientras que paintComponent() solo pinta el fondo del propio panel.
     * Para un panel simple sin hijos, ambas opciones producen el mismo resultado visual.
     *
     * @param g Objeto Graphics que proporciona el contexto de dibujo 2D.
     *          Swing lo crea y gestiona internamente; no hay que liberarlo.
     */
    @Override
    public void paint(Graphics g) {
        // SIEMPRE llamamos al método padre primero.
        // super.paint() limpia el fondo del panel con el color de fondo configurado
        // y dibuja los posibles componentes hijos antes de que añadamos nuestra imagen.
        super.paint(g);

        // Solo dibujamos si hay una imagen asignada
        if (imatge != null) {
            // drawImage escala y dibuja 'imatge' para que ocupe exactamente el panel.
            // Parámetros:
            //   imatge         → la imagen a dibujar
            //   0, 0           → esquina superior izquierda del destino dentro del panel
            //   this.getWidth()  → anchura actual del panel (escala horizontal)
            //   this.getHeight() → altura actual del panel (escala vertical)
            //   null           → no se necesita ImageObserver porque la imagen ya está
            //                    completamente cargada en memoria (BufferedImage)
            g.drawImage(imatge, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}