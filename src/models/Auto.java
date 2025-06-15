package models;

import interfaces.ICobrable;

/**
 *
 * @author Fabri
 */
public class Auto extends Vehiculo implements ICobrable{
    
    private int cantidadPuertas;

    public Auto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora) {
        super(patente, marca, modelo, cantidadHoras, precioPorHora);
    }
    
    public Auto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora, int cantidadPuertas){
        this(patente, marca, modelo,cantidadHoras,precioPorHora);
        this.cantidadPuertas = cantidadPuertas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Moto \n");
        sb.append(super.toString());
        sb.append("Cantidad puertas = ").append(cantidadPuertas);
        return sb.toString();
    }  
    
    @Override
    public double getIncremento() {
        return 0.20;
    }

    @Override
    public double calcularPrecio() {
        double valor = this.getCantidadHoras() * this.getPrecioPorHora();
        double valorIncrementado = valor + valor * getIncremento();
        return valorIncrementado;
    }
    
}
