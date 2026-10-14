package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TuberiaTresProcesos {

    public static void main(String[] args) {
        String rutaFichero = args.length > 0 ? args[0] : "alumnos.txt";
        String asignatura = args.length > 1 ? args[1] : "PSP";

        String javaBin = System.getProperty("java.home")
                + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder lector = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.LectorProceso", rutaFichero);
        ProcessBuilder filtro = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.FiltroAsignatura", asignatura);
        ProcessBuilder ordenador = new ProcessBuilder(javaBin, "-cp", classpath, "org.example.OrdenadorAlumnos");

        lector.redirectError(ProcessBuilder.Redirect.INHERIT);
        filtro.redirectError(ProcessBuilder.Redirect.INHERIT);
        ordenador.redirectError(ProcessBuilder.Redirect.INHERIT);


        File ficheroSalida = new File(asignatura.toLowerCase() + "_aprobados_ordenados.txt");
        ordenador.redirectOutput(ficheroSalida);

        try {
            List<Process> procesos = ProcessBuilder.startPipeline(List.of(lector, filtro, ordenador));
            System.out.println("Tubería iniciada con " + procesos.size() + " procesos");

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
