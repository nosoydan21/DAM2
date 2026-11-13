package org.example.modelo;

public class Ropa extends Producto{
    final double IVA_ROPA=0.1;
    private String talla;
    private String material;

    public Ropa() {
    }

    public Ropa(int id, String nombre, double precio, int cantidad, String talla, String material) {
        super(id, nombre, precio, cantidad);
        this.talla = talla;
        this.material = material;
    }


    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }



    @Override
    public double calcularPrecio() {
        return 0;
    }

    @Override
    public String mostrarDetalles() {

        return "Ropa{" +
                "id="+getId()+
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", cantidad=" + getStock() +
                "talla='" + talla + '\'' +
                ", material='" + material + '\'' +
                '}';
    }



}
