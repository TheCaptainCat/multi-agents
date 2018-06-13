package fr.bachachat.multiagents.graphics;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Grid;
import fr.bachachat.multiagents.logic.Vector;
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
        grid.addAgent(new Agent(new Vector(0, 0), new Vector(4, 0), grid));
        grid.addAgent(new Agent(new Vector(4, 0), new Vector(4, 4), grid));
        grid.addAgent(new Agent(new Vector(4, 4), new Vector(0, 4), grid));
        grid.addAgent(new Agent(new Vector(0, 4), new Vector(0, 0), grid));
        grid.addAgent(new Agent(new Vector(0, 1), new Vector(4, 3), grid));
        grid.addAgent(new Agent(new Vector(2, 3), new Vector(3, 0), grid));
        grid.addAgent(new Agent(new Vector(1, 4), new Vector(4, 2), grid));
        // Parent root = FXMLLoader.load(getClass().getResource("/Window.fxml"));
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
                    if (grid.isCompleted())
                        System.out.println("DONE");
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
