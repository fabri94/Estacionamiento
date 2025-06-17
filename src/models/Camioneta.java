package models;

import interfaces.ICobrable;

/**
 *
 * @author Fabri
 */
public class Camioneta extends Vehiculo implements ICobrable{
    private int capacidadCarga;
    //private static final int CANTIDAD_COLUMNAS_CSV = 7; 
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
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append("Precio total ").append(this.calcularPrecio()).append(",").append(getClass().getSimpleName());
        return sb.toString();
    }
    
    /*public static Camioneta fromCSV(String[] columnas){
        Camioneta camionetaLeida = null;
        if(columnas.length==5){
            camionetaLeida = new Camioneta(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Integer.parseInt(columnas[4]));
        }
        return camionetaLeida;
    }*/
}
