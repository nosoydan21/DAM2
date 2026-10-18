package org.example;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * ============================================================
 * CONCEPTO 3: CICLO DE VIDA DE UN PROCESO (controlarlo y matarlo)
 * ============================================================
 * ¿Para qué sirve?
 *   Evitar quedarte esperando eternamente a un proceso que se cuelga.
 *   Esperas un tiempo máximo y, si no acaba, lo terminas tú.
 *
 * Herramientas:
 *   pid()                 -> número identificador del proceso en el sistema
 *   isAlive()             -> ¿sigue vivo?
 *   waitFor()             -> espera sin límite (devuelve código de salida)
 *   waitFor(n, unidad)    -> espera como máximo n; devuelve true si terminó, false si no
 *   destroy()             -> petición educada de terminar (puede ignorarse)
 *   destroyForcibly()     -> lo mata a la fuerza (no se puede ignorar)
 *   onExit()              -> callback ASÍNCRONO: se ejecuta cuando termine, sin bloquear
 */
public class ControlCicloVidaProceso {
    public static void main(String[] args) {
        boolean esWindows = System.getProperty("os.name").toLowerCase().contains("win");

        // Comando que simula un trabajo largo (10 segundos)
        ProcessBuilder pb = esWindows
                ? new ProcessBuilder("cmd.exe", "/c", "timeout /t 10 /nobreak")
                : new ProcessBuilder("sleep", "10");
        try {
            System.out.println("=== INICIANDO SUBPROCESO ===");
            Process proceso = pb.start();
            long pid = proceso.pid();
            System.out.println("Proceso iniciado con PID: " + pid);

            // 1. onExit() devuelve un "CompletableFuture": una promesa de que algo pasará
            //    cuando el proceso acabe. thenAccept(...) define QUÉ hacer entonces.
            //    No bloquea: el programa sigue ejecutándose mientras tanto.
            proceso.onExit().thenAccept(p ->
                    System.out.println("[Callback Asíncrono] El proceso PID " + p.pid() + " ha finalizado.")
            );

            // 2. Espera con TIMEOUT: máximo 3 segundos (el proceso dura 10, así que NO llegará)
            System.out.println("Esperando finalización (máximo 3 segundos)...");
            boolean terminadoApertura = proceso.waitFor(3, TimeUnit.SECONDS);

            // 3. Si no terminó a tiempo: primero amable, luego a la fuerza
            if (!terminadoApertura) {
                System.out.println("ATENCIÓN: El proceso ha superado el límite de tiempo de 3s.");
                System.out.println("Enviando orden de terminación limpia (destroy)...");
                proceso.destroy();
                // Damos 2 segundos de margen para que obedezca
                if (!proceso.waitFor(2, TimeUnit.SECONDS)) {
                    System.out.println("El proceso no responde. Forzando terminación abrupta (destroyForcibly)...");
                    proceso.destroyForcibly();
                }
            }

            // 4. Espera final y lectura del código de salida.
            //    Si lo hemos matado, NO será 0 (en Linux suele ser 143; en Windows 1).
            int codigoSalida = proceso.waitFor();
            System.out.println("\n=== RESUMEN DE EJECUCIÓN ===");
            System.out.println("PID: " + pid);
            System.out.println("¿Está vivo?: " + proceso.isAlive()); // debe ser false
            System.out.println("Código de salida final: " + codigoSalida);
        } catch (IOException e) {
            System.err.println("Error de E/S al lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El hilo principal fue interrumpido: " + e.getMessage());
            // Buena práctica: restaurar el estado de "interrumpido" del hilo
            Thread.currentThread().interrupt();
        }
    }
}
