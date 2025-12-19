package main.Package;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            //
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Package/FXML/Scene1.fxml"));
            Controller controller = new Controller();
            controller.setTargetDate(LocalDate.now());

            loader.setController(controller);

            Parent root = loader.load();

            Scene scene = new Scene(root, 390, 844);

            //Stage setup
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("main.Package.Main");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }

    }
}
