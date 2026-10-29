package org.example;

import dao.LibroDAOBin;
import dao.LibroDAOCSV;
import dao.LibroDAOJson;
import modelo.Libro;

import java.io.IOException;
import java.util.ArrayList;

public class Simulacro1 {
    public static void main(String[] args) {

        LibroDAOCSV csv = new LibroDAOCSV();
        LibroDAOBin bin = new LibroDAOBin();
        LibroDAOJson json = new LibroDAOJson();

        try {
            ArrayList<Libro> cargados = csv.cargar("datos/libros.csv");
            bin.guardar("datos/libros.dat", cargados);
            ArrayList<Libro> recuperados = bin.cargar("datos/libros.dat");

            boolean encontrado = false;

            for (Libro libro : recuperados) {
                if (libro.getIdLibro() == 5) {
                    libro.setDisponible(false);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.err.println("Libro no encontrado");
            }

            bin.guardar("datos/libros.dat", recuperados);

            ArrayList<Libro> disponibles = new ArrayList<>();
            for (Libro libro : recuperados) {
                if (libro.isDisponible()) {
                    disponibles.add(libro);
                }
            }
            json.guardar("datos/disponibles.json", disponibles);

            System.out.println("Cargados: " + cargados.size());
            System.out.println("Recuperados: " + recuperados.size());
            System.out.println("Exportados: " + disponibles.size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}