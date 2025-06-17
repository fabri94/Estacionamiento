package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Auto;
import models.Camioneta;
import models.Moto;
import models.Vehiculo;

/**
 *
 * @author Fabri
 * @param <T>
 */
public class ProcesadorArchivo<T extends Vehiculo>{
    
    public void escribirCSV(List<T> lista, String encabezado){
        String archivoCSV = "datos.csv";
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))){
            bw.write(encabezado);
            for(T item : lista){
                bw.write(item.toCSV());
                bw.newLine();
            }
        }catch(IOException e){
            System.err.println("Error al escribir el archivo "+archivoCSV+"\n"+ e.getMessage());
        }
    }
    
    public List<Vehiculo> leerCSV(String path ){
        List<Vehiculo> lista = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String linea;
            while((linea = br.readLine())!=null){
                String[] columnas = linea.split(",");
                Vehiculo instancia = null;
                switch(columnas[6]){
                    case"Auto"-> instancia = new Auto(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Double.parseDouble(columnas[4]));
                
                    case"Camioneta"->instancia = new Camioneta(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Double.parseDouble(columnas[4]));
                    
                    case"Moto"->instancia = new Moto(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Double.parseDouble(columnas[4]));
                }
                lista.add(instancia);
            }
        }catch(IOException e){
            System.err.println("Error al leer el archivo "+ e.getMessage());
        }
        return lista;
    }
    
    /*
    public List<T> leerCSV(String path ){
        //List<T> lista = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String linea;
            while((linea = br.readLine())!=null){
                String[] columnas = linea.split(",");
                Vehiculo instancia = null;
                switch(columnas[6]){
                    case"Auto"-> instancia = new Auto(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Double.parseDouble(columnas[4]));
                
                    case"Camioneta"->instancia = new Camioneta(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Double.parseDouble(columnas[4]));
                    
                    case"Moto"->instancia = new Moto(columnas[0],columnas[1],columnas[2],Integer.parseInt(columnas[3]),Double.parseDouble(columnas[4]));
                }
                lista.add((T) instancia);
            }
        }catch(IOException e){
            System.err.println("Error al leer el archivo "+ e.getMessage());
        }
        return lista;
    }
    */
    
    
}
