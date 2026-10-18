package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ============================================================
 * PROGRAMA PRINCIPAL: MONTA LA TUBERÍA DE 3 PROCESOS
 * ============================================================
 * Cadena de montaje:
 *
 *   alumnos.txt -> [LectorProceso] -> [FiltroAsignatura] -> [OrdenadorAlumnos] -> fichero de salida
 *
 * Es lo mismo que "cat alumnos.txt | grep ... | sort > salida.txt" en consola,
 * pero con 3 procesos Java. Java conecta la salida de cada uno con la
 * entrada del siguiente automáticamente gracias a startPipeline().
 */
public class TuberiaTresProcesos {

    public static void main(String[] args) {
        // Parámetros opcionales: fichero (por defecto alumnos.txt) y asignatura (por defecto PSP)
        String rutaFichero = args.length > 0 ? args[0] : "alumnos.txt";
        String asignatura = args.length > 1 ? args[1] : "PSP";

        // Para lanzar procesos Java: ruta del "java" + classpath (ver Lanzador)
        String javaBin = System.getProperty("java.home")
                + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");

        // Preparamos los 3 procesos (todavía NO se ejecutan)
        ProcessBuilder lector = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.LectorProceso", rutaFichero);
        ProcessBuilder filtro = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.FiltroAsignatura", asignatura);
        ProcessBuilder ordenador = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.OrdenadorAlumnos");

        // Los errores (stderr) de cada hijo salen por la consola del padre.
        // Los datos (stdout) NO se tocan: van por la tubería al siguiente proceso.
        lector.redirectError(ProcessBuilder.Redirect.INHERIT);
        filtro.redirectError(ProcessBuilder.Redirect.INHERIT);
        ordenador.redirectError(ProcessBuilder.Redirect.INHERIT);

        // La salida del ÚLTIMO proceso va a un fichero, p. ej. "psp_aprobados_ordenados.txt"
        File ficheroSalida = new File(asignatura.toLowerCase() + "_aprobados_ordenados.txt");
        ordenador.redirectOutput(ficheroSalida);

        try {
            // startPipeline arranca los 3 a la vez y los conecta en cadena
            List<Process> procesos = ProcessBuilder.startPipeline(List.of(lector, filtro, ordenador));
            System.out.println("Tubería iniciada con " + procesos.size() + " procesos");

            // Solo esperamos al ÚLTIMO: cuando él termina, los anteriores ya han terminado
            // (necesita todos sus datos de entrada para acabar).
            int codigoSalida = procesos.get(procesos.size() - 1).waitFor();

            System.out.println("Tubería finalizada, código de salida: " + codigoSalida);
            System.out.println("Resultado en: " + ficheroSalida.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al ejecutar la tubería: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("La tubería fue interrumpida");
        }
    }
}
