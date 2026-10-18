package org.example;

import java.io.IOException;

/**
 * ============================================================
 * CONCEPTO 2: REDIRECCIÓN DE ENTRADA/SALIDA
 * ============================================================
 * ¿Para qué sirve?
 *   Decidir A DÓNDE va lo que escribe el hijo, SIN leerlo tú línea a línea.
 *
 * Opciones principales (se configuran ANTES de start()):
 *   pb.inheritIO()                  -> el hijo usa TU consola (sale por pantalla)
 *   pb.redirectOutput(File)         -> stdout a un fichero (BORRA lo anterior)
 *   pb.redirectOutput(Redirect.appendTo(File)) -> stdout a un fichero (AÑADE al final)
 *   pb.redirectError(File)          -> stderr a un fichero
 *   pb.redirectErrorStream(true)    -> mezcla stderr dentro de stdout
 */
public class RedireccionProceso {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("ping", "127.0.0.1");

        // Opción A: heredar la consola del padre (lo que escriba ping sale directo en pantalla)
        pb.inheritIO();

        // Opción B (comentada): mandar la salida a un fichero en disco
        // File log = new File("salida_ping.txt");
        // pb.redirectOutput(log);

        Process p = pb.start();
        p.waitFor(); // esperamos a que termine para no cerrar el programa antes
    }
}
