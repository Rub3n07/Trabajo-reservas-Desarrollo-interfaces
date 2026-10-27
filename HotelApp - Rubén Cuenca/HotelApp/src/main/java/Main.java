import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage ventana) throws Exception {
        // Cargamos la interfaz que hemos creado en el FXML.
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/vista.fxml"));
        Scene escena = new Scene(cargador.load());
        ventana.setTitle("HotelApp - Hotel Sol");
        ventana.setScene(escena);
        ventana.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
