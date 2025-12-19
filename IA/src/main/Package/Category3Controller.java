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

public class Category3Controller {

    @FXML

    private ListView<Content> category3List;

    @FXML

    private final ObservableList<Content> categoryItems3 = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        loadCategoryItems();
        category3List.setItems(categoryItems3);

        category3List.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                openSelected();
            }
        });

    }

    private void openSelected() {
        Content selected = category3List.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Package/FXML/Scene1.fxml"));
            Controller controller = new Controller();
            controller.setTargetDate(selected.getDate());

            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = (Stage) category3List.getScene().getWindow();
            Scene scene = new Scene(root, 390, 844);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void loadCategoryItems() {
        List<Content> list = Content.showCategory3Items();
        categoryItems3.setAll(list);
    }
}
