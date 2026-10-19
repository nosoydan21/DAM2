package org.example;

import java.io.File;
import java.io.IOException;

public class LanzadorContable {
    public static void main(String[] args) {
        String[] archivos = {"empresaA.dat","empresaB_CORRUPTO.dat","empresaC.dat"};

        System.out.println("[Lanzador] Iniciando llamada al procesador...");
        try {
            String javaHome = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            String classPath = System.getProperty("java.class.path");

            ProcessBuilder pb1 = new ProcessBuilder(javaHome, "-cp", classPath, "org.example.ProcesadorContable", archivos[0]);
            ProcessBuilder pb2 = new ProcessBuilder(javaHome, "-cp", classPath, "org.example.ProcesadorContable", archivos[1]);
            ProcessBuilder pb3 = new ProcessBuilder(javaHome, "-cp", classPath, "org.example.ProcesadorContable", archivos[2]);
            pb.inheritIO();
            Process process = pb.start();
            int outCode = process.waitFor();
            System.out.println("[Lanzador] El proceso hijo finalizó con código: " + outCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("[Lanzador] Error al ejecutar el proceso hijo " + e.getMessage());
        }

        //un elefante se balanceaba sobre la tela de una araña y como veía que no se caía fueron a llamar a otro elefante

    }
}
