package fr.bachachat.multiagents.graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Window.fxml"));
        stage.setTitle("Multi-Agents");
        stage.setScene(new Scene(root, 500, 375));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
