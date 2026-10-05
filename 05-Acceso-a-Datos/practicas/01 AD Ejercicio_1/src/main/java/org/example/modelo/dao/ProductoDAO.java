package org.example.modelo.dao;

import org.example.modelo.Producto;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class ProductoDAO {
    private final String FICHERODATOS="productos.csv";

    public void ProductoDAOFT() {

    }

    public ArrayList<Producto> leerDatos() throws IOException {
        ArrayList<Producto> productos=new ArrayList<Producto>();
        return productos;
    }
}
