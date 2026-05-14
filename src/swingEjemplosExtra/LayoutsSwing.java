package swingEjemplosExtra;

import javax.swing.*;
import java.awt.*;

public class LayoutsSwing extends JFrame {
    
    public LayoutsSwing() {
        // Configuración básica de la ventana
        setTitle("Ejemplo de Layouts en Swing");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear pestañas para cada layout
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // 1. FlowLayout
        tabbedPane.addTab("FlowLayout", crearPanelFlowLayout());
        
        // 2. BorderLayout
        tabbedPane.addTab("BorderLayout", crearPanelBorderLayout());
        
        // 3. GridLayout
        tabbedPane.addTab("GridLayout", crearPanelGridLayout());
        
        // 4. GridBagLayout
        tabbedPane.addTab("GridBagLayout", crearPanelGridBagLayout());
        
        // 5. BoxLayout
        tabbedPane.addTab("BoxLayout", crearPanelBoxLayout());
        
        // 6. GroupLayout
        tabbedPane.addTab("GroupLayout", crearPanelGroupLayout());
        
        add(tabbedPane);
    }
    
    // 1. FlowLayout - Los componentes fluyen en línea
    private JPanel crearPanelFlowLayout() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        panel.add(new JButton("Botón 1"));
        panel.add(new JButton("Botón 2"));
        panel.add(new JButton("Botón 3"));
        panel.add(new JButton("Botón Largo con Texto"));
        panel.add(new JButton("5"));
        
        return panel;
    }
    
    // 2. BorderLayout - Divide el panel en 5 zonas
    private JPanel crearPanelBorderLayout() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        
        panel.add(new JButton("Norte"), BorderLayout.NORTH);
        panel.add(new JButton("Sur"), BorderLayout.SOUTH);
        panel.add(new JButton("Este"), BorderLayout.EAST);
        panel.add(new JButton("Oeste"), BorderLayout.WEST);
        panel.add(new JButton("Centro"), BorderLayout.CENTER);
        
        return panel;
    }
    
    // 3. GridLayout - Matriz de celdas del mismo tamaño
    private JPanel crearPanelGridLayout() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        panel.add(new JButton("Fila 1, Col 1"));
        panel.add(new JButton("Fila 1, Col 2"));
        panel.add(new JButton("Fila 2, Col 1"));
        panel.add(new JButton("Fila 2, Col 2"));
        panel.add(new JButton("Fila 3, Col 1"));
        panel.add(new JButton("Fila 3, Col 2"));
        
        return panel;
    }
    
    // 4. GridBagLayout - El más flexible y complejo
    private JPanel crearPanelGridBagLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Fila 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JButton("Botón 1"), gbc);
        
        gbc.gridx = 1;
        panel.add(new JButton("Botón 2"), gbc);
        
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        panel.add(new JButton("Botón Ancho"), gbc);
        
        // Fila 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        panel.add(new JButton("Alto"), gbc);
        
        gbc.gridheight = 1;
        gbc.gridx = 1;
        panel.add(new JButton("Normal"), gbc);
        
        gbc.gridx = 2;
        panel.add(new JButton("Normal"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(new JButton("Ancho"), gbc);
        
        return panel;
    }
    
    // 5. BoxLayout - Organiza componentes en línea o columna
    private JPanel crearPanelBoxLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JButton("Botón 1"));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(new JButton("Botón 2"));
        panel.add(Box.createVerticalGlue());
        panel.add(new JButton("Botón 3"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(new JButton("Botón 4"));
        
        return panel;
    }
    
    // 6. GroupLayout - Usado comúnmente en builders de IDEs
    private JPanel crearPanelGroupLayout() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
        JButton btn1 = new JButton("Botón 1");
        JButton btn2 = new JButton("Botón 2");
        JButton btn3 = new JButton("Botón 3");
        
        // Configuración horizontal
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btn1)
                    .addGap(10)
                    .addComponent(btn2))
                .addComponent(btn3, GroupLayout.DEFAULT_SIZE, 
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        // Configuración vertical
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1)
                    .addComponent(btn2))
                .addGap(10)
                .addComponent(btn3)
        );
        
        return panel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LayoutsSwing().setVisible(true);
            }
        });
    }
}