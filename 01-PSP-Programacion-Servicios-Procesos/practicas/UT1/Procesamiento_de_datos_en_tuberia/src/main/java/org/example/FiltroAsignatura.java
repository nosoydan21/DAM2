package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
public class FiltroAsignatura {

    private static final double NOTA_MINIMA = 5.0;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: FiltroAsignatura <asignatura>");
            System.exit(1);
        }
        String asignaturaBuscada = args[0].trim();

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        try {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }

                String[] campos = linea.split(",");
                if (campos.length < 3) {
                    continue;
                }

                String asignatura = campos[1].trim();
                if (!asignatura.equalsIgnoreCase(asignaturaBuscada)) {
                    continue;
                }

                double nota;
                try {
                    nota = Double.parseDouble(campos[2].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                if (nota >= NOTA_MINIMA) {
                    salida.println(campos[0].trim() + "," + asignatura + "," + nota);
                }
            }
        } catch (IOException e) {
            System.err.println("FiltroAsignatura: error al leer la entrada: " + e.getMessage());
            System.exit(2);
        }
    }
}
