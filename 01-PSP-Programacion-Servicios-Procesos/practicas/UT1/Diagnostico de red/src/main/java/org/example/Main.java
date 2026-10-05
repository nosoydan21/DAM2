package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String logFileName = "registro_ping.log";
        File logFile = new File(logFileName);

        try {
            //Escribimos una cabecera tal y como pide la parte de ampliación del ejercicio
            escribirCabeceraLog(logFile);

            String os =  System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (os.contains("win")) {
                //Windows
                pb = new ProcessBuilder("ping", "-n", "4", "127.0.0.1");
            } else {
                //Linux
                pb = new ProcessBuilder("ping", "-c", "4", "127.0.0.1");
            }

            //Redirigimos la salida estándar y la entrada de error al fichero que hemos declarado arriba
            pb.redirectOutput(ProcessBuilder.Redirect.appendTo(logFile));
            pb.redirectError(ProcessBuilder.Redirect.appendTo(logFile));

            Process proceso = pb.start();

            System.out.println("=== METADATOS DEL PROCESO HIJO ===");
            System.out.println("PID del proceso: " + proceso.pid());
            System.out.println("Actualmente activo: " + proceso.isAlive());

            //Esperar a que el proceso finalice su tarea
            int exitCode = proceso.waitFor();

            System.out.println("\n=== RESULTADO DE LA EJECUCIÓN ===");
            System.out.println("¿Proceso activo tras el waitFor()? " + proceso.isAlive());
            System.out.println("Codigo de finalización (Exit Code): " + (exitCode == 0 ? "OK" : "ERROR"));

            System.out.println("Confirmacion: La salida se ha registrado correctamente en '"+logFile.getAbsolutePath()+"'.");
        } catch (IOException e) {
            System.out.println("Error de E/S al ejecutar el proceso o escribir el log: "+e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El proceso ha sido interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt(); //Restrablecemos el estado de interrupción
        }
    }

    private static void escribirCabeceraLog(File file) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String timeStamp = LocalDateTime.now().format(formatter); //formateamos la fecha actual con el formateador que hemos hecho una línea arriba

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            // el true es para un modo append, se va a añadir no a borrar y escribir
            writer.println("\n==========================================");
            writer.println("EJECCIÓN DIAGNOSTICO DE RED: " + timeStamp);
            writer.println("=============================================");
        }
    }
}