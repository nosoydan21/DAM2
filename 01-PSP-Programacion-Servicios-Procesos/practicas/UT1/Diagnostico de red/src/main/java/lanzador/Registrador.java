package lanzador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Registrador {
    public static void main(String[] args) {
        //validar que estoy recibiendo un parámetro
        if (args.length < 1) {
            System.err.println("[Registrador] Error: Falta el nombre de usuario");
            System.exit(1);
        }
        String user = args[0];
        String nomFichero = "log.txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(nomFichero,true))) {
            String mensaje = user + " accedió el " + LocalDateTime.now();
            out.println(mensaje);

            System.out.println("[Registrador] OK: Registro guardado correctamente en el fichero para -> " + user);
        } catch (IOException e) {
            System.err.println("[Registrador] ERROR: No se ha podido guardar el fichero.");
        }
    }
}
