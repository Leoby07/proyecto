package Proyecto; // el nombre del paquete es Proyecto

import java.awt.*; // importamos la librería para crear la interfaz gráfica
import java.awt.event.*; // importamos la librería para manejar los eventos de los botones
import javax.swing.*; // importamos la librería para usar componentes Swing

public class Horarios extends JFrame implements ActionListener { // la clase se llama Horarios y hereda de JFrame e implementa la interfaz ActionListener

    // declaramos los atributos de la clase
    private JLabel titulo; // un label para mostrar el título de la página
    private JPanel panelHorarios; // un panel para mostrar los horarios disponibles
    private JButton[] botones; // un arreglo de botones para seleccionar un horario
    private String[] horarios; // un arreglo de strings con los horarios disponibles
    private JButton regresar; // un botón para regresar a la página anterior

    public Horarios(String pelicula) { // el constructor de la clase que recibe el nombre de la película como parámetro
        super("Reservaciones de cine"); // llamamos al constructor de la superclase con el título de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // establecemos que la ventana se cierre al presionar la X
        this.setSize(800, 600); // establecemos el tamaño de la ventana
        this.setLayout(new BorderLayout()); // establecemos el layout de la ventana como BorderLayout

        // creamos el label del título y lo añadimos al norte de la ventana
        titulo = new JLabel("Has seleccionado la película " + pelicula + ". Estos son los horarios disponibles:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); // le damos un formato al label
        titulo.setHorizontalAlignment(JLabel.CENTER); // lo alineamos al centro
        this.add(titulo, BorderLayout.NORTH); // lo añadimos al norte de la ventana

        // creamos el panel de los horarios y lo añadimos al centro de la ventana
        panelHorarios = new JPanel(); // creamos el panel
        panelHorarios.setLayout(new GridLayout(2, 3)); // le damos un layout de GridLayout con 2 filas y 3 columnas
        this.add(panelHorarios, BorderLayout.CENTER); // lo añadimos al centro de la ventana

        // creamos el arreglo de los horarios con algunos valores
        horarios = new String[] {"10:00 am", "12:00 pm", "2:00 pm", "4:00 pm", "6:00 pm", "8:00 pm"};

        // creamos el arreglo de los botones con el mismo tamaño que el arreglo de los horarios
        botones = new JButton[horarios.length];

        // recorremos el arreglo de los horarios y creamos un botón para cada uno
        for (int i = 0; i < horarios.length; i++) {
            botones[i] = new JButton(horarios[i]); // creamos el botón con el horario
            botones[i].setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al botón
            botones[i].addActionListener(this); // le añadimos el listener para que responda al clic
            panelHorarios.add(botones[i]); // añadimos el botón al panel de los horarios
        }

        // creamos el botón de regresar y lo añadimos al sur de la ventana
        regresar = new JButton("Regresar"); // creamos el botón con el texto "Regresar"
        regresar.setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al botón
        regresar.addActionListener(this); // le añadimos el listener para que responda al clic
        this.add(regresar, BorderLayout.SOUTH); // lo añadimos al sur de la ventana

        // hacemos visible la ventana
        this.setVisible(true);
    }

    // el método que se ejecuta cuando se presiona un botón
    @Override
    public void actionPerformed(ActionEvent e) {
        // recorremos el arreglo de los botones para ver cuál fue el que se presionó
        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) { // si el botón presionado es el i-ésimo
                JOptionPane.showMessageDialog(this, "Has seleccionado el horario " + horarios[i]); // mostramos un mensaje con el horario
                break; // salimos del ciclo
            }
        }

        // si el botón presionado es el de regresar
        if (e.getSource() == regresar) {
            this.dispose(); // cerramos esta ventana
            Cine cine = new Cine(); // creamos una nueva instancia de la clase Cine
        }
    }
}