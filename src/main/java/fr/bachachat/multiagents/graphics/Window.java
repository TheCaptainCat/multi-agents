package fr.bachachat.multiagents.graphics;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Grid;
import fr.bachachat.multiagents.logic.Vector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Window extends Application {
    private GridDisplay gridDisplay;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Grid grid = new Grid(5);
        grid.addAgent(new Agent(new Vector(0, 0), new Vector(4, 4), grid));
        grid.addAgent(new Agent(new Vector(1, 0), new Vector(0, 4), grid));
        grid.addAgent(new Agent(new Vector(2, 2), new Vector(0, 0), grid));
        grid.addAgent(new Agent(new Vector(4, 3), new Vector(1, 2), grid));
        Parent root = FXMLLoader.load(getClass().getResource("/Window.fxml"));
        primaryStage.setTitle("Multi-Agents");
        gridDisplay = new GridDisplay(5, grid);


        BorderPane mainPanel = new BorderPane();
        mainPanel.setCenter(gridDisplay.getDisplay());
        Scene scene = new Scene(mainPanel, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
