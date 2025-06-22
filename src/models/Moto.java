package models;

import interfaces.*;

/**
 *
 * @author Fabri
 */
public class Moto extends Vehiculo implements ICobrable, ISerializableCSV{
    private int cilindrada;
    
    //CONSTRUCTOR VACIO PARA PODER USAR EL FROMCSV DESDE VISTA SIN TENER QUE CREAR UNA INSTANCIA DE MOTO PREVIAMENTE 
    public Moto(){
        
    }
    
    public Moto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora) {
        super(patente, marca, modelo, cantidadHoras, precioPorHora);
    }
    
    public Moto(String patente, String marca, String modelo, int cantidadHoras, double precioPorHora, int cilindrada){
        this(patente,marca,modelo,cantidadHoras,precioPorHora);
        this.cilindrada = cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Moto\n");
        sb.append(super.toString());
        sb.append(", Cilindrada = ").append(cilindrada);
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
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append(getClass().getSimpleName()).append(",");
        sb.append(this.cilindrada).append(",");
        sb.append(this.calcularPrecio());
        return sb.toString();
    }
    
    @Override
    public Moto fromCSV(String[] columnas){
        String patente = columnas[0];
        String marca = columnas[1];
        String modelo = columnas[2];
        int horas = Integer.parseInt(columnas[3]);
        double precio = Double.parseDouble(columnas[4]);
        String tipo = columnas[5];
        int cilindradaExt = Integer.parseInt(columnas[6]);
        return new Moto(patente, marca, modelo, horas, precio, cilindradaExt);       
    }
}
