package org.example;

public class Usuario {
    private int idUsuario;
    private String nickname;
    private String password;

    public Usuario(int idUsuario, String nickname, String password) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
