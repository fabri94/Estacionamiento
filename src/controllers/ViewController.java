package controllers;

import excepciones.EstacionamientoLlenoException;
import excepciones.VehiculoRepetidoException;
import interfaces.ISerializableCSV;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;
import utils.*;

/**
 * FXML Controller class
 *
 * @author Fabri
 */
public class ViewController implements Initializable {
    @FXML
    private Button btnAgregar;
    
    @FXML
    private Button btnModificar;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    private ListView<Vehiculo> listViewVehiculos;
    
    private Estacionamiento estacionamiento;
    
    private ProcesadorArchivoCSV<ISerializableCSV> procesador;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estacionamiento = new Estacionamiento();
        procesador = new ProcesadorArchivoCSV<>();
        this.leerArchivoCSV();
    }    
    
    @FXML
    public void abrirFormulario(Vehiculo vehiculoExistente){
        try{
            //Cargo la vista del FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/formulario.fxml"));
            //SETEO LA ESCENA
            Scene scene = new Scene(loader.load());
            
            FormularioController controller = loader.getController();
            
            controller.mostrarDatosVehiculo(vehiculoExistente);
            
            Stage stage = new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);            
            stage.showAndWait();
            
            Vehiculo resultado = controller.getVehiculo();
            
            /*if(resultado!=null){
                if(vehiculoExistente==null){
                    estacionamiento.agregarVehiculo(resultado);
                }
            }*/
            
            if(resultado!=null) 
            {
                if(vehiculoExistente==null){
                    try{
                        estacionamiento.agregarVehiculo(resultado);
                    }catch(VehiculoRepetidoException e){
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error");
                        alerta.setHeaderText("Patente duplicada");
                        alerta.setContentText("No se puede agregar el vehiculo\n"+resultado.toString()+"\n"+e.getMessage());
                        alerta.showAndWait();
                    }catch(EstacionamientoLlenoException e){
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error");
                        alerta.setHeaderText("Estacionamiento lleno");
                        alerta.setContentText("No se puede agregar el vehiculo\n"+e.getMessage());
                        alerta.showAndWait();
                    }
                }
            }            
            this.actualizarVista();
            
        }catch(IOException ex){
            
        }
    }
    
    @FXML
    public void agregarVehiculo(ActionEvent e){
        this.abrirFormulario(null);
    }
    
    @FXML
    public void modificarVehiculo(ActionEvent e){
        Vehiculo vehiculo = listViewVehiculos.getSelectionModel().getSelectedItem();
        if(vehiculo!=null){
            this.abrirFormulario(vehiculo);
        }
        this.actualizarVista();
    }
    
    @FXML
    public void eliminarVehiculo(ActionEvent e){
        Vehiculo vehiculo = this.listViewVehiculos.getSelectionModel().getSelectedItem();
        if(vehiculo!=null){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar eliminacion");
            alerta.setHeaderText("¿Esta seguro que desea eliminar este vehiculo?\n");
            alerta.setContentText(vehiculo.toString());
            
            Optional<ButtonType> resultado = alerta.showAndWait();
            
            if(resultado.isPresent()&&resultado.get()==ButtonType.OK){
                this.estacionamiento.eliminarVehiculo(vehiculo);
                this.actualizarVista();
            }
        }
    }
    
    public void actualizarVista(){
        this.listViewVehiculos.getItems().clear();
        this.listViewVehiculos.getItems().addAll(estacionamiento.getVehiculos());
        this.guardarArchivoCSV();
    }
    
    public void guardarArchivoCSV(){
        List<ISerializableCSV> data = new ArrayList<>();
        for(Vehiculo vehiculo : this.estacionamiento.getVehiculos()) {
            if(vehiculo instanceof ISerializableCSV item){
                data.add(item);
            }
        }
        this.procesador.escribirCSV(data);
    }
    
    public void leerArchivoCSV(){
        List<String[]> resultado = procesador.leerCSV("datos.csv");
        System.out.println("Entradas leídas del archivo: " + resultado.size());
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        for(String[] datos : resultado) {
            Vehiculo vehiculo = null;
            switch(datos[5]) 
            {
                case "Auto" -> vehiculo = new Auto().fromCSV(datos);
                
                case "Camioneta" -> vehiculo = new Camioneta().fromCSV(datos);
                
                case "Moto" -> vehiculo = new Moto().fromCSV(datos);
            }
            if (vehiculo != null) 
            {
                vehiculos.add(vehiculo);
            }
        }
        estacionamiento = new Estacionamiento();
        estacionamiento.getVehiculos().addAll(vehiculos);
        this.actualizarVista();
    }
    
    public void guardarArchivoJSON(){
        ArrayList<Vehiculo> data = new ArrayList<>(this.estacionamiento.getVehiculos());
        SerializadoraJSON.guardar(data, "vehiculos.json");
    }
}

