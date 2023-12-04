package Proyecto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Importar la clase JButton
import javax.swing.JButton;

public class Cine extends JFrame implements ActionListener {
    private JLabel titulo;
    private JPanel panelPeliculas;
    private JButton[] botones;
    private String[] peliculas;
    private String[] imagenes; // Agregar un arreglo para las imágenes

    public Cine() {
        super("Reservaciones de cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        titulo = new JLabel("Bienvenido al cine. Estas son las películas disponibles:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        panelPeliculas = new JPanel();
        panelPeliculas.setLayout(new GridLayout(2, 3));
        add(panelPeliculas, BorderLayout.CENTER);

        // Crear un arreglo con los nombres de las películas
        peliculas = new String[]{"Spiderman 2", "FNAF", "Gato con Botas", "Saw X", "Barbie", "Morbius"};
        // Crear un arreglo con los nombres de las imágenes de java
        imagenes = new String[]{"spider.jpeg","fnaf.jpeg", "gato.jpeg","saw.jpeg","Barbie.jpeg","morbius.jpeg"};
        botones = new JButton[peliculas.length];

        // Crear un ciclo for para recorrer los arreglos
        for (int i = 0; i < peliculas.length; i++) {
          // Crear un botón con el nombre de la película
          botones[i] = new JButton(peliculas[i]);
          // Agregar el nombre de la imagen al botón
          botones[i].setIcon(new ImageIcon(imagenes[i]));
          // Agregar el botón al panel o al marco
          panelPeliculas.add(botones[i]);
          // Agregar un escuchador de acción al botón
          botones[i].addActionListener(this);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) {
                dispose();
                Horarios horarios = new Horarios(peliculas[i]);
                break;
            }
        }
    }
}