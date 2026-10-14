package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Proceso 1 de la tubería.
 *
 * Lee el fichero de alumnos recibido como primer argumento de la línea de
 * comandos y vuelca su contenido línea a línea en la salida estándar
 * (System.out). No carga todo el fichero en memoria: usa un BufferedReader.
 */
public class LectorProceso {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: LectorProceso <ruta-del-fichero>");
            System.exit(1);
        }

        Path fichero = Path.of(args[0]);

        // La salida se escribe siempre en UTF-8 para que los siguientes
        // procesos del pipeline reciban bien los acentos.
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
