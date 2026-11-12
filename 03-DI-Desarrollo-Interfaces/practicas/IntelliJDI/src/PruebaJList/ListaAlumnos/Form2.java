package PruebaJList.ListaAlumnos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form2 {
    private JList list1;
    public JPanel JPanel1;
    private JButton mostrarAlumnoCompletoButton;
    DefaultListModel <Alumno> model = new DefaultListModel<>();

    public Form2() {
        Alumno alumno1 = new Alumno("Jander", "Riera Cruz", 20, 2889477);
        model.addElement(alumno1);
        list1.setModel(model);


        mostrarAlumnoCompletoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alumno alumno1 = (Alumno) list1.getSelectedValue();
                JOptionPane.showMessageDialog(JPanel1, "Nombre: " + alumno1.getNombre() +
                        "\nApellidos: " + alumno1.getApellidos() +
                        "\nEdad: " + alumno1.getEdad() +
                        "\nNRE: " + alumno1.getNre());
            }
        });
    }


    static void main() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Form2 form = new Form2();
        frame.setContentPane(form.JPanel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
