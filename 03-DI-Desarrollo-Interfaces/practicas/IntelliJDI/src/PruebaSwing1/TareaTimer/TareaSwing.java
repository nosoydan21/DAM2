package PruebaSwing1.TareaTimer;

import javax.swing.*;
import java.awt.event.*;

public class TareaSwing {
    private JButton reiniciarButton;
    private JSpinner spinner1; // Aqui añadimos el tiempo que queremos que tarde en llenarse la barra
    private JProgressBar progressBar1;
    private JButton pulsarButton;
    private JPanel jPanel2;

    private static final int INTERVALO_MS = 50;

    private Timer timerProgressBar1;
    private boolean pulsar = false;
    private double progreso = 0;

    public TareaSwing() {
        pulsarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        timerProgressBar1 = new Timer (INTERVALO_MS, t ->{

            // Segun el valor que pongamos en el spinner sera la velocidad que tenga en llenar la ProgressBar
            int segundos = (int) spinner1.getValue();
            double paso = 100.0 / (segundos * 1000.0 / INTERVALO_MS);

            if (pulsar){
                // Con el boton pulsado la barra sube sin pasar del 100
                progreso = Math.min(100, progreso + paso);
            } else {
                // Al soltar el boton la barra baja sin pasar del 0
                progreso = Math.max(0, progreso - paso);
                if (progreso == 0) {
                    // La barra está vacia no hay que hacer nada
                    timerProgressBar1.stop();
                }
            }
            progressBar1.setValue((int) Math.round(progreso));

            // -----------BARRA COMPLETA---------

            // Una vez la barra este completa
            if (progreso >= 100){
                // Ya no se considerara que se este pulsando
                pulsar = false;
                // El timer se detendrá
                timerProgressBar1.stop();
                // Por lo que desactivara el boton "pulsar"
                pulsarButton.setEnabled(false);

                // Una vez completa la barra se mostrara una nueva ventana mostrando este mensaje
                JOptionPane.showMessageDialog(jPanel2, "Objetivo Completado!!!");
            }
        });

        // El MouseListener se usa para distinguir cuando se PRESIONA un boton a cuando se SUELTA
        pulsarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Marcamos que el boton se está pulsando
                pulsar= true;
                // Arranca el timer
                timerProgressBar1.start();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // El timer empezara a vaciar la varra
                pulsar = false;

            }
        });





//            @Override
//            public void mousePressed(MouseEvent e) {
//                super.mousePressed(e);
//                int valor = progressBar1.getValue();
//                progressBar1.setValue(valor + 1);
//
//            }

        //-----REINICIAR-------
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerProgressBar1.stop();
                pulsar = false;
                // Reseteamos la variable
                progreso = 0;
                // Vaciamos la barra completa
                progressBar1.setValue(0);
                // Volvemos a hacer que funcione el boton "PULSAR"
                pulsarButton.setEnabled(true);
                spinner1.setEnabled(true);
            }
        });

        spinner1.addComponentListener(new ComponentAdapter() {
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Titulo de mi ventana");

        TareaSwing form1 = new TareaSwing();
        frame.setContentPane(form1.jPanel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


    }
}
