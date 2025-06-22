package models;

import interfaces.*;

/**
 *
 * @author Fabri
 */
public class Camioneta extends Vehiculo implements ICobrable, ISerializableCSV{
    private int capacidadCarga;
    
    public Camioneta(){
        
    }
    
    public Camioneta(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora) {
        super(patente, marca, modelo, cantidadHoras, precioPorHora);
    }
    
    public Camioneta(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora, int capacidadCarga){
        this(patente,marca,modelo,cantidadHoras,precioPorHora);
        this.capacidadCarga = capacidadCarga;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Camioneta\n");
        sb.append(super.toString());
        sb.append(", Capacidad de carga = ").append(capacidadCarga);
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
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append(getClass().getSimpleName()).append(",");
        sb.append(this.capacidadCarga).append(",");
        sb.append(this.calcularPrecio());
        return sb.toString();
    }
    
    @Override
    public Camioneta fromCSV(String[] columnas){
        String patente = columnas[0];
        String marca = columnas[1];
        String modelo = columnas[2];
        int horas = Integer.parseInt(columnas[3]);
        double precio = Double.parseDouble(columnas[4]);
        String tipo = columnas[5];
        int capacidad = Integer.parseInt(columnas[6]);
        return new Camioneta(patente, marca, modelo, horas, precio, capacidad);       
    }
}
