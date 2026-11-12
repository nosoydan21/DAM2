package PruebaSwing1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Form1 {
    private JPanel jPanel1;
    private JLabel jLabel1;
    private JButton button1;
    private JTextField textField1;
    private JButton button2;
    private JCheckBox checkBox1;
    private JButton button3;
    private JCheckBox alPulsarCheckBox;
    private JCheckBox alSoltarCheckBox;
    private JTextField textField2;
    private JButton lanzarUnTemporizadorButton;
    private JButton arrancarTemporizadorIncrementoSpinnerButton;
    private JSpinner spinner1;
    private JButton pararButton;
    private JProgressBar progressBar1;
    private JButton arrancarPararButton;

    private Timer timerSpinner;
    private Timer timerProgressBar;

    public Form1() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Hola Jander");

            }
        });

        /*button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/

        button2.addActionListener( e -> {
            if (checkBox1.isSelected()){
                checkBox1.setSelected(false);
            } else {
                checkBox1.setSelected(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Hola Eydan");
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                alPulsarCheckBox.setSelected(true);
                textField2.setText("Pulsando...");


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                alSoltarCheckBox.setSelected(true);
                textField2.setText("Soltado");

            }
        });
        lanzarUnTemporizadorButton.setMnemonic(KeyEvent.VK_T);
        lanzarUnTemporizadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = new Timer (2000, t ->{
                        JOptionPane.showMessageDialog(jPanel1,"Porque te amo con locura");
                        JOptionPane.showMessageDialog(jPanel1,"No hay dia que no piense en ti");
                    JOptionPane.showMessageDialog(jPanel1,"Cada que consigo hacer algo nuevo");
                    JOptionPane.showMessageDialog(jPanel1,"Eres la primera que quiero que lo sepa");
                });
                timer.setRepeats(false);
                timer.start();

            }
        });
        arrancarTemporizadorIncrementoSpinnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerSpinner = new Timer(1000, t -> {
                    int v = (Integer) spinner1.getValue();
                    spinner1.setValue(v + 1);
                });
                timerSpinner.setRepeats(true);
                timerSpinner.start();
            }
        });
        pararButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerSpinner.stop();
            }
        });

        // Creacion de temporizador para la incrementar el progressBar
        timerProgressBar = new Timer (1000, t ->{
            int valor = progressBar1.getValue();
            progressBar1.setValue(valor + 1);
        });

        arrancarPararButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerProgressBar.isRunning()) {
                    timerProgressBar.stop();
                } else {
                    timerProgressBar.start();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Titulo de mi ventana");

        Form1 form1 = new Form1();
        frame.setContentPane(form1.jPanel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);


    }

}
