package org.example;

import javax.swing.*;

public class form {
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JCheckBox checkBox1;

    public form() {

        button1.addActionListener(e -> {

        });

//        EXPRESION LAMBDA ANÁLOGA A LA ANTERIOR PARA CLASES MANEJADORAS DE UN SOLO METODO
        button2.addActionListener(e -> {
            if (checkBox1.isSelected()) {
                checkBox1.setSelected(false);
            } else {
                checkBox1.setSelected(true);
            }
        });
    }
}
