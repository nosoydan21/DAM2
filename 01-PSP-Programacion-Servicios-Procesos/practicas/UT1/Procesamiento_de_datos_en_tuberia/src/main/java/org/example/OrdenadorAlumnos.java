package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrdenadorAlumnos {

    public static void main(String[] args) {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        List<String> registros = new ArrayList<>();

        try {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                if (!linea.isBlank()) {
                    registros.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("OrdenadorAlumnos: error al leer la entrada: " + e.getMessage());
            System.exit(2);
        }

        registros.sort(Comparator.comparing(OrdenadorAlumnos::nombre));

        for (String registro : registros) {
            salida.println(registro);
        }
    }

    private static String nombre(String registro) {
        int coma = registro.indexOf(',');
        return coma >= 0 ? registro.substring(0, coma).trim() : registro.trim();
    }
}