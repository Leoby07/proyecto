package Proyecto;

import java.awt.*;
import javax.swing.*;

public class PantallaCarga extends JFrame {
    private JLabel mensaje;
    private JButton ingresar;

    public PantallaCarga() {
        super("Cinema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        mensaje = new JLabel("Bienvenido al cine");
        mensaje.setFont(new Font("Arial", Font.BOLD, 24));
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        add(mensaje, BorderLayout.CENTER);

        ingresar = new JButton("Ingresar");
        ingresar.setFont(new Font("Arial", Font.PLAIN, 18));
        ingresar.addActionListener(e -> {
            dispose();
            Cine cine = new Cine();
        });
        add(ingresar, BorderLayout.SOUTH);

        setVisible(true);
    }
}