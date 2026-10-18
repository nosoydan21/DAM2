package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * ============================================================
 * PROCESO 2 DE LA TUBERÍA: FILTRO
 * ============================================================
 * Lee por STDIN (lo que escribe el Lector), se queda solo con los alumnos
 * de UNA asignatura que estén APROBADOS (nota >= 5) y lo escribe por STDOUT.
 * (Equivale a un "grep" con condición.)
 */
public class FiltroAsignatura {

    private static final double NOTA_MINIMA = 5.0;

    public static void main(String[] args) {
        // args[0] = asignatura a buscar (PSP, AD, SGE...)
        if (args.length < 1) {
            System.err.println("Uso: FiltroAsignatura <asignatura>");
            System.exit(1);
        }
        String asignaturaBuscada = args[0].trim();

        // Entrada = stdin (la salida del proceso anterior); salida = stdout (entrada del siguiente)
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        try {
            String linea;
            // IMPORTANTE: hay que leer TODA la entrada hasta null; si no, el proceso anterior
            // se bloquearía al intentar escribir en una tubería llena.
            while ((linea = entrada.readLine()) != null) {
                if (linea.isBlank()) {
                    continue; // saltamos líneas vacías
                }

                // Formato de cada línea: "Nombre,ASIGNATURA,nota"
                String[] campos = linea.split(",");
                if (campos.length < 3) {
                    continue; // línea mal formada -> la ignoramos
                }

                // ¿Es la asignatura que buscamos? (ignorando mayúsculas/minúsculas)
                String asignatura = campos[1].trim();
                if (!asignatura.equalsIgnoreCase(asignaturaBuscada)) {
                    continue;
                }

                // Convertimos la nota a número; si no es un número válido, ignoramos la línea
                double nota;
                try {
                    nota = Double.parseDouble(campos[2].trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                // Solo pasan los aprobados
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
