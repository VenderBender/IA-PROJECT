package main.Package;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javafx.scene.control.Label;



public class Controller {

    @FXML
    private Button addToFavorite;

    @FXML
    private ImageView picture;

    @FXML
    private Content currentContent;

    @FXML
    private TextArea description;

    @FXML
    private Label label;

    @FXML
    private Label currentDate;

    private LocalDate targetDate;


    public void setTargetDate(LocalDate date) {
        this.targetDate = date;
    }

    @FXML
    public void initialize() {
        LocalDate date = targetDate != null ? targetDate : LocalDate.now();

        // Convert date into string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateString = date.format(formatter);

        Content.getContentByDate(date).ifPresentOrElse(content -> {
            label.setText(content.getTitle());
            description.setText(content.getDescription());
            currentDate.setText(dateString);
            currentContent = content;

            String imageLoc = currentContent.getImage();
            File image = new File(imageLoc);
            if (image.exists()) {

                picture.setImage(new Image(image.toURI().toString()));
                System.out.println("image load successful");
            } else {
                picture.setImage(null);
            }
            System.out.println("Absolute Path: " + image.getAbsolutePath());
            System.out.println("Exists: " + image.exists());
            System.out.println("Can Read: " + image.canRead());
            System.out.println("Working Directory: " + System.getProperty("user.dir"));


                boolean fav = Content.isFavorited(currentContent.getId());
            addToFavorite.setText(fav ? "★" : "☆");
        }, () -> {
            description.setText("No description: " + date);
            label.setText("No description: " + date);
        });
    }

    public void switchToSearch (javafx.event.ActionEvent event) {
        try {
            Parent searchRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/Package/FXML/Search.fxml")));
            Scene searchScene = new Scene(searchRoot);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(searchScene);
            currentStage.setTitle("Search");
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void switchToFavorites (javafx.event.ActionEvent event) {
        try {
            Parent favoritesRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/Package/FXML/Favorites.fxml")));
            Scene favoritesScene = new Scene(favoritesRoot);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(favoritesScene);
            currentStage.setTitle("Favorites");
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void switchToCategories (javafx.event.ActionEvent event) {
        try {
            Parent categoriesRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/Package/FXML/Categories.fxml")));
            Scene categoriesScene = new Scene(categoriesRoot);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(categoriesScene);
            currentStage.setTitle("Categories");
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void updateFavoriteButton() {
        if(currentContent == null) {
            addToFavorite.setDisable(true);
            return;

        }

        addToFavorite.setDisable(false);
        boolean fav = Content.isFavorited(currentContent.getId());
        addToFavorite.setText(fav ? "★" : "☆");

    }


    public void onFavorite() {
        if (currentContent == null) {
            System.out.println("Current content was empty");
            return;
        }

        boolean newState = !Content.isFavorited(currentContent.getId());
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() {
                Content.updateFavoriteStatus(currentContent.getId(), newState);
                return null;
            }
            @Override
            protected void succeeded() {
                updateFavoriteButton();
            }
            protected void failed() {

            }
        };
        new Thread(task).start();
    }
}



