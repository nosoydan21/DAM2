package lanzador;

import java.io.File;
import java.io.IOException;

public class Lanzador {
    public static void main(String[] args) {
        String usuarioAEnviar = "Jander";

        System.out.println("[Lanzador] Iniciando llamada al proceso Registrador...");
        try {
            String javaHome = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            String classPath = System.getProperty("java.class.path");

            ProcessBuilder pb = new ProcessBuilder(javaHome, "-cp", classPath, "lanzador.Registrador", usuarioAEnviar);
            pb.inheritIO();
            Process process = pb.start();
            int outCode = process.waitFor();
            System.out.println("[Lanzador] El proceso hijo finalizó con código: " + outCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("[Lanzador] Error al ejecutar el proceso hijo " + e.getMessage());
        }
    }
}
