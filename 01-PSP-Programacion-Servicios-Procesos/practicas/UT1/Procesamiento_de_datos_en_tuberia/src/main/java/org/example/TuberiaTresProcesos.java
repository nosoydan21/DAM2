package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Proceso orquestador de la tubería.
 *
 * Configura los tres ProcessBuilder (LectorProceso -> FiltroAsignatura ->
 * OrdenadorAlumnos), los interconecta con la API moderna
 * ProcessBuilder.startPipeline(...), redirige la salida final al fichero
 * "<asignatura>_aprobados_ordenados.txt" (asignatura en minúsculas) y
 * espera la finalización de toda la tubería informando del código de salida.
 *
 * Uso: TuberiaTresProcesos [ruta-alumnos.txt] [asignatura]
 *   - ruta-alumnos.txt : por defecto "alumnos.txt"
 *   - asignatura       : por defecto "PSP" (admite "AD", "SGE", ...)
 */
public class TuberiaTresProcesos {

    public static void main(String[] args) {
        String rutaFichero = args.length > 0 ? args[0] : "alumnos.txt";
        String asignatura = args.length > 1 ? args[1] : "PSP";

        // Ejecutable java que lanzará cada proceso hijo y classpath actual.
        String javaBin = System.getProperty("java.home")
                + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");

        // 1) Cada proceso se lanza como una JVM independiente.
        ProcessBuilder lector = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.LectorProceso", rutaFichero);
        ProcessBuilder filtro = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.FiltroAsignatura", asignatura);
        ProcessBuilder ordenador = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.OrdenadorAlumnos");

        // Los errores de los procesos hijos se muestran en esta consola.
        lector.redirectError(ProcessBuilder.Redirect.INHERIT);
        filtro.redirectError(ProcessBuilder.Redirect.INHERIT);
        ordenador.redirectError(ProcessBuilder.Redirect.INHERIT);

        // 2) La salida final se redirige a "<asignatura>_aprobados_ordenados.txt".
        File ficheroSalida = new File(asignatura.toLowerCase(Locale.ROOT) + "_aprobados_ordenados.txt");
        ordenador.redirectOutput(ficheroSalida);

        try {
            // 3) startPipeline conecta automáticamente stdout de cada proceso
            //    con stdin del siguiente.
            List<Process> procesos = ProcessBuilder.startPipeline(List.of(lector, filtro, ordenador));

            System.out.println("Tubería iniciada (" + procesos.size() + " procesos). Esperando...");

            // 4) Se espera al último proceso (el resto termina en cadena).
            Process ultimoProceso = procesos.get(procesos.size() - 1);
            int codigoSalida = ultimoProceso.waitFor();

            System.out.println("Tubería finalizada.");
            System.out.println("Código de salida devuelto: " + codigoSalida);
            System.out.println("Resultado guardado en: " + ficheroSalida.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error de E/S al ejecutar la tubería: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La ejecución de la tubería fue interrumpida.");
        }
    }
}
