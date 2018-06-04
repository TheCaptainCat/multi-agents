package fr.bachachat.multiagents.graphics;

import fr.bachachat.multiagents.logic.Grid;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GridDisplay {

    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = ELEMENT_SIZE / 10;

    private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);
    private int size;
    private Grid grid;

    public GridDisplay(int size, Grid grid) {
        tilePane.setStyle("-fx-background-color: rgba(255, 215, 0, 0.1);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        setSize(size);
        this.grid = grid;
    }

    public void setSize(int newSize) {
        size = newSize;
        tilePane.setPrefColumns(size);
        tilePane.setPrefRows(size);
        createElements();
    }

    public Group getDisplay() {
        return display;
    }

    private void createElements() {
        tilePane.getChildren().clear();
        int number = 1;
        Text text = null;
        Group group = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                group = new Group();
                text = new Text(45, 55, String.valueOf(number));
                group.getChildren().add(createElement());
                group.getChildren().add(text);
                tilePane.getChildren().add(group);
                number++;
            }
        }
    }

    private Rectangle createElement() {
        Rectangle rectangle = new Rectangle(0, 0, ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.ORANGE);
        rectangle.setFill(Color.STEELBLUE);
        return rectangle;
    }

}
