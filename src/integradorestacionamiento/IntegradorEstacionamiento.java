package integradorestacionamiento;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fabri
 */
public class IntegradorEstacionamiento extends Application{

    public static void main(String[] args) {
        Application.launch();
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view.fxml"));
    
        Scene scene = new Scene(loader.load());
        
        stage.setScene(scene);
        
        stage.show();
    }
    
}
