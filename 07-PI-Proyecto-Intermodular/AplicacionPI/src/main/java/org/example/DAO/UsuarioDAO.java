package org.example.dao;

import org.example.ConexionBD;
import org.example.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public boolean registrar(Usuario usuario) {
        String sql = "INSERT INTO USUARIO(nickname, password) VALUES (?,?)";
        String hash = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getNickname());
            ps.setString(2, hash);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String nickname) {
        String sql = "DELETE FROM USUARIO WHERE nickname = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nickname);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException ex) {
            System.err.println("Error al eliminar: " + ex.getMessage());
            return false;
        }
    }

    public boolean iniciarSesion(Usuario usuario) {
        String sql = "SELECT * FROM USUARIO WHERE nickname = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getNickname());

            try (ResultSet rs = ps.executeQuery()) {
                String hash = rs.getString("password");

                if(BCrypt.checkpw(hash, usuario.getPassword())) {
                    registrar(usuario);
                    return true;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error al iniciar sesion: " + ex.getMessage());
            return false;
        }
    }
}
