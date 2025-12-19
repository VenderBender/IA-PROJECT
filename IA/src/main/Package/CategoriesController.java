package main.Package;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/*
 * Class purpose: Acts as a hub for the different categories. Stores
 * Buttons which lead to different category lists when clicked.
 */

public class CategoriesController {

    public void switchToCat1 (javafx.event.ActionEvent event) {
        try {
            Parent searchRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/Package/FXML/Category1.fxml")));
            Scene searchScene = new Scene(searchRoot);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(searchScene);
            currentStage.setTitle("Category 1");
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToCat2 (javafx.event.ActionEvent event) {
        try {
            Parent searchRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/Package/FXML/Category2.fxml")));
            Scene searchScene = new Scene(searchRoot);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(searchScene);
            currentStage.setTitle("Category 2");
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToCat3 (javafx.event.ActionEvent event) {
        try {
            Parent searchRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/Package/FXML/Category3.fxml")));
            Scene searchScene = new Scene(searchRoot);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(searchScene);
            currentStage.setTitle("Category 3");
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToCat4 (javafx.event.ActionEvent event) {
        try {
            Parent searchRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/Package/FXML/Category4.fxml")));
            Scene searchScene = new Scene(searchRoot);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(searchScene);
            currentStage.setTitle("Category 4");
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
