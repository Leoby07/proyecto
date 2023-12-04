package Proyecto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Asientos extends JFrame implements ActionListener {
    private JLabel titulo;
    private JLabel preguntaCantidad;
    private JLabel preguntaTipoBoleto;
    private JTextField campoCantidad;
    private JComboBox<String> comboTipoBoleto;
    private JButton confirmar;
    private JButton regresar;
    private String pelicula;
    private String horario;
    private List<String> asientosLibres;
    private final String posicionesOcupadas;
    private String rutaImagen;

    public Asientos(String pelicula, String posicionesOcupadas) {
        super("Reservaciones de cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        this.pelicula = pelicula;
        this.posicionesOcupadas = posicionesOcupadas;

        // Obtener lista de asientos libres
        asientosLibres = obtenerAsientosLibres();


        titulo = new JLabel("Has seleccionado la película " + pelicula + " en el horario " + horario + ".");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        add(centerPanel, BorderLayout.CENTER);

        preguntaCantidad = new JLabel("¿Cuántos asientos quieres reservar?");
        preguntaCantidad.setFont(new Font("Arial", Font.PLAIN, 18));
        preguntaCantidad.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(preguntaCantidad);

        campoCantidad = new JTextField(10);
        campoCantidad.setFont(new Font("Arial", Font.PLAIN, 18));
        campoCantidad.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(campoCantidad);

        preguntaTipoBoleto = new JLabel("¿Qué tipo de boleto prefieres?");
        preguntaTipoBoleto.setFont(new Font("Arial", Font.PLAIN, 18));
        preguntaTipoBoleto.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(preguntaTipoBoleto);

        comboTipoBoleto = new JComboBox<String>();
        comboTipoBoleto.setFont(new Font("Arial", Font.PLAIN, 18));
        comboTipoBoleto.addItem("Menor de Edad (35 pesos)");
        comboTipoBoleto.addItem("Boleto Normal (50 pesos)");
        comboTipoBoleto.addItem("Edad Avanzada (35 pesos)");
        centerPanel.add(comboTipoBoleto);

        confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmar.addActionListener(this);
        add(confirmar, BorderLayout.WEST);

        regresar = new JButton("Regresar");
        regresar.setFont(new Font("Arial", Font.PLAIN, 18));
        regresar.addActionListener(this);
        add(regresar, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmar) {
            try {
                int cantidad = Integer.parseInt(campoCantidad.getText());
                if (cantidad > 0) {
                    String tipoBoleto = (String) comboTipoBoleto.getSelectedItem();
                    // Nueva: Obtener lista de asientos ocupados
                    List<Integer> asientosOcupados = BaseDeDatos.obtenerAsientosOcupados(pelicula, horario);

                    // Nueva: Eliminar asientos ocupados de la lista de libres
                    for (Integer asientoOcupado : asientosOcupados) {
                        String asientoOcupadoStr = String.valueOf(asientoOcupado);
                        asientosLibres.remove(asientoOcupadoStr);
                    }

                    // Crear instancia de Posicion para la selección de asientos
                    Posicion posicion = new Posicion(pelicula, horario, cantidad, tipoBoleto, asientosLibres);
                    dispose();  // Cerrar la ventana actual
                } else {
                    JOptionPane.showMessageDialog(this, "Debes ingresar una cantidad positiva de asientos.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Debes ingresar un número entero de asientos.");
            }
        } else if (e.getSource() == regresar) {
            // Lógica para el botón Regresar si es necesario.
            JOptionPane.showMessageDialog(this, "Regresando a la selección de horarios.");
            dispose();
            Horarios horarios = new Horarios(pelicula);
        }
    }

    // Nueva: Método para obtener la lista de asientos libres
    private List<String> obtenerAsientosLibres() {
        List<String> asientos = obtenerTodosLosAsientos();
        List<Integer> asientosOcupados = BaseDeDatos.obtenerAsientosOcupados(pelicula, horario);

        // Eliminar asientos ocupados de la lista de asientos libres
        asientos.removeAll(asientosOcupados.stream().map(Object::toString).collect(Collectors.toList()));

        return asientos;
    }

    // Nueva: Método ficticio para obtener todos los asientos
    private List<String> obtenerTodosLosAsientos() {
        List<String> asientos = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            asientos.add(String.valueOf(i));
        }
        return asientos;
    }
}
