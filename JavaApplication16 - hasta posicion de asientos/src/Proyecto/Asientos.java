package Proyecto; 

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class Asientos extends JFrame implements ActionListener { 

    
    private JLabel titulo; 
    private JLabel pregunta; 
    private JTextField campo; 
    private JButton confirmar; 
    private JButton regresar; 
    private String pelicula; 
    private String horario; 

    public Asientos(String pelicula, String horario) { // el constructor de la clase que recibe el nombre de la película y el horario como parámetros
        super("Reservaciones de cine"); // llamamos al constructor de la superclase con el título de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // establecemos que la ventana se cierre al presionar la X
        this.setSize(800, 600); // establecemos el tamaño de la ventana
        this.setLayout(new BorderLayout()); // establecemos el layout de la ventana como BorderLayout

        // asignamos los valores de los parámetros a los atributos de la clase
        this.pelicula = pelicula;
        this.horario = horario;

        // creamos el label del título y lo añadimos al norte de la ventana
        titulo = new JLabel("Has seleccionado la película " + pelicula + " en el horario " + horario + ".");
        titulo.setFont(new Font("Arial", Font.BOLD, 24)); // le damos un formato al label
        titulo.setHorizontalAlignment(JLabel.CENTER); // lo alineamos al centro
        this.add(titulo, BorderLayout.NORTH); // lo añadimos al norte de la ventana

        // creamos el label de la pregunta y lo añadimos al centro de la ventana
        pregunta = new JLabel("¿Cuántos asientos quieres reservar?");
        pregunta.setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al label
        pregunta.setHorizontalAlignment(JLabel.CENTER); // lo alineamos al centro
        this.add(pregunta, BorderLayout.CENTER); // lo añadimos al centro de la ventana

        // creamos el campo de texto y lo añadimos al este de la ventana
        campo = new JTextField(10); // creamos el campo de texto con un tamaño de 10 caracteres
        campo.setFont(new Font("Arial", Font.PLAIN, 18)); // le damos un formato al campo de texto
        campo.setHorizontalAlignment(JTextField.CENTER); // lo alineamos al centro
        this.add(campo, BorderLayout.EAST); // lo añadimos al este de la ventana

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

    Asientos(String pelicula, String horario, int cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // el método que se ejecuta cuando se presiona un botón
    public void actionPerformed(ActionEvent e) {
        // si el botón presionado es el de confirmar
        if (e.getSource() == confirmar) {
        try {
            int cantidad = Integer.parseInt(campo.getText()); // obtenemos la cantidad de asientos ingresada en el campo de texto y la convertimos a un entero
            if (cantidad > 0) { // si la cantidad es positiva
                this.dispose(); // cerramos esta ventana
                Posicion posicion = new Posicion(pelicula, horario, cantidad); //
                    JOptionPane.showMessageDialog(this, "Has reservado " + cantidad + " asientos para la película " + pelicula + " en el horario " + horario + "."); // mostramos un mensaje con la confirmación de la reserva
                } else { // si la cantidad es negativa o cero
                    JOptionPane.showMessageDialog(this, "La cantidad de asientos debe ser mayor que cero."); // mostramos un mensaje de error
                }
            } catch (NumberFormatException ex) { // si la cantidad no es un número válido
                JOptionPane.showMessageDialog(this, "La cantidad de asientos debe ser un número entero."); // mostramos un mensaje de error
            }
        }

        // si el botón presionado es el de regresar
        if (e.getSource() == regresar) {
            this.dispose(); // cerramos esta ventana
            Horarios horarios;
        }
    }
}
