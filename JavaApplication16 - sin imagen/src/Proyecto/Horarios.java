package Proyecto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Horarios extends JFrame implements ActionListener {
    private JLabel titulo;
    private JPanel panelHorarios;
    private JButton[] botones;
    private String[] horarios;
    private JButton regresar;
    private String pelicula;

    public Horarios(String pelicula) {
        super("Reservaciones de cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        this.pelicula = pelicula;

        titulo = new JLabel("Has seleccionado la película " + pelicula + ". Estos son los horarios disponibles:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.add(titulo, BorderLayout.CENTER);
        add(panelTitulo, BorderLayout.NORTH);

        panelHorarios = new JPanel(new GridLayout(2, 3));
        add(panelHorarios, BorderLayout.CENTER);

        horarios = new String[]{"10:00 am", "12:00 pm", "2:00 pm", "4:00 pm", "6:00 pm", "8:00 pm"};
        botones = new JButton[horarios.length];

        for (int i = 0; i < horarios.length; i++) {
            botones[i] = new JButton(horarios[i]);
            botones[i].setFont(new Font("Arial", Font.PLAIN, 18));
            botones[i].addActionListener(this);
            panelHorarios.add(botones[i]);
        }

        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        regresar = new JButton("Regresar");
        regresar.setFont(new Font("Arial", Font.PLAIN, 18));
        regresar.addActionListener(this);
        panelBotones.add(regresar);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

 
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < botones.length; i++) {
            if (e.getSource() == botones[i]) {
                dispose();
                new Asientos(pelicula, horarios[i]);
                break;
            }
        }

        if (e.getSource() == regresar) {
            dispose();
            new Cine();
        }
    }
}
