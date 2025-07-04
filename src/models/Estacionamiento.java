package models;

import excepciones.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabri
 */
public class Estacionamiento {
    private List<Vehiculo> vehiculos;
    private int capacidadMaxima;

    public Estacionamiento(){
        this.capacidadMaxima = 2;
        this.vehiculos = new ArrayList<>();
    }
    
    public Estacionamiento(int capacidadMaxima) {
        this();
        this.capacidadMaxima = capacidadMaxima;
    }
        
    public void agregarVehiculo(Vehiculo v){
        if(vehiculos.contains(v)){
            throw new VehiculoRepetidoException("Ya existe un vehículo con la patente: " + v.getPatente());
        }
        vehiculos.add(v);
        //System.out.println("Vehiculo agregado");
    }
    
    public void eliminarVehiculo(Vehiculo v){
        if(v!=null)
        {
            if(buscarVehiculo(v.getPatente()))
            {
                vehiculos.remove(v);
            }
        }
    }
    
    private boolean buscarVehiculo(String patente){
        boolean seEncontro = false;
        for (Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getPatente().equals(patente)){
                seEncontro = true;
                break;
            }
        }
        return seEncontro;
    }
    
    public List<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
    public boolean estaLleno(){
        return this.vehiculos.size() == capacidadMaxima;
    }
}
