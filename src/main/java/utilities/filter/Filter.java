package utilities.filter;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.sql.SQLException;

public interface Filter {
    StackPane getFilteredImages() throws SQLException;
}