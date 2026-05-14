package swingEjemplosExtra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_SIZE = 15;
    private static final int PADDLE_SPEED = 5;
    private static final int BALL_SPEED = 3;
    
    private int player1Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int player2Y = HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int ballX = WIDTH / 2 - BALL_SIZE / 2;
    private int ballY = HEIGHT / 2 - BALL_SIZE / 2;
    private int ballXDir = BALL_SPEED;
    private int ballYDir = BALL_SPEED;
    
    private int player1Score = 0;
    private int player2Score = 0;
    
    private boolean up1Pressed = false;
    private boolean down1Pressed = false;
    private boolean up2Pressed = false;
    private boolean down2Pressed = false;
    
    public PongGame() {
        setTitle("Pong Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), true);
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), false);
            }
        });
        
        Timer timer = new Timer(16, e -> {
            updateGame();
            repaint();
        });
        timer.start();
    }
    
    private void handleKeyPress(int keyCode, boolean pressed) {
        switch (keyCode) {
            case KeyEvent.VK_W -> up1Pressed = pressed;
            case KeyEvent.VK_S -> down1Pressed = pressed;
            case KeyEvent.VK_UP -> up2Pressed = pressed;
            case KeyEvent.VK_DOWN -> down2Pressed = pressed;
        }
    }
    
    private void updateGame() {
        // Mover paletas
        if (up1Pressed && player1Y > 0) {
            player1Y -= PADDLE_SPEED;
        }
        if (down1Pressed && player1Y < HEIGHT - PADDLE_HEIGHT) {
            player1Y += PADDLE_SPEED;
        }
        if (up2Pressed && player2Y > 0) {
            player2Y -= PADDLE_SPEED;
        }
        if (down2Pressed && player2Y < HEIGHT - PADDLE_HEIGHT) {
            player2Y += PADDLE_SPEED;
        }
        
        // Mover pelota
        ballX += ballXDir;
        ballY += ballYDir;
        
        // Rebotes en los bordes superior e inferior
        if (ballY <= 0 || ballY >= HEIGHT - BALL_SIZE) {
            ballYDir *= -1;
        }
        
        // Rebotes en las paletas
        if (ballX <= PADDLE_WIDTH && 
            ballY + BALL_SIZE >= player1Y && 
            ballY <= player1Y + PADDLE_HEIGHT) {
            ballXDir = BALL_SPEED;
        }
        
        if (ballX >= WIDTH - PADDLE_WIDTH - BALL_SIZE && 
            ballY + BALL_SIZE >= player2Y && 
            ballY <= player2Y + PADDLE_HEIGHT) {
            ballXDir = -BALL_SPEED;
        }
        
        // Puntos
        if (ballX < 0) {
            player2Score++;
            resetBall();
        }
        if (ballX > WIDTH - BALL_SIZE) {
            player1Score++;
            resetBall();
        }
    }
    
    private void resetBall() {
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        ballXDir = (Math.random() > 0.5) ? BALL_SPEED : -BALL_SPEED;
        ballYDir = (Math.random() > 0.5) ? BALL_SPEED : -BALL_SPEED;
    }
    
    @Override
    public void paint(Graphics g) {
        // Limpiar pantalla
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Dibujar paletas
        g.setColor(Color.WHITE);
        g.fillRect(0, player1Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillRect(WIDTH - PADDLE_WIDTH, player2Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        
        // Dibujar pelota
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
        
        // Dibujar línea central
        for (int i = 0; i < HEIGHT; i += 20) {
            g.fillRect(WIDTH / 2 - 1, i, 2, 10);
        }
        
        // Dibujar marcador
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(String.valueOf(player1Score), WIDTH / 4, 50);
        g.drawString(String.valueOf(player2Score), 3 * WIDTH / 4, 50);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PongGame game = new PongGame();
            game.setVisible(true);
        });
    }
}