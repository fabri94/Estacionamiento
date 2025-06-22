package utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import models.*;

/**
 *
 * @author Fabri
 */
public class SerializadoraJSON {
    private static Gson gson;

    static {
        RuntimeTypeAdapterFactory<Vehiculo> adapter =
        RuntimeTypeAdapterFactory.of(Vehiculo.class, "type")
            .registerSubtype(Auto.class, "Auto")
            .registerSubtype(Camioneta.class, "Camioneta")
            .registerSubtype(Moto.class, "Moto");

        gson = new GsonBuilder()
        .registerTypeAdapterFactory(adapter)
        .setPrettyPrinting()
        .create();
    }

    public static <T> void guardar(ArrayList<T> lista, String path){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            gson.toJson(lista, writer);
        } catch(IOException e){
            System.out.println("Error al guardar JSON: " + e.getMessage());
        }
    }

    public static <T> ArrayList<T> leer(String path, Class<T> clase){
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            Type tipoLista = TypeToken.getParameterized(List.class, clase).getType();
            return gson.fromJson(br, tipoLista);
        } catch(Exception e){
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
        return null;
    }
}
