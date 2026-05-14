package swingEjemplosExtra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CambioFondoAleatorio extends JFrame {
    
    public CambioFondoAleatorio() {
        // Configuración básica de la ventana
        setTitle("Cambio de Fondo Aleatorio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear el botón
        JButton cambiarColorBtn = new JButton("Cambiar Color de Fondo");
        
        // Añadir acción al botón
        cambiarColorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarFondoAleatorio();
            }
        });
        
        // Configurar el layout y añadir el botón
        setLayout(new FlowLayout());
        add(cambiarColorBtn);
    }
    
    private void cambiarFondoAleatorio() {
        Random rand = new Random();
        
        // Generar componentes RGB aleatorios
        int r = rand.nextInt(256); // Rojo (0-255)
        int g = rand.nextInt(256); // Verde (0-255)
        int b = rand.nextInt(256); // Azul (0-255)
        
        // Crear el color
        Color nuevoColor = new Color(r, g, b);
        
        // Cambiar el fondo del content pane
        getContentPane().setBackground(nuevoColor);
    }
    
    public static void main(String[] args) {
        new CambioFondoAleatorio().setVisible(true);
    }
}