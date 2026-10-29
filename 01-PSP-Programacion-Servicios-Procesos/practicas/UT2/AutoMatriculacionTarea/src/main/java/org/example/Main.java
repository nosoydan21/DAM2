package org.example;

import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        // TODO: Desarrollar metodo :)
        String[] dnis = {
                "12345678Z",
                "87654321X",
                "45678901G",
                "23456789D",
                "34567890V",
                "56789012B",
                "67890123N",
                "78901234H"
        };

        System.out.println("INICIO DEL PROCESO DE MATRÍCULA");
        for (int i = 0; i < dnis.length; i++) {
            Thread hilo = new Thread(new TareaAlumno(dnis[i]), "Alumno" + i + "-" + dnis[i]);
            hilo.start();

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100,300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
