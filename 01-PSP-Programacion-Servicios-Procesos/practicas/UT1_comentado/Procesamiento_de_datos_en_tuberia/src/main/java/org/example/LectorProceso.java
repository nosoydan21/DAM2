package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================
 * PROCESO 1 DE LA TUBERÍA: LECTOR
 * ============================================================
 * Lee un fichero y lo escribe línea a línea por su STDOUT.
 * Al estar en una tubería, esa salida llega sola a la ENTRADA del siguiente proceso.
 * (Es el equivalente a "cat alumnos.txt" en consola.)
 */
public class LectorProceso {

    public static void main(String[] args) {
        // args[0] = ruta del fichero a leer (la manda TuberiaTresProcesos)
        if (args.length < 1) {
            System.err.println("Uso: LectorProceso <>");
            System.exit(1); // código 1 = error de uso
        }

        Path fichero = Path.of(args[0]);

        // PrintWriter sobre System.out, forzando UTF-8 para que las tildes (Pérez, Sofía...)
        // no se corrompan al viajar entre procesos. El "true" = autoflush (envía en cada println).
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        try (BufferedReader lector = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                salida.println(linea); // sale por stdout -> entra al filtro
            }
        } catch (IOException e) {
            // Los errores van a stderr (canal aparte) para no mezclarse con los datos
            System.err.println("LectorProceso: no se pudo leer '" + fichero + "': " + e.getMessage());
            System.exit(2); // código 2 = error de lectura
        }
    }
}
