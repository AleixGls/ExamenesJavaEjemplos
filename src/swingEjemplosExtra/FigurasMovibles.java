package swingEjemplosExtra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FigurasMovibles extends JFrame {

    private MovableCircle circle;
    private MovableSquare square;
    private DrawingPanel drawingPanel;

    public FigurasMovibles() {
        setTitle("Figuras con Límites");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel de dibujo con fondo blanco
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel);

        // Crear las figuras
        circle = new MovableCircle(100, 100, 50, Color.RED, drawingPanel);
        square = new MovableSquare(300, 200, 80, Color.BLUE, drawingPanel);

        // Configurar el key listener
        drawingPanel.setFocusable(true);
        drawingPanel.requestFocusInWindow();
        drawingPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                // Guardar posiciones originales
                int originalCircleX = circle.getPosicionX();
                int originalCircleY = circle.getPosicionY();
                int originalSquareX = square.getPosicionX();
                int originalSquareY = square.getPosicionY();
                
                // Calcular nuevas posiciones
                int newCircleX = originalCircleX;
                int newCircleY = originalCircleY;
                int newSquareX = originalSquareX;
                int newSquareY = originalSquareY;
                
                // Actualizar posiciones según teclas
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: newCircleY -= 5; break;
                    case KeyEvent.VK_DOWN: newCircleY += 5; break;
                    case KeyEvent.VK_LEFT: newCircleX -= 5; break;
                    case KeyEvent.VK_RIGHT: newCircleX += 5; break;
                    case KeyEvent.VK_W: newSquareY -= 5; break;
                    case KeyEvent.VK_S: newSquareY += 5; break;
                    case KeyEvent.VK_A: newSquareX -= 5; break;
                    case KeyEvent.VK_D: newSquareX += 5; break;
                }
                
                // Aplicar movimiento solo si es válido
                circle.tryMove(newCircleX, newCircleY);
                square.tryMove(newSquareX, newSquareY);
                
                // Verificar colisión entre figuras
                if (circle.collidesWith(square)) {
                    // Revertir movimiento si hay colisión
                    circle.setPosicion(originalCircleX, originalCircleY);
                    square.setPosicion(originalSquareX, originalSquareY);
                }
                
                drawingPanel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    // Panel de dibujo
    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            circle.draw(g);
            square.draw(g);
            
            // Dibujar mensaje de controles
            g.setColor(Color.BLACK);
            g.drawString("Círculo: Flechas | Cuadrado: WASD", 10, 20);
            g.drawString("No pueden salir de pantalla ni chocar", 10, 40);
        }
    }

    // Clase base abstracta para figuras
    private abstract class MovableShape {
        protected int x, y;
        protected Color color;
        protected JPanel parentPanel;
        
        public abstract void draw(Graphics g);
        public abstract boolean collidesWith(MovableShape other);
        public abstract boolean isMoveValid(int newX, int newY);
        
        // Métodos de posición
        public int getPosicionX() { return x; }
        public int getPosicionY() { return y; }
        
        public void setPosicion(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void tryMove(int newX, int newY) {
            if (isMoveValid(newX, newY)) {
                setPosicion(newX, newY);
            }
        }
    }

    // Clase del círculo
    private class MovableCircle extends MovableShape {
        private int radius;

        public MovableCircle(int x, int y, int radius, Color color, JPanel parentPanel) {
            setPosicion(x, y);
            this.radius = radius;
            this.color = color;
            this.parentPanel = parentPanel;
        }

        @Override
        public void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

        @Override
        public boolean isMoveValid(int newX, int newY) {
            // Verificar límites de pantalla
            return (newX - radius >= 0) && 
                   (newX + radius <= parentPanel.getWidth()) &&
                   (newY - radius >= 0) && 
                   (newY + radius <= parentPanel.getHeight());
        }

        @Override
        public boolean collidesWith(MovableShape other) {
            if (other instanceof MovableSquare) {
                MovableSquare square = (MovableSquare) other;
                return circleSquareCollision(this, square);
            }
            return false;
        }
        
        private boolean circleSquareCollision(MovableCircle circle, MovableSquare square) {
            int closestX = clamp(circle.x, square.x - square.size/2, square.x + square.size/2);
            int closestY = clamp(circle.y, square.y - square.size/2, square.y + square.size/2);
            
            int distanceX = circle.x - closestX;
            int distanceY = circle.y - closestY;
            
            return (distanceX * distanceX + distanceY * distanceY) < (radius * radius);
        }
        
        private int clamp(int value, int min, int max) {
            return Math.max(min, Math.min(max, value));
        }
    }

    // Clase del cuadrado
    private class MovableSquare extends MovableShape {
        protected int size;

        public MovableSquare(int x, int y, int size, Color color, JPanel parentPanel) {
            setPosicion(x, y);
            this.size = size;
            this.color = color;
            this.parentPanel = parentPanel;
        }

        @Override
        public void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.fillRect(x - size/2, y - size/2, size, size);
        }

        @Override
        public boolean isMoveValid(int newX, int newY) {
            // Verificar límites de pantalla
            return (newX - size/2 >= 0) && 
                   (newX + size/2 <= parentPanel.getWidth()) &&
                   (newY - size/2 >= 0) && 
                   (newY + size/2 <= parentPanel.getHeight());
        }

        @Override
        public boolean collidesWith(MovableShape other) {
            if (other instanceof MovableCircle) {
                MovableCircle circle = (MovableCircle) other;
                return circle.collidesWith(this);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FigurasMovibles example = new FigurasMovibles();
            example.setVisible(true);
        });
    }
}