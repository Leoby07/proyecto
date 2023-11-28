package Proyecto; // el nombre del paquete es Proyecto

import java.awt.*; // importamos la librería para crear la interfaz gráfica
import java.awt.event.*; // importamos la librería para manejar los eventos de los botones
import javax.swing.*; // importamos la librería para usar componentes Swing

public class Cine extends JFrame implements ActionListener { // la clase se llama Cine y hereda de JFrame e implementa la interfaz ActionListener

    // declaramos los atributos de la clase
    private JLabel titulo; // un label para mostrar el título de la página
    private JPanel panelPeliculas; // un panel para mostrar las películas disponibles
    private JButton[] botones; // un arreglo de botones para seleccionar una película
    private String[] peliculas; // un arreglo de strings con los nombres de las películas

    public Cine() { // el constructor de la clase
        super("Reservaciones de cine"); // llamamos al constructor de la superclase con el título de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // establecemos que la ventana se cierre al presionar la X
        this.setSize(800, 600); // establecemos el tamaño de la ventana
        this.setLayout(new BorderLayout()); // establecemos el layout de la ventana como BorderLayout

        // creamos el label del título y lo añadimos al norte de la ventana
        titulo = new JLabel("Bienvenido al cine. Estas son las películas disponibles:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); // le damos un formato al label
        titulo.setHorizontalAlignment(JLabel.CENTER); // lo alineamos al centro
        this.add(titulo, BorderLayout.NORTH); // lo añadimos al norte de la ventana

        // creamos el panel de las películas y lo añadimos al centro de la ventana
        panelPeliculas = new JPanel(); // creamos el panel
        panelPeliculas.setLayout(new GridLayout(2, 3)); // le damos un layout de GridLayout con 2 filas y 3 columnas
        this.add(panelPeliculas, BorderLayout.CENTER); // lo añadimos al centro de la ventana

        // creamos el arreglo de las películas con los nombres
        peliculas = new String[] {"Avatar", "Titanic", "The Avengers", "The Matrix", "The Lion King", "The Godfather"};

        // creamos el arreglo de los botones con el mismo tamaño que el arreglo de las películas
        botones = new JButton[peliculas.length];

        // recorremos el arreglo de las películas y creamos un botón para cada una
        for (int i = 0; i < peliculas.length; i++) {
            botones[i] = new JButton(peliculas[i]); // creamos el botón con el nombre de la película
            botones[i].setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al botón
            botones[i].addActionListener(this); // le añadimos el listener para que responda al clic
            panelPeliculas.add(botones[i]); // añadimos el botón al panel de las películas
        }

        // hacemos visible la ventana
        this.setVisible(true);
    }

    // el método que se ejecuta cuando se presiona un botón
    @Override
    public void actionPerformed(ActionEvent e) {
    // recorremos el arreglo de los botones para ver cuál fue el que se presionó
    for (int i = 0; i < botones.length; i++) {
        if (e.getSource() == botones[i]) { // si el botón presionado es el i-ésimo
            this.dispose(); // cerramos esta ventana
            Horarios horarios; // creamos una nueva instancia de la clase Horarios con el nombre de la película
            horarios = new Horarios(peliculas[i]) {
                
            };
            break; // salimos del ciclo
        }
    }
}
}