package models;

import interfaces.*;

/**
 *
 * @author Fabri
 */
public class Auto extends Vehiculo implements ICobrable, ISerializableCSV{
    
    private int cantidadPuertas;
    
    public Auto(){
        
    }
    
    public Auto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora) {
        super(patente, marca, modelo, cantidadHoras, precioPorHora);
    }
    
    public Auto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora, int cantidadPuertas){
        this(patente, marca, modelo,cantidadHoras,precioPorHora);
        this.cantidadPuertas = cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Auto\n");
        sb.append(super.toString());
        sb.append(", Cantidad puertas = ").append(cantidadPuertas);
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
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append(getClass().getSimpleName()).append(",");
        sb.append(this.cantidadPuertas).append(",");
        sb.append(this.calcularPrecio());
        return sb.toString();
    }
    
    @Override
    public Auto fromCSV(String[] columnas){
        String patente = columnas[0];
        String marca = columnas[1];
        String modelo = columnas[2];
        int horas = Integer.parseInt(columnas[3]);
        double precio = Double.parseDouble(columnas[4]);
        String tipo = columnas[5];
        int puertas = Integer.parseInt(columnas[6]);        
        return new Auto(patente, marca, modelo, horas, precio, puertas);       
    }
}
