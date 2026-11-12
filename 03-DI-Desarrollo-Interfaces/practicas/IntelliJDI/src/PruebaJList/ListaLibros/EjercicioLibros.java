package PruebaJList.ListaLibros;

import PruebaJList.ListaAlumnos.Alumno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EjercicioLibros {
    private JList list1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton guardarButton;
    private JButton editarButton;
    private JButton borrarButton;
    private JButton nuevoButton;
    private JButton cancelarButton;
    private JPanel JPanel1;
    private JButton informaciónButton;

    private DefaultListModel<Libro> model = new DefaultListModel<>();

    private Libro libroEnEdicion = null;

    public EjercicioLibros() {
        Libro libro1 = new Libro("Harry Potter: La piedra filosofal", "J.K. Rowling", 2001, "Bloomsbury");
        model.addElement(libro1);
        Libro libro2 = new Libro("El señor de los anillos", "J.R.R. Tolkein", 1954, "George Allen & Unwin");
        model.addElement(libro2);
        Libro libro3 = new Libro("IT", "Stephen King", 1986, "Viking Penguin");
        model.addElement(libro3);
        Libro libro4 = new Libro("Las crónicas de Narnia - El león, la bruja y el armario ", "C. S. Lewis", 1950, "Geoffrey Bles");
        model.addElement(libro4);
        Libro libro5 = new Libro("El Laberinto del Minotauro", "Bernardo Souvirón Guijo", 2016 , "Gredos");
        model.addElement(libro5);
        list1.setModel(model);

        informaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro libro1 = (Libro) list1.getSelectedValue();
                JOptionPane.showMessageDialog(JPanel1, "Titulo: " + libro1.getTitulo() +
                        "\nAutor: " + libro1.getAutor() +
                        "\nAño: " + libro1.getAñoPublicacion() +
                        "\nEditorial: " + libro1.getEditorial());
            }
        });


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro libro = (Libro) list1.getSelectedValue();

                libroEnEdicion = libro;

                textField1.setText(libro.getTitulo());
                textField2.setText(libro.getAutor());
                textField3.setText(String.valueOf(libro.getAñoPublicacion()));
                textField4.setText(libro.getEditorial());

            }
        });


        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(JPanel1, "¿Está seguro de que desea eliminar el libro seleccionado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    model.removeElement(list1.getSelectedValue());
                }
            }
        });


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = textField1.getText();
                    String autor = textField2.getText();
                    int añoPublicacion = Integer.parseInt(textField3.getText());
                    String editorial = textField4.getText();

                    if (libroEnEdicion == null) {
                        // Modo NUEVO: creamos un libro y lo añadimos a la lista
                        model.addElement(new Libro(titulo, autor, añoPublicacion, editorial));
                    } else {
                        // Modo EDITAR: actualizamos el libro existente
                        libroEnEdicion.setTitulo(titulo);
                        libroEnEdicion.setAutor(autor);
                        libroEnEdicion.setAñoPublicacion(añoPublicacion);
                        libroEnEdicion.setEditorial(editorial);
                        list1.repaint();   // refresca el título que se ve en la lista
                    }

                    // Limpiar y volver al estado inicial
                    libroEnEdicion = null;
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPanel1, "El año debe ser un número");
                    }
            }
        });


        nuevoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libroEnEdicion = null;
                list1.clearSelection();

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");

                textField1.requestFocus();
            }
        });


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libroEnEdicion = null;
                list1.clearSelection();

                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }
        });
    }

    static void main() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EjercicioLibros form = new EjercicioLibros();
        frame.setContentPane(form.JPanel1);
        frame.pack();
        frame.setSize(550, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}