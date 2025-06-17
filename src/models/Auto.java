package models;

import interfaces.ICobrable;

/**
 *
 * @author Fabri
 */
public class Auto extends Vehiculo implements ICobrable{
    
    private int cantidadPuertas;
    //private static final int CANTIDAD_COLUMNAS_CSV = 7;
    
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
        
        sb.append("Auto \n");
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
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append("Precio total ").append(this.calcularPrecio()).append(",").append(getClass().getSimpleName());
        return sb.toString();
    }
    
    /*public static Auto fromCSV(String[] columnas){
        Auto autoLeido = null;
        if(columnas.length==CANTIDAD_COLUMNAS_CSV){
            autoLeido = new Auto(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Integer.parseInt(columnas[4]));
        }
        return autoLeido;
    }*/
}
