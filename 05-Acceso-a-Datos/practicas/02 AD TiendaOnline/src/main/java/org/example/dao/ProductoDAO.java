package org.example.dao;

import org.example.modelo.Producto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection cn;

    public ProductoDAO() throws SQLException, IOException {
        ConexionBD conexionBD=new ConexionBD();
        cn= conexionBD.getCn();

    }

    public boolean buscarProductos(int idProducto) throws SQLException, IOException{
        return true;
        //Realizo una consulta en base de datos para comprobar si exite el producto
    }

    public void insertarProducto(Producto producto) throws SQLException {
        //Insertar el producto en la base de datos
    }


    public boolean venderProducto(int idProducto, int cantidad) {
        //Modificar la cantidad de producto si hay stock
        return true;
    }

    public
}
