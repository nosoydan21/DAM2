package org.example;

import java.time.LocalDate;

public class OpiUsuario {
    private Usuario usuario;
    private Empresa empresa;
    private String textoOpinion;
    private LocalDate fechaOpinion;
    private int idOpinion;

    public OpiUsuario(Usuario usuario, Empresa empresa, String textoOpinion, LocalDate fechaOpinion, int idOpinion) {
        this.usuario = usuario;
        this.empresa = empresa;
        this.textoOpinion = textoOpinion;
        this.fechaOpinion = fechaOpinion;
        this.idOpinion = idOpinion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTextoOpinion() {
        return textoOpinion;
    }

    public void setTextoOpinion(String textoOpinion) {
        this.textoOpinion = textoOpinion;
    }

    public LocalDate getFechaOpinion() {
        return fechaOpinion;
    }

    public void setFechaOpinion(LocalDate fechaOpinion) {
        this.fechaOpinion = fechaOpinion;
    }

    public int getIdOpinion() {
        return idOpinion;
    }

    public void setIdOpinion(int idOpinion) {
        this.idOpinion = idOpinion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
