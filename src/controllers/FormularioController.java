package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Auto;
import models.Camioneta;
import models.Moto;
import models.Vehiculo;


public class FormularioController implements Initializable {

    @FXML
    private ChoiceBox<String> cbTipo;
    
    @FXML
    private TextField txtPatente;
    
    @FXML
    private TextField txtMarca;
    
    @FXML
    private TextField txtModelo;
    
    @FXML
    private TextField txtCantidadDeHoras;
    
    @FXML
    private TextField txtPrecio;
    
    @FXML
    private TextField txtDatoExtra;
    
    @FXML
    private Button btnAceptar;
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Label lblDatoExtra;
    
    private Vehiculo vehiculo;
    private Vehiculo vehiculoAModificar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTipo.getItems().addAll("AUTO","CAMIONETA","MOTO");
        this.cbTipo.setValue("AUTO");
    }   
    
    @FXML
    public void tipoCambiado(){
        switch(cbTipo.getValue()){
            case"AUTO"-> lblDatoExtra.setText("CANTIDAD DE PUERTAS");
            
            case"CAMIONETA"->lblDatoExtra.setText("CAPACIDAD DE CARGA");
            
            case"MOTO"->lblDatoExtra.setText("CILINDRADA");
        }
    }
    
    @FXML
    public void aceptar(){
        String tipo = cbTipo.getValue();
        String patente = txtPatente.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        int cantidadDeHoras = Integer.parseInt(txtCantidadDeHoras.getText());
        int datoExtra = Integer.parseInt(txtDatoExtra.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        
        if(vehiculoAModificar!=null){
            this.vehiculo = modificarVehiculo(marca,modelo,cantidadDeHoras,precio,tipo,datoExtra);
        }else{
            this.vehiculo = crearVehiculo(patente,marca,modelo,cantidadDeHoras,precio,tipo,datoExtra);
        }
        this.cerrar();
    }
    
    @FXML
    public void cancelar(){
        this.cerrar();
    }
    
    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }
    
    
    private Vehiculo modificarVehiculo(String marca, String modelo, int cantidadDeHoras, double precio, String tipo, int datoExtra){
        vehiculoAModificar.setMarca(marca);
        vehiculoAModificar.setModelo(modelo);
        vehiculoAModificar.setCantidadHoras(cantidadDeHoras);
        vehiculoAModificar.setPrecioPorHora(precio);

        switch(tipo){
            case"AUTO"->((Auto)vehiculoAModificar).setCantidadPuertas(datoExtra);

            case"CAMIONETA"->((Camioneta)vehiculoAModificar).setCapacidadCarga(datoExtra);

            case"MOTO"->((Moto)vehiculoAModificar).setCilindrada(datoExtra);
        }
        return vehiculoAModificar;
    }
    
    private Vehiculo crearVehiculo(String patente, String marca, String modelo, int cantidadDeHoras, double precio, String tipo, int datoExtra){
        Vehiculo vehiculoCreado = null;
        switch(tipo){
            case"AUTO"->vehiculoCreado = new Auto(patente,marca,modelo,cantidadDeHoras,precio,datoExtra);

            case"CAMIONETA"->vehiculoCreado = new Camioneta(patente,marca,modelo,cantidadDeHoras,precio,datoExtra);

            case"MOTO"->vehiculoCreado = new Moto(patente,marca,modelo,cantidadDeHoras,precio,datoExtra);
        }
        return vehiculoCreado;
    }
    
    private void cerrar(){
        Stage stage =(Stage) btnCancelar.getScene().getWindow();
        
        stage.close();
    }
    
}
