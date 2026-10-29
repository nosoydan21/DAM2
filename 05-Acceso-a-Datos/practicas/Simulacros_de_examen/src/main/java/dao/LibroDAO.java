package dao;

import modelo.Libro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LibroDAO {
    public ArrayList<Libro> cargar(String ruta) throws IOException, ClassNotFoundException;

    public void guardar(String ruta, ArrayList<Libro> libros) throws IOException;
}
