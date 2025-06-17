package controllers;

import excepciones.VehiculoRepetidoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Estacionamiento;
import models.Vehiculo;

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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estacionamiento = new Estacionamiento();
    }    
    
    @FXML
    public void agregar(ActionEvent e){
        try{
            //Cargo la vista del FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/formulario.fxml"));
            //SETEO LA ESCENA
            Scene scene = new Scene(loader.load());
            
            FormularioController controller = loader.getController();
            
            Stage stage = new Stage();
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);            
            stage.showAndWait();
            Vehiculo nuevo = controller.getVehiculo();
            if(nuevo!=null){
                this.estacionamiento.agregarVehiculo(nuevo);
                this.refrescarVista();
            }
            
        }catch(IOException | VehiculoRepetidoException ex){
            Alert alerta = new Alert(Alert.AlertType.valueOf(ex.getMessage()));
            alerta.setTitle("Error");
            alerta.setHeaderText("Error en la carga del vehiculo\n");
            //alerta.setContentText(nuevo.toString());
        }
        
        
    }
    
    @FXML
    public void modificar(ActionEvent e){
        try{
            
        }catch(RuntimeException ex){
            
        }
    }
    
    @FXML
    public void eliminar(ActionEvent e){
        try{
            
        }catch(RuntimeException ex){
            
        }
    }
    
    public void refrescarVista(){
        this.listViewVehiculos.getItems().clear();
        this.listViewVehiculos.getItems().addAll(estacionamiento.getVehiculos());
    }
}
