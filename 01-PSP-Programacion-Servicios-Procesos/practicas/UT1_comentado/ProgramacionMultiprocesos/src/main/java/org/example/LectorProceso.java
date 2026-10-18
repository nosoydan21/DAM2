package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * ============================================================
 * CONCEPTO 1: LANZAR UN PROCESO Y LEER LO QUE ESCRIBE
 * ============================================================
 * ¿Para qué sirve?
 *   Tu programa Java (el PADRE) ejecuta otro programa del sistema
 *   (el HIJO, aquí "dir" o "ls") y recoge su resultado para usarlo.
 *
 * Idea clave: todo proceso tiene 3 canales:
 *   stdin  = entrada   | stdout = salida normal | stderr = errores
 *   Aquí leemos el stdout del hijo.
 */
public class LectorProceso {
    public static void main(String[] args) {
        // Detectamos el sistema operativo porque los comandos cambian:
        // Windows -> "dir"  |  Linux/Mac -> "ls -la"
        boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");

        // En Windows, "dir" no es un programa, es un comando interno de cmd,
        // por eso hay que llamarlo con: cmd.exe /c dir
        List<String> comando = esWindows ? List.of("cmd.exe", "/c", "dir") : List.of("ls", "-la");

        // 1. PREPARAR: ProcessBuilder solo "prepara" el comando, todavía no lo ejecuta.
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            // 2. LANZAR: start() crea el proceso hijo y devuelve un objeto Process
            //    (tu "mando a distancia" del hijo).
            Process proceso = pb.start();

            // 3. LEER: getInputStream() es la SALIDA del hijo vista desde tu programa
            //    (el nombre confunde: es "input" para ti porque TÚ lo lees).
            //    BufferedReader nos permite leer línea a línea con readLine().
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                // readLine() devuelve null cuando el hijo ya no escribe más
                while ((linea = reader.readLine()) != null) {
                    System.out.println("[SALIDA HIJO] " + linea);
                }
            }

            // 4. ESPERAR: waitFor() bloquea hasta que el hijo termina y devuelve
            //    su CÓDIGO DE SALIDA. Convenio: 0 = todo bien, otro número = error.
            int codigoSalida = proceso.waitFor();
            System.out.println("Proceso finalizado con código: " + codigoSalida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
