package main.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class FavoritesController {


    @FXML

    private ListView<Content> favoritesList;

    @FXML

    private Button removeFavorite;

    @FXML

    private final ObservableList<Content> favorites = FXCollections.observableArrayList();

    public void initialize() {
        favoritesList.setItems(favorites);
        loadFavorites();

        removeFavorite.setOnAction(e -> removeSelected());
        favoritesList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) openSelected();
        });
    }

    public void loadFavorites() {
        List<Content> list = Content.showFavorites();
        favorites.setAll(list);
    }

    private void openSelected() {
        Content selected = favoritesList.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Package/FXML/Scene1.fxml"));
            Controller controller = new Controller();
            controller.setTargetDate(selected.getDate());

            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = (Stage) favoritesList.getScene().getWindow();
            Scene scene = new Scene(root, 390, 844);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void removeSelected() {
        Content selected = favoritesList.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        Content.updateFavoriteStatus(selected.getId(), false);
        favorites.remove(selected);
    }
}
