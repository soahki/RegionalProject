package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import localities.Municipality;
import localities.Region;
import statistics.Quantifiable;
import statistics.Statistics;
import utilities.jdbc.Seeder;
import utilities.filter.Filter;
import ui.tools.MapGenerator;
import utilities.filter.NumberOfMunicipalitiesFilter;
import utilities.filter.StandardFilter;
import utilities.filter.TotalPopulationFilter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserInterface extends Application {
    private static Map<Region, List<Municipality>> regionMap;  // Sorted municipality according to its county/region
    private static final double BUTTON_WIDTH = 120;
    private static final double BUTTON_HEIGHT = 50;

    private String statisticsTitle;
    private Stage primaryStage;
    private Filter filter = new StandardFilter();   // Default filter, Filter being the way the map image is being rendered.

    /**
     * Method as part of UserInterface being subclass of Application.
     * @param primaryStage, the stage being part of the default scene.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        regionMap = Seeder.getMap();
        this.primaryStage = primaryStage;
        displayScene("Regioner i Sverige", getRegionScene());
    }

    /**
     * Method to call when scene is to be changed.
     * @param title String, to be displayed on frame
     * @param scene Scene, to be displayed in frame
     */
    private void displayScene(String title, Scene scene) {
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Method to produce a Scene with municipalities of a region/county.
     * @param region Region, the key to access the accompanying municipalities.
     * @return Scene to be displayed.
     */
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

        hBox.getChildren().add(MapGenerator.getMap(region.getRegionCode()));
        hBox.getChildren().add(vBox);
        branch.getChildren().add(hBox);

        return new Scene(branch);
    }

    /**
     * Method to produce a Scene with regions.
     * @return Scene to be displayed.
     */
    private Scene getRegionScene() {
        HBox hBoxOverallUI = new HBox(5);
        VBox vBoxLeftUI = new VBox(5);
        VBox vBoxCenterUI = new VBox(5);
        VBox vBoxRightUI = new VBox(5);

        Button[] regionButtons = regionButtons();
        GridPane grid = styleButtons(regionButtons);

        // Set left side UI
        try {
            vBoxLeftUI.getChildren().add(filter.getFilteredImages());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        vBoxLeftUI.setPadding(new Insets(5));

        // Set center UI
        Label lblStats = new Label("Statistics");
        Slider sldPresentTopStats = new Slider(0, 100, 10);
        vBoxCenterUI.getChildren().add(lblStats);
        vBoxCenterUI.getChildren().add(sldPresentTopStats);
        vBoxCenterUI.getChildren().add(statisticsSummaryBox((Quantifiable)filter));
        vBoxCenterUI.getChildren().add(filterComboBox());
        vBoxCenterUI.setPrefWidth(250);
        vBoxCenterUI.setPadding(new Insets(5));

        // Set right side UI
        vBoxRightUI.getChildren().add(grid);

        // Set overall UI to hBox.
        hBoxOverallUI.getChildren().add(vBoxLeftUI);
        hBoxOverallUI.getChildren().add(vBoxCenterUI);
        hBoxOverallUI.getChildren().add(vBoxRightUI);
        return new Scene(hBoxOverallUI);
    }

    // Here in lies all the filters for the map presentation
    private ComboBox filterComboBox() {
        ComboBox<Filter> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().add(new StandardFilter());
        filterComboBox.getItems().add(new NumberOfMunicipalitiesFilter(regionMap));
        filterComboBox.getItems().add(new TotalPopulationFilter(regionMap));
        filterComboBox.setOnAction( event -> {
            filter = filterComboBox.getSelectionModel().getSelectedItem();
            String title = filterComboBox.getSelectionModel().getSelectedItem().toString();
            displayScene(title, getRegionScene());
        });

        return filterComboBox;
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

    /**
     * Method to style buttons in a GridPane.
     * @param buttons Button[] will be ordered in a GridPane.
     * @return GridPane including Button[] according to style set.
     */
    private GridPane styleButtons(Button[] buttons) {
        // Initialize GridPane instance
        GridPane gridPane = new GridPane();

        // Set basic style
        // gridPane.setStyle("-fx-background-color: #ABABAB");
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

    // Add a statistics summary box
    private VBox statisticsSummaryBox(Quantifiable quantifiable) {
        Statistics stats = new Statistics(quantifiable.toString(), quantifiable.getStatistics());
        VBox vBox = new VBox();
        Label lblHeading = new Label("Statistics: " + stats.getName());

        String sb = "Minimum: " + String.format("%.2f%n", stats.getMin()) +
                "Maximum: " + String.format("%.2f%n", stats.getMax()) +
                "Median: " + String.format("%.2f%n", stats.getMedian()) +
                "Mean: " + String.format("%.2f%n", stats.getMean()) +
                "Sum: " + String.format("%.2f%n", stats.getSum()) +
                "StdDev: " + String.format("%.2f%n", stats.getStdDev());


        TextArea statsArea = new TextArea(sb);
        vBox.getChildren().add(lblHeading);
        vBox.getChildren().add(statsArea);
        return vBox;
    }

    public static void launchUI(String[] args) {
        launch(args);
    }

}