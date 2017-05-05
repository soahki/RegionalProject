package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import localities.AdministrativeZones;
import localities.Municipality;
import localities.Region;

import java.util.List;
import java.util.Map;

public class Home extends Application {
    private static Map<Region, List<Municipality>> regionMap = new AdministrativeZones().getRegionMap();
    private static final double BUTTON_WIDTH = 120;
    private static final double BUTTON_HEIGHT = 50;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Group root = new Group();

        setUpButtons(root);

        primaryStage.setTitle("TEST of interface");
        primaryStage.setScene(new Scene(root, BUTTON_WIDTH * 5, BUTTON_HEIGHT * 5));
        primaryStage.show();
    }

    
    private void setUpButtons(Group root) {
        GridPane grid = new GridPane();
        Button[] regionButtons = regionButtons();

        int row = 0;
        int column = 0;

        // Set up the buttons in a 5 column grid.
        for (int i = 0; i < regionButtons.length; i++) {
            grid.add(regionButtons[i], column, row);
            column++;
            if ((i + 1) % 5 == 0) {
                row++;
                column = 0;
            }
        }
        root.getChildren().add(grid);
    }

    private Button[] regionButtons() {
        Button[] buttons = new Button[regionMap.keySet().size()];
        int i = 0;
        for (Region region : regionMap.keySet()) {
            buttons[i] = new Button(region.getName());
            buttons[i].setPrefWidth(BUTTON_WIDTH);
            buttons[i].setPrefHeight(BUTTON_HEIGHT);
            buttons[i].setOnAction(event -> {
                System.out.println(region);
            });
            i++;
        }
        return buttons;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
