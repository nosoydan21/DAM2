package es.iesvicentemedina.carrera.modelo;

import java.io.Serializable;

public class Participacion implements Serializable {
    private final static long serialVersionUID=1L;

    private int dorsal;
    private String nombre;
    private int tiempoSegundos;
    private boolean finalizada;

    public Participacion(int dorsal, String nombre, int timepoSegundos, boolean finalizada) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.tiempoSegundos = timepoSegundos;
        this.finalizada = finalizada;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }

    public void setTiempoSegundos(int tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    @Override
    public String toString() {
        return "Dorsal: " + dorsal +
                " | Nombre: " + nombre +
                " | Tiempo (Segundos): " + tiempoSegundos +
                " | Finalizada: " + finalizada;
    }
}
