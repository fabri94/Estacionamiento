package models;

/**
 *
 * @author Fabri
 */
public class DatosVehiculoDTO {
    private String patente;
    private String marca;
    private String modelo;
    private int horas;
    private double precio;
    private int datoExtra;
    private String tipo;

    public DatosVehiculoDTO(String patente, String marca, String modelo, int horas, double precio, int datoExtra, String tipo) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.horas = horas;
        this.precio = precio;
        this.datoExtra = datoExtra;
        this.tipo = tipo;
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getHoras() {
        return horas;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDatoExtra() {
        return datoExtra;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    
}
