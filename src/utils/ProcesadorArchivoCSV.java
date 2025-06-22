package utils;

import interfaces.ISerializableCSV;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabri
 * @param <T>
 */
public class ProcesadorArchivoCSV<T extends ISerializableCSV>{
    
    public void escribirCSV(List<T> lista){
        String archivoCSV = "datos.csv";
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))){
            for(T item : lista){
                bw.write(item.toCSV());
                bw.newLine();
            }
        }catch(IOException e){
            System.err.println("Error al escribir el archivo "+archivoCSV+"\n"+ e.getMessage());
        }
    }
    
    public List<String[]> leerCSV(String path){
        List<String[]> lista = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String linea;
            while((linea = br.readLine())!=null){
                if(!linea.isEmpty()){
                    lista.add(linea.split(","));
                }
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
                String instancia = null;
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
