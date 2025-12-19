package main.Package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {

    @FXML

    private TextField searchBox;

    @FXML

    private ListView<Content> itemList;

    @FXML

    private final ObservableList<Content> items = FXCollections.observableArrayList();

    @FXML

    public void initialize() {
        loadAllContent();
        itemList.setItems(items);
        itemList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                openSelected();
            }
        });

    }

    @FXML
    private void doSearch() {
        String input = searchBox.getText();
        if (input == null || input.isBlank()) {
            loadAllContent();
            return;
        }
        List<Content> results = searchList(input, items);
        items.setAll(results);
    }

    private void loadAllContent() {
        List<Content> allContent = Content.getAllContents();
        items.setAll(allContent);
    }

    private List<Content> searchList(String searchWords, List<Content> listOfItems){
        List<String> searchWordsArray = List.of(searchWords.trim().split(" "));
        return listOfItems.stream()
                .filter(content ->
                        searchWordsArray.stream().allMatch(word ->
                        content.getTitle().toLowerCase().contains(word.toLowerCase()) ||
                        content.getDescription().toLowerCase().contains(word.toLowerCase())))
                .collect(Collectors.toList());
    }

    private void openSelected() {
        Content selected = itemList.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Package/FXML/Scene1.fxml"));
            Controller controller = new Controller();
            controller.setTargetDate(selected.getDate());

            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = (Stage) itemList.getScene().getWindow();
            Scene scene = new Scene(root, 390, 844);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
