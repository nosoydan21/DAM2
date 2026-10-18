package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ============================================================
 * DIAGNÓSTICO DE RED: ping + log con cabecera
 * ============================================================
 * Combina varios conceptos:
 *   - Detectar el SO para elegir el comando (ping -n en Windows, -c en Linux)
 *   - Redirección en modo AÑADIR (appendTo) para no perder ejecuciones anteriores
 *   - Metadatos del proceso (pid, isAlive) y código de salida
 */
public class Main {
    public static void main(String[] args) {
        String logFileName = "registro_ping.log";
        File logFile = new File(logFileName);

        try {
            // Cabecera con fecha/hora para separar visualmente cada ejecución en el log
            escribirCabeceraLog(logFile);

            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            // La opción para limitar nº de paquetes cambia según SO:
            //   Windows: -n 4   |   Linux: -c 4
            if (os.contains("win")) {
                pb = new ProcessBuilder("ping", "-n", "4", "127.0.0.1");
            } else {
                pb = new ProcessBuilder("ping", "-c", "4", "127.0.0.1");
            }

            // Mandamos stdout Y stderr al mismo fichero, en modo AÑADIR.
            // (Sin appendTo, borraría la cabecera que acabamos de escribir.)
            pb.redirectOutput(ProcessBuilder.Redirect.appendTo(logFile));
            pb.redirectError(ProcessBuilder.Redirect.appendTo(logFile));

            Process proceso = pb.start();

            System.out.println("=== METADATOS DEL PROCESO HIJO ===");
            System.out.println("PID del proceso: " + proceso.pid());
            System.out.println("Actualmente activo: " + proceso.isAlive()); // normalmente true

            // Esperamos a que acabe el ping
            int exitCode = proceso.waitFor();

            System.out.println("\n=== RESULTADO DE LA EJECUCIÓN ===");
            System.out.println("¿Proceso activo tras el waitFor()? " + proceso.isAlive()); // false
            System.out.println("Codigo de finalización (Exit Code): " + (exitCode == 0 ? "OK" : "ERROR"));

            System.out.println("Confirmacion: La salida se ha registrado correctamente en '" + logFile.getAbsolutePath() + "'.");
        } catch (IOException e) {
            System.out.println("Error de E/S al ejecutar el proceso o escribir el log: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El proceso ha sido interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt(); // restablecemos el estado de interrupción
        }
    }

    // Escribe una cabecera con la fecha/hora al final del fichero
    private static void escribirCabeceraLog(File file) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String timeStamp = LocalDateTime.now().format(formatter); // fecha actual con ese formato

        // FileWriter(file, true): el "true" activa el modo AÑADIR (append)
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println("\n==========================================");
            writer.println("EJECCIÓN DIAGNOSTICO DE RED: " + timeStamp);
            writer.println("=============================================");
        }
    }
}
