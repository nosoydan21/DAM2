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

/**
 * ============================================================
 * PROCESO 3 DE LA TUBERÍA: ORDENADOR
 * ============================================================
 * Lee por STDIN todas las líneas, las ORDENA por nombre (alfabético) y las escribe por STDOUT.
 * (Equivale al comando "sort".)
 *
 * Por qué hay que leer TODO antes de escribir: para ordenar necesitas tener
 * todos los datos en memoria; no se puede ordenar "sobre la marcha".
 */
public class OrdenadorAlumnos {

    public static void main(String[] args) {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        PrintWriter salida = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        List<String> registros = new ArrayList<>();

        try {
            // 1) Leer todo y guardarlo en una lista
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

        // 2) Ordenar: comparamos solo por el NOMBRE (lo que hay antes de la primera coma)
        //    "OrdenadorAlumnos::nombre" = referencia al método nombre() de abajo.
        //    Para ordenar por NOTA descendente habría que cambiar este comparador.
        registros.sort(Comparator.comparing(OrdenadorAlumnos::nombre));

        // 3) Escribir el resultado ya ordenado
        for (String registro : registros) {
            salida.println(registro);
        }
    }

    // De "Ana López,AD,9.5" devuelve "Ana López"
    private static String nombre(String registro) {
        int coma = registro.indexOf(',');
        return coma >= 0 ? registro.substring(0, coma).trim() : registro.trim();
    }
}
