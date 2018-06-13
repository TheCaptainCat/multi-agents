package fr.bachachat.multiagents.graphics;

import fr.bachachat.multiagents.logic.Grid;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Window extends Application {
    private GridDisplay gridDisplay;

    @Override
    public void start(Stage primaryStage) {
        Grid grid = new Grid(5);
        grid.initializeRandomAgents(10);
        primaryStage.setTitle("Multi-Agents");
        gridDisplay = new GridDisplay(5, grid);
        grid.launchAgents();
        BorderPane mainPanel = new BorderPane();
        mainPanel.setCenter(gridDisplay.getDisplay());
        Scene scene = new Scene(mainPanel, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> close());
        draw(primaryStage, scene);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                    Platform.runLater(() -> draw(primaryStage, scene));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void close() {
        System.exit(0);
    }

    private void draw(Stage primaryStage, Scene scene) {
        gridDisplay.createElements();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
