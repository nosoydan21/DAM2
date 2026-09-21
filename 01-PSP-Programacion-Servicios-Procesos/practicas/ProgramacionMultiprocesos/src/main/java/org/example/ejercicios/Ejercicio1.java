package org.example.ejercicios;

import java.io.File;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        /*
        EJERCICIO 1
            Auditoría de red
                ● Objetivo: Practicar la creación de un proceso y la redirección directa de salida a un
                archivo usando redirectOutput.
                ● Enunciado: Crea un programa en Java que ejecute el comando ping a un servidor
                conocido (por ejemplo, 8.8.8.8 o google.com). En lugar de mostrar el resultado
                por la consola de NetBeans, redirige la salida directamente a un archivo llamado
                resultado_ping.txt en la raíz de tu proyecto. El programa debe avisar por
                consola cuando la tarea haya finalizado indicando el código de salida del proceso.
        */
        /*  FORMA A - PRIMERA FORMA, CÓDIGO DENTRO DEL MAIN
        ProcessBuilder pb = new ProcessBuilder("ping", "8.8.8.8");
        File archivoSalida = new File("resultado_ping.txt");
        pb.redirectOutput(archivoSalida);

        try {
            System.out.println("Iniciando prueba de red...");
            Process process = pb.start();

            int codigoSalida = process.waitFor();

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

        /* FORMA B - CÓDIGO EN UN METODO APARTE*/
        int resultado = hacerPingYGuardar("8.8.8.8", "resultado_ping2.txt");
        switch (resultado) {
            case 0 -> System.out.println("¡Exito!");
            case 1 -> System.out.println("Error: No se puedo contactar con el IP");
            case -1 -> System.out.println("Error del sistema al ejecutar el proceso");
        }
    }

//    ESTO ES LO MISMO PERO CON UN METODO APARTA
    public static int hacerPingYGuardar(String ip, String nombreArchivo){
        ProcessBuilder pb = new ProcessBuilder("ping", ip);
        pb.redirectOutput(new File(nombreArchivo));
        try {
            Process process = pb.start();
            int codigoSalida = process.waitFor();

            return (codigoSalida == 0) ? 0:1;
        } catch (IOException | InterruptedException e) {
            return -1;
        }
    }
}
