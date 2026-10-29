package dao;

import modelo.Libro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LibroDAOCSV implements LibroDAO{
    @Override
    public ArrayList<Libro> cargar(String ruta) throws IOException {
        ArrayList<Libro> libros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            int numLinea = 1;
            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                numLinea++;

                if (linea.trim().isEmpty()) {
                    System.err.println("La línea " + numLinea + " está vacía");
                    continue;
                }

                try {
                    String datos[] = linea.split(";");
                    if (datos.length != 5) {
                        System.err.println("Línea " + numLinea + ": debe tener 5 campos y tiene " +  datos.length);
                        continue;
                    }
                    int id = Integer.parseInt(datos[0].trim());
                    String titulo = datos[1].trim();
                    String autor = datos[2].trim();
                    double precio = Double.parseDouble(datos[3].trim());
                    String textDisp = datos[4].trim();

                    if (id <= 0) {
                        System.err.println("Error en línea " + numLinea + ": ID debe ser positivo");
                        continue;
                    }

                    if (precio <= 0) {
                        System.err.println("Error en línea " + numLinea + ": Precio debe ser positivo");
                        continue;
                    }

                    if (!textDisp.equals("true") && !textDisp.equals("false")) {
                        System.err.println("Error en línea " + numLinea + ":  Disponible debe ser true o false");
                        continue;
                    }

                    boolean disponible = Boolean.parseBoolean(textDisp);
                    libros.add(new Libro(id, titulo, autor, precio, disponible));

                } catch (NumberFormatException e) {
                    System.err.println("Error en la línea " + numLinea + ": número no válido -->" + e.getMessage());
                }
            }
        }
        return libros;
    }

    @Override
    public void guardar(String ruta, ArrayList<Libro> libros) throws IOException {
    }
}
