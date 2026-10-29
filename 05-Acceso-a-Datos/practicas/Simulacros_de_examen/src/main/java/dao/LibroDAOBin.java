package dao;

import modelo.Libro;

import java.io.*;
import java.util.ArrayList;

public class LibroDAOBin implements LibroDAO {
    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Libro> cargar(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (ArrayList<Libro>) ois.readObject();
        }
    }

    @Override
    public void guardar(String ruta, ArrayList<Libro> libros) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))){
            oos.writeObject(libros);
        }
    }
}
