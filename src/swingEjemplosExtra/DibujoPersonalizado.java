package swingEjemplosExtra;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class DibujoPersonalizado extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        // Configuración inicial
        setBackground(Color.WHITE);
        
        // Dibujar formas básicas
        dibujarFormasBasicas(g2d);
        
        // Dibujar texto
        dibujarTexto(g2d);
        
        // Dibujar formas complejas
        dibujarFormasComplejas(g2d);
        
        // Dibujar un placeholder de imagen (sin intentar cargar archivo)
        dibujarPlaceholderImagen(g2d);
    }
    
    private void dibujarFormasBasicas(Graphics2D g2d) {
        // Rectángulo
        g2d.setColor(Color.BLUE);
        g2d.drawRect(50, 50, 100, 80);
        
        // Rectángulo relleno con transparencia
        g2d.setColor(new Color(255, 0, 0, 128));
        g2d.fillRect(200, 50, 100, 80);
        
        // Óvalo
        g2d.setColor(Color.GREEN);
        g2d.drawOval(350, 50, 100, 80);
        
        // Óvalo relleno
        g2d.setColor(new Color(0, 0, 255, 100));
        g2d.fillOval(500, 50, 100, 80);
        
        // Línea
        g2d.setColor(Color.BLACK);
        g2d.drawLine(50, 150, 600, 150);
    }
    
    private void dibujarTexto(Graphics2D g2d) {
        // Texto simple
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 24);
        g2d.setFont(font);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("Dibujando en Swing", 50, 200);
        
        // Texto girado
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(-15), 50, 250);
        g2d.setTransform(transform);
        g2d.setColor(Color.MAGENTA);
        g2d.drawString("Texto girado", 50, 250);
        
        // Restaurar transformación
        g2d.setTransform(new AffineTransform());
    }
    
    private void dibujarFormasComplejas(Graphics2D g2d) {
        // Polígono
        int[] xPoints = {50, 100, 150, 100};
        int[] yPoints = {300, 250, 300, 350};
        g2d.setColor(new Color(255, 165, 0));
        g2d.fillPolygon(xPoints, yPoints, 4);
        
        // Estrella
        GeneralPath estrella = new GeneralPath();
        estrella.moveTo(250, 275);
        estrella.lineTo(275, 325);
        estrella.lineTo(225, 325);
        estrella.closePath();
        estrella.moveTo(225, 300);
        estrella.lineTo(275, 300);
        estrella.lineTo(250, 350);
        g2d.setColor(Color.YELLOW);
        g2d.fill(estrella);
        
        // Arco
        g2d.setColor(Color.CYAN);
        g2d.drawArc(350, 275, 100, 100, 45, 270);
        
        // Curva cuadrática
        QuadCurve2D curva = new QuadCurve2D.Float(
            500, 300, // punto inicial
            550, 250, // punto de control
            600, 300  // punto final
        );
        g2d.setColor(Color.PINK);
        g2d.draw(curva);
    }
    
    private void dibujarPlaceholderImagen(Graphics2D g2d) {
        // Dibujar un placeholder en lugar de intentar cargar una imagen
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(400, 350, 150, 100);
        g2d.drawString("Área de imagen", 410, 400);
        
        // Dibujar un icono simple
        g2d.setColor(new Color(200, 230, 255));
        g2d.fillRect(410, 360, 130, 80);
        g2d.setColor(Color.BLUE);
        g2d.drawLine(420, 370, 530, 430);
        g2d.drawLine(420, 430, 530, 370);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dibujo Personalizado en Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            
            DibujoPersonalizado panel = new DibujoPersonalizado();
            frame.add(panel);
            
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}