package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ProcesadorContable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //validar que estoy recibiendo un parámetro
        if (args.length < 1) {
            System.err.println("[Procesador] Error: Falta el nombre del archivo");
            System.exit(1);
        }
        String nomFichero = args[0];

        System.out.println("Iniciando procesador contable....");
        try (BufferedReader out = new BufferedReader(new FileReader(nomFichero))) {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "timeout /t 10 /nobreak");
            Process p = pb.start();
            Thread.sleep(2000);

            if (nomFichero.contains("CORRUPTO")) {
                System.err.println("[PROCESADOR] Archivo corrupto. Finalizando ejecución....");
                System.exit(2);
                System.out.println("Finalizado.");
            }

            System.out.println("[Procesador] OK: Archivo '" + nomFichero + "' leído correctamente." );
            System.exit(0);
        } catch (IOException e) {
            System.err.println("[Procesador] ERROR: No se ha podido guardar el fichero.");
        } catch (InterruptedException e) {
            System.out.println("[Procesador] ERROR: Interrupcion en el procesador contable.");
        }
    }
}