package models;

import java.util.Objects;

/**
 *
 * @author Fabri
 */
public class Vehiculo {
    private String patente;
    private String marca;
    private String modelo;
    private int cantidadHoras;
    private double precioPorHora;

    public Vehiculo(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.cantidadHoras = cantidadHoras;
        this.precioPorHora = precioPorHora;
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

    public int getCantidadHoras() {
        return cantidadHoras;
    }

    public double getPrecioPorHora() {
        return precioPorHora;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCantidadHoras(int cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public void setPrecioPorHora(double precioPorHora) {
        this.precioPorHora = precioPorHora;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        return Objects.equals(this.patente, other.patente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Patente = ").append(patente);
        sb.append(", Marca = ").append(marca);
        sb.append(", Modelo = ").append(modelo);
        sb.append(", Cantidad de horas = ").append(cantidadHoras);
        sb.append(", Precio por hora = ").append(precioPorHora);
        return sb.toString();
    }
    
    
}
