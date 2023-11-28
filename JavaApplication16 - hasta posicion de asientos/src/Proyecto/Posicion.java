package Proyecto; // el nombre del paquete es Proyecto

import java.awt.*; // importamos la librería para crear la interfaz gráfica
import java.awt.event.*; // importamos la librería para manejar los eventos de los botones
import javax.swing.*; // importamos la librería para usar componentes Swing

public class Posicion extends JFrame implements ActionListener { // la clase se llama Posicion y hereda de JFrame e implementa la interfaz ActionListener

    // declaramos los atributos de la clase
    private JLabel titulo; // un label para mostrar el título de la página
    private JPanel panelAsientos; // un panel para mostrar los asientos disponibles
    private JButton[][] botones; // una matriz de botones para representar los asientos
    private int filas; // el número de filas de asientos
    private int columnas; // el número de columnas de asientos
    private int cantidad; // la cantidad de asientos que se quieren reservar
    private int reservados; // la cantidad de asientos que se han reservado
    private JButton confirmar; // un botón para confirmar la reserva
    private JButton regresar; // un botón para regresar a la página anterior
    private String pelicula; // un string para guardar el nombre de la película
    private String horario; // un string para guardar el horario seleccionado

    public Posicion(String pelicula, String horario, int cantidad) { // el constructor de la clase que recibe el nombre de la película, el horario y la cantidad de asientos como parámetros
        super("Reservaciones de cine"); // llamamos al constructor de la superclase con el título de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // establecemos que la ventana se cierre al presionar la X
        this.setSize(800, 600); // establecemos el tamaño de la ventana
        this.setLayout(new BorderLayout()); // establecemos el layout de la ventana como BorderLayout

        // asignamos los valores de los parámetros a los atributos de la clase
        this.pelicula = pelicula;
        this.horario = horario;
        this.cantidad = cantidad;
        this.reservados = 0; // inicializamos el contador de asientos reservados en cero

        // creamos el label del título y lo añadimos al norte de la ventana
        titulo = new JLabel("Has reservado " + cantidad + " asientos para la película " + pelicula + " en el horario " + horario + ".");
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); // le damos un formato al label
        titulo.setHorizontalAlignment(JLabel.CENTER); // lo alineamos al centro
        this.add(titulo, BorderLayout.NORTH); // lo añadimos al norte de la ventana

        // creamos el panel de los asientos y lo añadimos al centro de la ventana
        panelAsientos = new JPanel(); // creamos el panel
        filas = 5; // asignamos el número de filas de asientos
        columnas = 10; // asignamos el número de columnas de asientos
        panelAsientos.setLayout(new GridLayout(filas, columnas)); // le damos un layout de GridLayout con el número de filas y columnas
        this.add(panelAsientos, BorderLayout.CENTER); // lo añadimos al centro de la ventana

        // creamos la matriz de los botones con el número de filas y columnas
        botones = new JButton[filas][columnas];

        // recorremos la matriz de los botones y creamos un botón para cada posición
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton(); // creamos el botón
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al botón
                botones[i][j].setBackground(Color.GREEN); // le damos un color verde al botón para indicar que está disponible
                botones[i][j].addActionListener(this); // le añadimos el listener para que responda al clic
                panelAsientos.add(botones[i][j]); // añadimos el botón al panel de los asientos
            }
        }

        // creamos el botón de confirmar y lo añadimos al oeste de la ventana
        confirmar = new JButton("Confirmar"); // creamos el botón con el texto "Confirmar"
        confirmar.setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al botón
        confirmar.addActionListener(this); // le añadimos el listener para que responda al clic
        this.add(confirmar, BorderLayout.WEST); // lo añadimos al oeste de la ventana

        // creamos el botón de regresar y lo añadimos al sur de la ventana
        regresar = new JButton("Regresar"); // creamos el botón con el texto "Regresar"
        regresar.setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al botón
        regresar.addActionListener(this); // le añadimos el listener para que responda al clic
        this.add(regresar, BorderLayout.SOUTH); // lo añadimos al sur de la ventana

        // hacemos visible la ventana
        this.setVisible(true);
    }

    // el método que se ejecuta cuando se presiona un botón
    public void actionPerformed(ActionEvent e) {
        // recorremos la matriz de los botones para ver cuál fue el que se presionó
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (e.getSource() == botones[i][j]) { // si el botón presionado es el de la posición i, j
                    if (botones[i][j].getBackground() == Color.GREEN) { // si el botón está disponible
                        if (reservados < cantidad) { // si aún no se han reservado todos los asientos
                            botones[i][j].setBackground(Color.RED); // cambiamos el color del botón a rojo para indicar que está reservado
                            reservados++; // incrementamos el contador de asientos reservados
                        } else { // si ya se han reservado todos los asientos
                            JOptionPane.showMessageDialog(this, "Ya has reservado el máximo de asientos permitidos."); // mostramos un mensaje de error
                        }
                    } else if (botones[i][j].getBackground() == Color.RED) { // si el botón está reservado
                        botones[i][j].setBackground(Color.GREEN); // cambiamos el color del botón a verde para indicar que está disponible
                        reservados--; // decrementamos el contador de asientos reservados
                    }
                    break; // salimos del ciclo interno
                }
            }
        }

        // si el botón presionado es el de confirmar
        if (e.getSource() == confirmar) {
            if (reservados == cantidad) { // si se han reservado la cantidad de asientos solicitada
                JOptionPane.showMessageDialog(this, "Has confirmado tu reserva de " + cantidad + " asientos para la película " + pelicula + " en el horario " + horario + "."); // mostramos un mensaje con la confirmación de la reserva
            } else { // si no se han reservado la cantidad de asientos solicitada
                JOptionPane.showMessageDialog(this, "Debes reservar " + cantidad + " asientos para confirmar tu reserva."); // mostramos un mensaje de error
            }
        }

        // si el botón presionado es el de regresar
        if (e.getSource() == regresar) {
            this.dispose(); // cerramos esta ventana
            Asientos asientos = new Asientos(pelicula, horario, cantidad); // creamos una nueva instancia de la clase Asientos con el nombre de la película, el horario y la cantidad de asientos
        }
    }
}