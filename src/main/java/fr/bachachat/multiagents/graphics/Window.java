package fr.bachachat.multiagents.graphics;

import fr.bachachat.multiagents.logic.Grid;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Window extends Application {
    private GridDisplay gridDisplay;
    private Grid grid;

    @Override
    public void start(Stage primaryStage) {
        this.grid = new Grid(5);
        this.grid.initializeRandomAgents(10);
        primaryStage.setTitle("Multi-Agents");
        this.gridDisplay = new GridDisplay(grid.getSize(), grid);
        this.grid.launchAgents();
        BorderPane mainPanel = new BorderPane();
        mainPanel.setCenter(this.gridDisplay.getDisplay());
        Scene scene = new Scene(mainPanel, grid.getSize() * 120, grid.getSize() * 120);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> close());
        draw(primaryStage);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                    Platform.runLater(() -> draw(primaryStage));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void close() {
        System.exit(0);
    }

    private void draw(Stage primaryStage) {
        this.gridDisplay.createElements();
        if (this.grid.isCompleted())
            primaryStage.setTitle("Multi-Agents - Finished");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
