package controllers;

import excepciones.VehiculoRepetidoException;
import java.io.IOException;
import java.net.URL;
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
            if(resultado!=null){
                if(vehiculoExistente==null){
                    if(!estacionamiento.getVehiculos().contains(resultado)){
                        estacionamiento.agregarVehiculo(resultado);
                    }else{
                        System.out.println("Vehiculo ya registrado");
                    }
                }
            }
            
            this.refrescarVista();
            
        }catch(IOException | VehiculoRepetidoException ex){
            Alert alerta = new Alert(Alert.AlertType.valueOf(ex.getMessage()));
            alerta.setTitle("Error");
            alerta.setHeaderText("Error en la carga del vehiculo\n");
            //alerta.setContentText(resultado.toString());
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
        this.refrescarVista();
    }
    
    @FXML
    public void eliminarVehiculo(ActionEvent e){
        Vehiculo vehiculo = this.listViewVehiculos.getSelectionModel().getSelectedItem();
        if(vehiculo!=null){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar eliminacion");
            alerta.setHeaderText("¿Esta seguro que desea eliminar este vehiculo?:\n");
            alerta.setContentText(vehiculo.toString());
            
            Optional<ButtonType> resultado = alerta.showAndWait();
            
            if(resultado.isPresent()&&resultado.get()==ButtonType.OK){
                this.estacionamiento.eliminarVehiculo(vehiculo);
                this.refrescarVista();
            }
        }
    }
    
    public void refrescarVista(){
        this.listViewVehiculos.getItems().clear();
        this.listViewVehiculos.getItems().addAll(estacionamiento.getVehiculos());
    }
}
