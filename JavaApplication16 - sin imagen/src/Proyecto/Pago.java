package Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pago extends JFrame implements ActionListener {
    private JLabel titulo;
    private JButton confirmar;
    private JButton regresar;
    private String pelicula;
    private String horario;
    private int cantidad;
    private String posiciones;
    private String tipoBoleto;
    private double totalPagar;
    private JComboBox<String> formasPago;
    private java.util.List<String> asientosLibres;

    public Pago(String pelicula, String horario, int cantidad, String posiciones, String tipoBoleto) {
        
        
        super("Reservaciones de cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        this.pelicula = pelicula;
        this.horario = horario;
        this.cantidad = cantidad;
        this.posiciones = posiciones;
        this.tipoBoleto = tipoBoleto;
        this.totalPagar = calcularTotalPagar(cantidad, tipoBoleto);  // Calcular el total a pagar

        titulo = new JLabel("Resumen de la reserva:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel centroPanel = new JPanel();
        centroPanel.setLayout(new GridLayout(2, 1));

        // Mostrar opciones de forma de pago
        formasPago = new JComboBox<>();
        formasPago.addItem("Tarjeta de Crédito");
        formasPago.addItem("Efectivo");
        formasPago.addItem("Otro");
        centroPanel.add(formasPago);

        // Mostrar total a pagar
        JLabel totalLabel = new JLabel("Total a Pagar: $" + totalPagar);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        centroPanel.add(totalLabel);

        add(centroPanel, BorderLayout.CENTER);

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

    private double calcularTotalPagar(int cantidad, String tipoBoleto) {
    double precioBoleto;

    switch (tipoBoleto) {
        case "Menor de Edad (35 pesos)":
        case "Edad Avanzada (35 pesos)":
            precioBoleto = 35.0;
            break;
        case "Boleto Normal (50 pesos)":
        default:
            precioBoleto = 50.0;
            break;
    }

    return precioBoleto * cantidad;
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmar) {
            // Obtener la forma de pago seleccionada
            String formaPagoSeleccionada = (String) formasPago.getSelectedItem();
            // Lógica para confirmar la reserva con la forma de pago seleccionada

            // Crear instancia de MostrarReserva para mostrar la confirmación
            MostrarReserva mostrarReserva = new MostrarReserva(pelicula, horario, cantidad, tipoBoleto, formaPagoSeleccionada, totalPagar);
            dispose();  // Cerrar la ventana actual
        } else if (e.getSource() == regresar) {
            // Lógica para el botón Regresar si es necesario.
            JOptionPane.showMessageDialog(this, "Regresando a la selección de asientos.");
            dispose();  // Cerrar la ventana actual
            Posicion posicion = new Posicion(pelicula, horario, cantidad, tipoBoleto, asientosLibres);
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        SwingUtilities.invokeLater(() -> new Pago("Titanic", "18:00", 2, "0,0,0,1,1,1,", "Boleto Normal"));
    }
}
