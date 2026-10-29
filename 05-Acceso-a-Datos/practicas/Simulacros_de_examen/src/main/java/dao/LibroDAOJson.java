package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelo.Libro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LibroDAOJson implements LibroDAO{
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public ArrayList<Libro> cargar(String ruta) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void guardar(String ruta, ArrayList<Libro> libros) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))){
            gson.toJson(libros, bw);
            bw.flush();
        }
    }
}
