package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    public enum Estado {
        CONFIRMADO("Confirmado"),
        RESERVADO("Reservado"),
        CANCELADO("Cancelado");

        private final String texto;

        Estado(String texto) {
            this.texto = texto;
        }

        public String getTexto() {
            return texto;
        }
    }
    private String id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Profesor profesor;
    private Espacio espacio;
    private int numAsistentes;
    private Estado estado;

    public Reserva(String id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
                   Profesor profesor, Espacio espacio, int numAsistentes, Estado estado) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.profesor = profesor;
        this.espacio = espacio;
        this.numAsistentes = numAsistentes;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public int getNumAsistentes() {
        return numAsistentes;
    }

    public void setNumAsistentes(int numAsistentes) {
        this.numAsistentes = numAsistentes;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
