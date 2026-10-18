package lanzador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * ============================================================
 * HIJO: recibe un usuario por argumento y lo apunta en log.txt
 * ============================================================
 * Se ejecuta como proceso independiente, lanzado por Lanzador.
 */
public class Registrador {
    public static void main(String[] args) {
        // Validar que recibo el parámetro. args = lo que el padre puso tras el nombre de la clase.
        if (args.length < 1) {
            System.err.println("[Registrador] Error: Falta el nombre de usuario");
            // System.exit(1): termina el proceso con código 1 = ERROR.
            // Así el padre puede saber que algo fue mal mirando waitFor().
            System.exit(1);
        }
        String user = args[0];
        String nomFichero = "log.txt";

        // FileWriter(nombre, true) -> modo AÑADIR: cada ejecución añade una línea sin borrar las anteriores
        try (PrintWriter out = new PrintWriter(new FileWriter(nomFichero, true))) {
            String mensaje = user + " accedió el " + LocalDateTime.now();
            out.println(mensaje);

            System.out.println("[Registrador] OK: Registro guardado correctamente en el fichero para -> " + user);
        } catch (IOException e) {
            System.err.println("[Registrador] ERROR: No se ha podido guardar el fichero.");
        }
        // Si llega al final sin System.exit, el código de salida es 0 (éxito).
    }
}
