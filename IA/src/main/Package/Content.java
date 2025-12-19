package main.Package;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Class purpose: Gets information from the database such as
 * titles, dates, descriptions etc. and represents that database
 * content
 */

public class Content {
    private final String title;
    private final String description;
    private final LocalDate date;
    private final int id;
    private final String image;

    public Content (String description, String title, LocalDate date, int id, String image) {
        this.description = description;
        this.title = title;
        this.date = date;
        this.id = id;
        this.image = image;
    }

    // getter methods for accessing different content elements
    public LocalDate getDate() {
        return date;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public String getImage() {
        return image;
    }

    public static boolean isFavorited (int id) {
        String sql = "SELECT favorited FROM content WHERE id = ?";

        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("favorited") == 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void updateFavoriteStatus(int id, boolean favorite) {
        String sql = "UPDATE content SET favorited = ? WHERE id = ?";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, favorite ? 1 : 0);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Content> showFavorites() {
        List<Content> Favorites = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE favorited = 1 ORDER BY date DESC";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                Favorites.add(new Content(description, title, date, id, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Favorites;
    }

    public static List<Content> showCategory1Items() {
        List<Content> Category = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE category = 1 ORDER BY date DESC";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                Category.add(new Content(description, title, date, id, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Category;
    }

    public static List<Content> showCategory2Items() {
        List<Content> Category = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE category = 2 ORDER BY date DESC";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                Category.add(new Content(description, title, date, id, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Category;
    }

    public static List<Content> showCategory3Items() {
        List<Content> Category = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE category = 3 ORDER BY date DESC";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                Category.add(new Content(description, title, date, id, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Category;
    }

    public static List<Content> showCategory4Items() {
        List<Content> Category = new ArrayList<>();
        String sql = "SELECT * FROM content WHERE category = 4 ORDER BY date DESC";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int id = resultSet.getInt("id");
                String image = resultSet.getString("image");
                Category.add(new Content(description, title, date, id, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Category;
    }

    public static Optional<Content> getContentByDate(LocalDate date) {
        String sql = "SELECT * FROM content WHERE date = ?";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, date.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                return Optional.of(new Content(description, title, date, id, image));
            } else {
                System.out.println("Content not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public static List<Content> getAllContents() {
        List<Content> list = new ArrayList<>();
        String sql = "SELECT * FROM content ORDER BY date DESC";
        try (Connection con = DBConnect.connect();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDate date = LocalDate.parse(resultSet.getString("date"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                list.add(new Content(description, title, date, id, image));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // Displaying the content titles
    @Override
    public String toString() {
        return date.toString() + " - " + title;
    }
}
