package org.example.ejercicios;

import java.io.File;
import java.io.IOException;

/**
 * ============================================================
 * EJERCICIO 1: AUDITORÍA DE RED (ping + redirección a fichero)
 * ============================================================
 * Aplica el CONCEPTO 2 (redirección): en vez de mostrar el ping por consola,
 * se guarda en un fichero, y el programa avisa del código de salida.
 */
public class Ejercicio1 {
    public static void main(String[] args) {
        /*
        ENUNCIADO ORIGINAL:
            Crea un programa en Java que ejecute ping a un servidor conocido.
            Redirige la salida directamente a un archivo resultado_ping.txt.
            Avisa por consola al finalizar indicando el código de salida.
        */

        /*  FORMA A - TODO DENTRO DEL MAIN (comentada)
        ProcessBuilder pb = new ProcessBuilder("ping", "8.8.8.8");
        File archivoSalida = new File("resultado_ping.txt");
        pb.redirectOutput(archivoSalida);   // stdout -> fichero (sobrescribe)

        try {
            System.out.println("Iniciando prueba de red...");
            Process process = pb.start();

            int codigoSalida = process.waitFor(); // esperamos y recogemos el código

            if(codigoSalida == 0){
                System.out.println("¡Proceso completado con exito!");
                System.out.println("Revisa el archivo generado en: " + archivoSalida.getAbsolutePath());
            } else {
                System.out.println("El proceso falló con codigo de error: " + codigoSalida);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar ejecutar el comando.");
            e.printStackTrace();
        }
         */

        /* FORMA B - EL CÓDIGO EN UN MÉTODO APARTE (más limpio y reutilizable) */
        int resultado = hacerPingYGuardar("8.8.8.8", "resultado_ping2.txt");
        // El método devuelve un "código propio" que traducimos a mensajes
        switch (resultado) {
            case 0 -> System.out.println("¡Exito!");
            case 1 -> System.out.println("Error: No se puedo contactar con el IP");
            case -1 -> System.out.println("Error del sistema al ejecutar el proceso");
        }
    }

    // Lo mismo que la forma A pero en un método.
    // Devuelve:  0 = ping OK | 1 = ping falló | -1 = no se pudo ni lanzar el proceso
    public static int hacerPingYGuardar(String ip, String nombreArchivo){
        ProcessBuilder pb = new ProcessBuilder("ping", ip);
        pb.redirectOutput(new File(nombreArchivo));
        try {
            Process process = pb.start();
            int codigoSalida = process.waitFor();

            return (codigoSalida == 0) ? 0 : 1;
        } catch (IOException | InterruptedException e) {
            return -1;
        }
    }
}
