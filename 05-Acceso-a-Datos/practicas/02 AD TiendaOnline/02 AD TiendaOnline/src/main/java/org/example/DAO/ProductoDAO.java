package org.example.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection cn;

    public ProductoDAO() throws SQLException, IOException {
        ConexionBD conexionBD=new ConexionBD();
        cn= conexionBD.getCn();

    }


}
