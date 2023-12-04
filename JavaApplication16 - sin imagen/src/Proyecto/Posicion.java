package Proyecto;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class Posicion extends JFrame implements ActionListener {
    private List<String> asientosLibres; // Se agregó la inicialización
    private JLabel titulo;
    private JPanel panelAsientos;
    private JButton[][] botones;
    private int filas;
    private int columnas;
    private JButton confirmar;
    private JButton regresar;
    private String pelicula;
    private String horario;
    private int seleccionados;
    private String posiciones;
    private String tipoBoleto;
    private int limiteAsientos;
    private List<String> asientosSeleccionados;


    public Posicion(String pelicula, String horario, int cantidad, String tipoBoleto, List<String> asientosLibres) {
        super("Reservaciones de cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        this.pelicula = pelicula;
        this.horario = horario;
        this.seleccionados = 0;
        this.posiciones = "";
        this.tipoBoleto = tipoBoleto;
        this.limiteAsientos = cantidad;
        this.asientosSeleccionados = new ArrayList<>();


        this.asientosLibres = asientosLibres;

        titulo = new JLabel("Has reservado " + cantidad + " asientos para la película " + pelicula + " en el horario " + horario + ". Elige la posición de los asientos:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        add(titulo, BorderLayout.NORTH);

        panelAsientos = new JPanel();
        filas = 5;
        columnas = 10;
        panelAsientos.setLayout(new GridLayout(filas, columnas));
        add(panelAsientos, BorderLayout.CENTER);

        botones = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton(i + "," + j);
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 18));
                botones[i][j].setBackground(Color.WHITE);
                botones[i][j].addActionListener(this);
                panelAsientos.add(botones[i][j]);
            }
        }

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



    private void mostrarAsientosOcupados(List<String> asientosOcupados) {
    // Reiniciar todos los asientos a libres
    reiniciarAsientos();

    for (String posicion : asientosOcupados) {
        String[] coordenadas = posicion.split(",");
        int fila = Integer.parseInt(coordenadas[0]);
        int columna = Integer.parseInt(coordenadas[1]);

        // Marcar el asiento como ocupado con color rojo
        botones[fila][columna].setBackground(Color.RED);
        botones[fila][columna].setEnabled(false);
    }

    // Marcar los asientos seleccionados con otro color (por ejemplo, verde)
    for (String seleccionado : asientosSeleccionados) {
        String[] coordenadas = seleccionado.split(",");
        int fila = Integer.parseInt(coordenadas[0]);
        int columna = Integer.parseInt(coordenadas[1]);

        // Marcar el asiento seleccionado con otro color (por ejemplo, verde)
        botones[fila][columna].setBackground(Color.GREEN);
    }
}

private void reiniciarAsientos() {
    // Reiniciar todos los asientos a libres con color blanco
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            botones[i][j].setBackground(Color.WHITE);
            botones[i][j].setEnabled(true);
        }
    }
}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmar) {
            if (seleccionados == 0) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un asiento.");
            } else {
                // Crear instancia de Pago para la confirmación del pago
                Pago pago = new Pago(pelicula, horario, seleccionados, posiciones, tipoBoleto);
                dispose();  // Cerrar la ventana actual
            }
        } else if (e.getSource() instanceof JButton) {
        JButton boton = (JButton) e.getSource();

        // Obtener las coordenadas del asiento
        String coordenadas = boton.getText();

        // Alternar el estado del asiento entre seleccionado y no seleccionado
        if (boton.getBackground() == Color.GREEN) {
            boton.setBackground(Color.WHITE);
            seleccionados--;
            posiciones = posiciones.replace(coordenadas + ",", "");  // Eliminar la posición del asiento

            // Quitar la posición de los asientos seleccionados
            asientosSeleccionados.remove(coordenadas);
        } else {
            if (seleccionados < limiteAsientos) { // Verificar límite de asientos
                boton.setBackground(Color.GREEN);
                seleccionados++;
                posiciones += coordenadas + ",";  // Agregar la posición del asiento

                // Agregar la posición de los asientos seleccionados
                asientosSeleccionados.add(coordenadas);
            } else {
                JOptionPane.showMessageDialog(this, "Solo puedes seleccionar hasta " + limiteAsientos + " asientos.");
            }
        }
    }
}
}