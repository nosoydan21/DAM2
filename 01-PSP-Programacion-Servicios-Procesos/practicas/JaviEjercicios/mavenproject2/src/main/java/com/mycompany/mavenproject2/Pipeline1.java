/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author jtole
 */
public class Pipeline1 {

        public static void main(String[] args) {
        try {
            // 1. Definir los procesos de la tubería adaptados a Windows
            // 'type' lee el fichero logs.txt
            ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "/c", "type", "logs.txt");
            
            // 'findstr' filtra las líneas que contengan "ERROR"
            ProcessBuilder pb2 = new ProcessBuilder("findstr", "ERROR");
            
            // 'find /c /v ""' cuenta el número de líneas recibidas
            ProcessBuilder pb3 = new ProcessBuilder("cmd.exe", "/c", "find", "/c", "/v", "\"\"");

            // 2. Iniciar el pipeline completo (disponible a partir de Java 9)
            List<Process> procesos = ProcessBuilder.startPipeline(
                Arrays.asList(pb1, pb2, pb3)
            );

            // 3. Obtener el último proceso para leer el resultado de la tubería
            Process ultimoProceso = procesos.get(procesos.size() - 1);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(ultimoProceso.getInputStream()))) {
                
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println("Número de líneas con ERROR: " + linea.trim());
                }
            }

            // 4. Esperar a que finalicen todos los procesos de la cadena
            for (Process p : procesos) {
                p.waitFor();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
