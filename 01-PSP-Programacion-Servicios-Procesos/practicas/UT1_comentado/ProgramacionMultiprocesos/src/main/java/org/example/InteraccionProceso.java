package org.example;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * ============================================================
 * CONCEPTO 4: ENVIAR DATOS AL PROCESO HIJO (su stdin)
 * ============================================================
 * ¿Para qué sirve?
 *   Que el hijo RECIBA datos desde tu programa, como si alguien los tecleara.
 *
 *   p.getOutputStream() -> canal HACIA el hijo (tú escribes, él lee su stdin)
 *   p.getInputStream()  -> canal DESDE el hijo (él escribe, tú lees su stdout)
 *
 * ¡MUY IMPORTANTE!  Hay que flush() y CERRAR el canal de escritura.
 * Cerrarlo equivale a decirle al hijo "ya no hay más datos" (fin de entrada).
 * Si no lo cierras y el hijo espera más, el programa se queda colgado.
 */
public class InteraccionProceso {
    public static void main(String[] args) throws Exception {
        // Lanzamos Python con un mini-programa que:
        //   1) lee UNA línea de su entrada estándar
        //   2) imprime "Hola " + esa línea
        // "-u" = salida sin buffer, "-c" = ejecutar el código que sigue.
        // (Requiere tener Python instalado y el comando "python" disponible.)
        ProcessBuilder pb = new ProcessBuilder("python", "-u", "-c",
                "import sys; nombre=sys.stdin.readline(); print('Hola '+nombre)");

        Process p = pb.start();

        // 1. Escribir en la entrada del hijo. El try-with-resources CIERRA el writer
        //    al salir del bloque -> el hijo recibe "fin de entrada".
        try (PrintWriter writer = new PrintWriter(p.getOutputStream())) {
            writer.println("Carlos"); // simulamos que el usuario escribe "Carlos" + Enter
            writer.flush();           // forzamos el envío inmediato (vacía el buffer)
        }

        // 2. Leer la respuesta del hijo
        try (Scanner scanner = new Scanner(p.getInputStream())) {
            if (scanner.hasNextLine()) {
                System.out.println("Respuesta recibida: " + scanner.nextLine());
            }
        }

        p.waitFor();
    }
}
