package models;

import interfaces.ICobrable;

/**
 *
 * @author Fabri
 */
public class Moto extends Vehiculo implements ICobrable{
    private int cilindrada;

    public Moto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora) {
        super(patente, marca, modelo, cantidadHoras, precioPorHora);
    }
    
    public Moto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora, int cilindrada){
        this(patente,marca,modelo,cantidadHoras,precioPorHora);
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Moto \n");
        sb.append(super.toString());
        sb.append("Cilindrada = ").append(cilindrada);
        return sb.toString();
    }  
    
    @Override
    public double getIncremento() {
        return 0.10;
    }

    @Override
    public double calcularPrecio() {
        double valor = this.getCantidadHoras() * this.getPrecioPorHora();
        double valorIncrementado = valor + valor * getIncremento();
        return valorIncrementado;
    }
    
    
}
