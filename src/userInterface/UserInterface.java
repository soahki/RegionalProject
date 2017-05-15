package userInterface;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        displayPrimaryScene(primaryStage);
    }

    private void displayPrimaryScene(Stage primaryStage) {
        Group root = new Group();

        setUpRegionButtons(root, primaryStage);

        primaryStage.setTitle("Regioner i Sverige");
        primaryStage.setScene(new Scene(root, BUTTON_WIDTH * 5, BUTTON_HEIGHT * 5));
        primaryStage.show();
    }

    private Scene getMunicipalityScene(Region region, Stage stage) {
        Group branch = new Group();
        GridPane grid = new GridPane();
        Button[] municipalityButtons = municipalityButtons(region);

        int row = 0;
        int column = 0;

        // Set up the buttons in a 5 column grid.
        for (int i = 0; i < municipalityButtons.length; i++) {
            grid.add(municipalityButtons[i], column, row);
            column++;
            if ((i + 1) % 5 == 0) {
                row++;
                column = 0;
            }
        }
        Button back = new Button("Return to regions");
        back.setOnAction(event -> displayPrimaryScene(stage));
        grid.add(back, 0, ++row);
        branch.getChildren().add(grid);
        return new Scene(branch);
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

    private void setUpRegionButtons(Group root, Stage stage) {
        GridPane grid = new GridPane();
        Button[] regionButtons = regionButtons(stage);

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

    private Button[] regionButtons(Stage stage) {
        Button[] buttons = new Button[regionMap.keySet().size()];
        int i = 0;
        for (Region region : regionMap.keySet()) {
            buttons[i] = new Button(region.getName());
            buttons[i].setPrefWidth(BUTTON_WIDTH);
            buttons[i].setPrefHeight(BUTTON_HEIGHT);
            buttons[i].setOnAction(event -> {
                stage.setTitle("Kommuner i " + region.getName());
                stage.setScene(getMunicipalityScene(region, stage));
                stage.show();
            });
            i++;
        }
        return buttons;
    }

    public static void launchUI(String[] args) {
        launch(args);
    }

}
