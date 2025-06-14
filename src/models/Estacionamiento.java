package models;

import excepciones.EstacionamientoLlenoException;
import excepciones.VehiculoNoEncontradoException;
import excepciones.VehiculoRepetidoException;
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
        this.capacidadMaxima = 5;
        this.vehiculos = new ArrayList<>();
    }
    
    public Estacionamiento(int capacidadMaxima) {
        this();
        this.capacidadMaxima = capacidadMaxima;
    }
    
    public void agregarVehiculo(Vehiculo v){
        if(vehiculos.contains(v)){
            throw new VehiculoRepetidoException("El vehiculo ya esta registrado en el estacionamiento ");
        }
        if(vehiculos.size()==capacidadMaxima){
            throw new EstacionamientoLlenoException("No hay mas lugar disponible en el estacionamiento ");
        }
        vehiculos.add(v);
        System.out.println("Vehiculo agregado");
    }
    
    public void eliminarVehiculo(Vehiculo v){
        if(v!=null){
            if(buscarVehiculo(v.getPatente())){
                vehiculos.remove(v);
            }else{
                throw new VehiculoNoEncontradoException("El vehiculo no se encuentra en los registros");
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
    
    /*public void modificarVehiculo(Vehiculo v){
        if(!vehiculos.contains(v)){
            throw new VehiculoNoEncontradoException("El vehiculo no se encuentra en los registros");
        }else{
            
        }
    }*/
    
    public List<Vehiculo> getVehiculos(){
        return vehiculos;
    }
}
