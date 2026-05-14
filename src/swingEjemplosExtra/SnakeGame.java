package swingEjemplosExtra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JFrame {

    private static final int TILE_SIZE = 30;  // Aumenté el tamaño de los tiles
    private static final int WIDTH = 30;     // Aumenté el ancho del tablero
    private static final int HEIGHT = 20;    // Aumenté la altura del tablero
    private static final int ALL_TILES = WIDTH * HEIGHT;
    private static final int DELAY = 120;    // Ajusté la velocidad

    private final int[] x = new int[ALL_TILES];
    private final int[] y = new int[ALL_TILES];

    private int bodyParts = 3;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction = 'R'; // U, D, L, R
    private boolean running = false;
    private Timer timer;
    private Random random;

    public SnakeGame() {
        random = new Random();
        this.setTitle("Snake Game - Ventana Grande");
        this.setSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new GamePanel());
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        startGame();
    }

    private void startGame() {
        // Posición inicial de la serpiente
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 5 * TILE_SIZE - i * TILE_SIZE;
            y[i] = 5 * TILE_SIZE;
        }
        
        newApple();
        running = true;
        timer = new Timer(DELAY, new GameLoop());
        timer.start();
    }

    private void newApple() {
        appleX = random.nextInt(WIDTH) * TILE_SIZE;
        appleY = random.nextInt(HEIGHT) * TILE_SIZE;
    }

    private void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] = y[0] - TILE_SIZE;
            case 'D' -> y[0] = y[0] + TILE_SIZE;
            case 'L' -> x[0] = x[0] - TILE_SIZE;
            case 'R' -> x[0] = x[0] + TILE_SIZE;
        }
    }

    private void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    private void checkCollisions() {
        // Check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // Check borders
        if (x[0] < 0 || x[0] >= WIDTH * TILE_SIZE || 
            y[0] < 0 || y[0] >= HEIGHT * TILE_SIZE) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    private class GameLoop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (running) {
                move();
                checkApple();
                checkCollisions();
            }
            repaint();
        }
    }

    private class GamePanel extends JPanel implements KeyListener {

        public GamePanel() {
            this.setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
            this.setBackground(new Color(20, 20, 20)); // Fondo más oscuro
            this.setFocusable(true);
            this.addKeyListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (running) {
                // Draw grid (opcional)
                g.setColor(new Color(30, 30, 30));
                for (int i = 0; i < WIDTH; i++) {
                    for (int j = 0; j < HEIGHT; j++) {
                        g.drawRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
                }

                // Draw apple
                g.setColor(Color.RED);
                g.fillOval(appleX, appleY, TILE_SIZE, TILE_SIZE);

                // Draw snake
                for (int i = 0; i < bodyParts; i++) {
                    if (i == 0) {
                        // Cabeza de la serpiente
                        g.setColor(new Color(0, 200, 0));
                        g.fillRoundRect(x[i], y[i], TILE_SIZE, TILE_SIZE, 10, 10);
                        // Ojos
                        g.setColor(Color.WHITE);
                        g.fillOval(x[i] + 5, y[i] + 5, 8, 8);
                        g.fillOval(x[i] + TILE_SIZE - 13, y[i] + 5, 8, 8);
                    } else {
                        // Cuerpo de la serpiente
                        g.setColor(new Color(0, 180, 0));
                        g.fillRoundRect(x[i], y[i], TILE_SIZE, TILE_SIZE, 5, 5);
                    }
                }

                // Draw score
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 25));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Puntos: " + applesEaten, 20, 30);
            } else {
                gameOver(g);
            }
        }

        private void gameOver(Graphics g) {
            // Fondo semitransparente
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

            // Game Over text
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("GAME OVER", 
                    (WIDTH * TILE_SIZE - metrics.stringWidth("GAME OVER")) / 2, 
                    HEIGHT * TILE_SIZE / 2 - 50);

            // Score text
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            metrics = getFontMetrics(g.getFont());
            g.drawString("Puntuación final: " + applesEaten, 
                    (WIDTH * TILE_SIZE - metrics.stringWidth("Puntuación final: " + applesEaten)) / 2, 
                    HEIGHT * TILE_SIZE / 2 + 20);

            // Restart instruction
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Presiona ESPACIO para reiniciar", 
                    (WIDTH * TILE_SIZE - metrics.stringWidth("Presiona ESPACIO para reiniciar")) / 2, 
                    HEIGHT * TILE_SIZE / 2 + 70);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
                case KeyEvent.VK_SPACE:
                    if (!running) {
                        // Reiniciar el juego
                        bodyParts = 3;
                        applesEaten = 0;
                        direction = 'R';
                        startGame();
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}