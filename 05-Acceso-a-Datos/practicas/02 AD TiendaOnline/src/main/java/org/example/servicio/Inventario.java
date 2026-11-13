package org.example.servicio;

import org.example.dao.ProductoDAO;
import org.example.modelo.Producto;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;
    private ProductoDAO fuenteDatos;

    public Inventario() throws IOException, SQLException {
        listaProductos=new ArrayList<Producto>();
        fuenteDatos=new ProductoDAO();

    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
    public boolean buscarProductoID(int idProducto){
        for (Producto p:listaProductos){
            if (p.getId()==idProducto){
                return true;
            }
        }
        return false;
    }
    public boolean insertarProducto(Producto p){
        if (buscarProductoID(p.getId())){
            return false;
        }
        else{
            listaProductos.add(p);
            return true;
        }
    }

    public boolean venderProducto(int idProducto,int cantidad){
       return fuenteDatos.venderProducto(idProducto, cantidad);
    }

    public void reponerProducto(int idProducto, int cantidad){
        return fuenteDatos.reponerProducto(int idProducto, int cantidad);
    }
}
