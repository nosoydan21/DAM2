package org.example;

/**
 * ============================================================
 * EJERCICIO_PIPELINE (PENDIENTE DE HACER)
 * ============================================================
 * Fichero de datos: src/main/resources/datos_brutos.txt
 *   Formato de cada línea:  ID: 104 | Usuario: mgarcia | Fecha: 15/03/2023 | Estado: OK
 *
 * Qué hay que construir (siguiendo el modelo de Procesamiento_de_datos_en_tuberia):
 *   1) Proceso LECTOR   -> lee datos_brutos.txt y lo escribe por stdout
 *   2) Proceso FILTRO   -> deja solo las líneas con "Estado: OK"
 *   3) Proceso ORDENADOR-> ordena por ID ascendente (hay que extraer el número tras "ID:")
 *   4) Main             -> arma los 3 ProcessBuilder, redirige la salida del último a un fichero
 *                          y llama a ProcessBuilder.startPipeline(...)
 *
 * Pista para sacar el ID de una línea:
 *   Integer.parseInt(linea.split("\\|")[0].replace("ID:", "").trim())
 */
public class Main {
    public static void main(String[] args) {
        // TODO: montar la tubería aquí (o llamar a cargarDatos())
    }

    // TODO: método previsto para cargar/leer los datos brutos
    public static void cargarDatos(){

    }
}
