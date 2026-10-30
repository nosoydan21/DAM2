package org.example;

import org.example.dao.UsuarioDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {

    //    3 datos de la conexión a la base de datos (URL, USER, PASSWORD)
    private static final String URL = "jdbc:mariadb://localhost:3306/vozzo";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main() {
        try (Connection con = ConexionBD.getConexion()) {
            System.out.println("Conectado a la base de datos");

            UsuarioDAO dao = new UsuarioDAO();

            //Registrando usuario
//            Usuario usuario = new Usuario("Hugo", "1234");
//            dao.registrar(usuario);

            //Eliminando usuario
//            dao.eliminar("Jander");
//            dao.eliminar("Eydan");
//            dao.eliminar("Hugo");
            System.out.println("Registrado a la base de datos");
        } catch (SQLException ex) {
            System.err.println("Error al conectar: " +  ex.getMessage());
        }
    }

}
