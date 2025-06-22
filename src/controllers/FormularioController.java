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
import models.DatosVehiculoDTO;
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
        //DatosVehiculoDTO datosVehiculo = leerFormulario();
        
        if(vehiculo!=null){
            this.vehiculo = modificarVehiculo(marca,modelo,cantidadDeHoras,precio,tipo,datoExtra);
            //this.vehiculo = modificarVehiculo(datosVehiculo.getMarca(),datosVehiculo.getModelo(),datosVehiculo.getHoras(),datosVehiculo.getPrecio(),datosVehiculo.getTipo(),datosVehiculo.getDatoExtra());
        }else{
            this.vehiculo = crearVehiculo(patente,marca,modelo,cantidadDeHoras,precio,tipo,datoExtra);
            //this.vehiculo = crearVehiculo(datosVehiculo.getPatente(),datosVehiculo.getMarca(),datosVehiculo.getModelo(),datosVehiculo.getHoras(),datosVehiculo.getPrecio(),datosVehiculo.getTipo(),datosVehiculo.getDatoExtra());
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
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setCantidadHoras(cantidadDeHoras);
        vehiculo.setPrecioPorHora(precio);

        switch(tipo){
            case"AUTO"->((Auto)vehiculo).setCantidadPuertas(datoExtra);

            case"CAMIONETA"->((Camioneta)vehiculo).setCapacidadCarga(datoExtra);

            case"MOTO"->((Moto)vehiculo).setCilindrada(datoExtra);
        }
        return vehiculo;
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

    public void mostrarDatosVehiculo(Vehiculo vehiculoExistente) {
        this.vehiculo = vehiculoExistente;
        if(vehiculo!=null)
        {
            this.txtPatente.setText(vehiculo.getPatente());
            this.txtPatente.setFocusTraversable(false);
            this.txtPatente.setEditable(false);
            this.txtMarca.setText(vehiculo.getMarca());
            this.txtModelo.setText(vehiculo.getModelo());
            this.txtCantidadDeHoras.setText(String.valueOf(vehiculo.getCantidadHoras()));
            this.txtPrecio.setText(String.valueOf(vehiculo.getPrecioPorHora()));

            if(vehiculo instanceof Auto a){
                this.txtDatoExtra.setText(String.valueOf(a.getCantidadPuertas()));
                cbTipo.setValue("AUTO");
            }
            if(vehiculo instanceof Camioneta c){
                this.txtDatoExtra.setText(String.valueOf(c.getCapacidadCarga()));
                cbTipo.setValue("CAMIONETA");
            }
            if(vehiculo instanceof Moto m){
                this.txtDatoExtra.setText(String.valueOf(m.getCilindrada()));
                cbTipo.setValue("MOTO");
            }
        }
    }
    
    /*
    private DatosVehiculoDTO leerFormulario(){
        
        String tipo = cbTipo.getValue();
        String patente = txtPatente.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        int cantidadDeHoras = Integer.parseInt(txtCantidadDeHoras.getText());
        int datoExtra = Integer.parseInt(txtDatoExtra.getText());
        double precio = Double.parseDouble(txtPrecio.getText());
        
        return new DatosVehiculoDTO(patente,marca,modelo,cantidadDeHoras,precio,datoExtra,tipo);
        
    }
    */
}
