package PruebaJList.ListaAlumnos;

public class Alumno {

    private String nombre;
    private String apellidos;
    private int edad;
    private int nre;

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos, int edad, int nre) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nre = nre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNre() {
        return nre;
    }

    public void setNre(int nre) {
        this.nre = nre;
    }

    @Override
    public String toString() {
        return nombre +"  " + apellidos;
    }
}
