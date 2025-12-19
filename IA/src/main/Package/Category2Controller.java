package main.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

/*
 * Class purpose: Stores a list of content assigned to it.
 */

public class Category2Controller {

    @FXML

    private ListView<Content> category2List;

    @FXML

    private final ObservableList<Content> categoryItems2 = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        loadCategoryItems();
        category2List.setItems(categoryItems2);

        category2List.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                openSelected();
            }
        });
    }

    private void openSelected() {
        Content selected = category2List.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Package/FXML/Scene1.fxml"));
            Controller controller = new Controller();
            controller.setTargetDate(selected.getDate());

            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = (Stage) category2List.getScene().getWindow();
            Scene scene = new Scene(root, 390, 844);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void loadCategoryItems() {
        List<Content> list = Content.showCategory2Items();
        categoryItems2.setAll(list);
    }
}
