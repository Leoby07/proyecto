package Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarReserva extends JFrame implements ActionListener {
    private JLabel titulo;
    private JButton confirmar;
    private String pelicula;
    private String horario;
    private int cantidad;
    private String tipoBoleto;
    private String formaPago;
    private double totalPagar;

    public MostrarReserva(String pelicula, String horario, int cantidad, String tipoBoleto, String formaPago, double totalPagar) {
        super("Reservaciones de cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        this.pelicula = pelicula;
        this.horario = horario;
        this.cantidad = cantidad;
        this.tipoBoleto = tipoBoleto;
        this.formaPago = formaPago;
        this.totalPagar = totalPagar;

        titulo = new JLabel("Resumen de la reserva:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        JTextArea detalleReserva = new JTextArea();
        detalleReserva.setFont(new Font("Arial", Font.PLAIN, 18));
        detalleReserva.setEditable(false);

        // Construir el mensaje de la reserva
        String mensajeReserva = "Pelicula: " + pelicula + "\n"
                + "Horario: " + horario + "\n"
                + "Cantidad de asientos: " + cantidad + "\n"
                + "Tipo de boleto: " + tipoBoleto + "\n"
                + "Forma de pago: " + formaPago + "\n"
                + "Total a pagar: $" + totalPagar;

        detalleReserva.setText(mensajeReserva);
        add(detalleReserva, BorderLayout.CENTER);

        confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmar.addActionListener(this);
        add(confirmar, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == confirmar) {
        // Lógica para confirmar la reserva y redirigir a la pantalla de carga
        PantallaCarga pantallaCarga = new PantallaCarga();
        pantallaCarga.setVisible(true);
        dispose();  // Cerrar la ventana actual
    }
}

    public static void main(String[] args) {
        // Ejemplo de uso
        SwingUtilities.invokeLater(() -> new MostrarReserva("Titanic", "18:00", 2, "Boleto Normal", "Tarjeta de Crédito", 20.0));
    }
}