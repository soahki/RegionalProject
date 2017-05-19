package userInterface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import localities.AdministrativeZones;
import localities.Municipality;
import localities.Region;

import java.util.List;
import java.util.Map;

public class UserInterface extends Application {
    private static Map<Region, List<Municipality>> regionMap = new AdministrativeZones().getRegionMap();
    private static final double BUTTON_WIDTH = 120;
    private static final double BUTTON_HEIGHT = 50;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        displayScene("Regioner i Sverige", getRegionScene());
    }

    private void displayScene(String title, Scene scene) {
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene getMunicipalityScene(Region region) {
        Group branch = new Group();
        HBox hBox = new HBox(5);

        Button[] municipalityButtons = municipalityButtons(region);
        GridPane buttonGrid = styleButtons(municipalityButtons);

        Button back = new Button("Return to regions");
        back.setStyle("-fx-base: #38C5FF");
        back.setOnAction(event -> displayScene("Län i Sverige", getRegionScene()));

        VBox vBox = new VBox(5);
        vBox.getChildren().add(buttonGrid);
        vBox.getChildren().add(back);

        hBox.getChildren().add(OverviewMap.getMap(region.getRegionCode()));
        hBox.getChildren().add(vBox);
        branch.getChildren().add(hBox);

        return new Scene(branch);
    }

    private Scene getRegionScene() {
        HBox hBox = new HBox(5);

        Button[] regionButtons = regionButtons();
        GridPane grid = styleButtons(regionButtons);

        hBox.getChildren().add(OverviewMap.getMap());
        hBox.getChildren().add(grid);
        return new Scene(hBox);
    }

    private Button[] regionButtons() {
        Button[] buttons = new Button[regionMap.keySet().size()];
        int i = 0;
        for (Region region : regionMap.keySet()) {
            buttons[i] = new Button(region.getName());
            buttons[i].setPrefWidth(BUTTON_WIDTH);
            buttons[i].setPrefHeight(BUTTON_HEIGHT);
            buttons[i].setOnAction(event ->
                    displayScene("Kommuner i " + region.getName(), getMunicipalityScene(region)));
            i++;
        }
        return buttons;
    }

    private Button[] municipalityButtons(Region region) {
        Button[] buttons = new Button[regionMap.get(region).size()];
        int i = 0;
        for (Municipality municipality : regionMap.get(region)) {
            buttons[i] = new Button(municipality.getName());
            buttons[i].setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            i++;
        }

        return buttons;
    }

    private GridPane styleButtons(Button[] buttons) {
        // Initialize GridPane instance
        GridPane gridPane = new GridPane();

        // Set basic style
        gridPane.setStyle("-fx-background-color: #ABABAB");
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Add buttons to a 5*n grid.
        int row = 0;
        int column = 0;
        for (int i = 0; i < buttons.length; i++) {
            gridPane.add(buttons[i], column, row);
            column++;
            if ((i + 1) % 5 == 0) {
                row++;
                column = 0;
            }
        }
        return gridPane;
    }

    public static void launchUI(String[] args) {
        launch(args);
    }

}