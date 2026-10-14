package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LectorProceso {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: LectorProceso <>");
            System.exit(1);
        }

        Path fichero = Path.of(args[0]);

        PrintWriter salida = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        try (BufferedReader lector = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                salida.println(linea);
            }
        } catch (IOException e) {
            System.err.println("LectorProceso: no se pudo leer '" + fichero + "': " + e.getMessage());
            System.exit(2);
        }
    }
}
