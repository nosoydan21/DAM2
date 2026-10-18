package lanzador;

import java.io.File;
import java.io.IOException;

/**
 * ============================================================
 * PADRE QUE LANZA UN PROCESO JAVA HIJO CON ARGUMENTOS
 * ============================================================
 * ¿Para qué sirve? Para dividir el trabajo en programas separados:
 *   Lanzador (padre) --usuario--> Registrador (hijo, escribe el log)
 *
 * Para lanzar OTRO programa Java hay que ejecutar el comando "java" con:
 *   - java.home       : carpeta del Java que estás usando ahora
 *   - java.class.path : dónde están tus clases compiladas
 *                       (SIN esto el hijo no encuentra su clase -> ClassNotFoundException)
 */
public class Lanzador {
    public static void main(String[] args) {
        String usuarioAEnviar = "Jander";

        System.out.println("[Lanzador] Iniciando llamada al proceso Registrador...");
        try {
            // Ruta al ejecutable java:  <java.home>/bin/java
            String javaHome = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            String classPath = System.getProperty("java.class.path");

            // Equivale a ejecutar en consola:  java -cp <classPath> lanzador.Registrador Jander
            // El último elemento llegará al hijo como args[0].
            ProcessBuilder pb = new ProcessBuilder(javaHome, "-cp", classPath, "lanzador.Registrador", usuarioAEnviar);

            // inheritIO: lo que escriba el hijo sale en la consola del padre
            pb.inheritIO();
            Process process = pb.start();

            // Código de salida del hijo: 0 = OK, 1 = faltó el argumento (ver Registrador)
            int outCode = process.waitFor();
            System.out.println("[Lanzador] El proceso hijo finalizó con código: " + outCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("[Lanzador] Error al ejecutar el proceso hijo " + e.getMessage());
        }
    }
}
