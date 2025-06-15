package models;

import interfaces.ICobrable;

/**
 *
 * @author Fabri
 */
public class Camioneta extends Vehiculo implements ICobrable{
    private int capacidadCarga;

    public Camioneta(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora) {
        super(patente, marca, modelo, cantidadHoras, precioPorHora);
    }
    
    public Camioneta(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora, int capacidadCarga){
        this(patente,marca,modelo,cantidadHoras,precioPorHora);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Camioneta \n");
        sb.append(super.toString());
        sb.append("Capacidad de carga = ").append(capacidadCarga);
        return sb.toString();
    }  
    
    @Override
    public double getIncremento() {
        return 0.30;
    }

    @Override
    public double calcularPrecio() {
        double valor = this.getCantidadHoras() * this.getPrecioPorHora();
        double valorIncrementado = valor + valor * getIncremento();
        return valorIncrementado;
    }
    
    
}
