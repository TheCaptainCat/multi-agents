package fr.bachachat.multiagents.graphics;

import fr.bachachat.multiagents.logic.Agent;
import fr.bachachat.multiagents.logic.Grid;
import fr.bachachat.multiagents.logic.Vector;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GridDisplay {

    private static final double ELEMENT_SIZE = 100;
    private static final double GAP = 0;

    private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);
    private int size;
    private Grid grid;

    public GridDisplay(int size, Grid grid) {
        tilePane.setStyle("-fx-background-color: rgba(255, 215, 0, 0.1);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        this.grid = grid;
        setSize(size);
    }

    public void setSize(int newSize) {
        size = newSize;
        tilePane.setPrefColumns(size);
        tilePane.setPrefRows(size);
    }

    public Group getDisplay() {
        return display;
    }

    public void createElements() {
        tilePane.getChildren().clear();
        int number = 1;
        Text text = null;
        Group group = null;
        Agent agent = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                group = new Group();
                agent = this.grid.getAgent(new Vector(j, i));
                if (agent == null)
                    group.getChildren().add(createElement());
                else {
                    group.getChildren().add(createAgent());
                    text = new Text(40, 55, String.valueOf(agent.getDestination()));
                    group.getChildren().add(text);
                }
                tilePane.getChildren().add(group);
                number++;
            }
        }
    }

    private Rectangle createElement() {
        Rectangle rectangle = new Rectangle(0, 0, ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        return rectangle;
    }

    private Rectangle createAgent() {
        Rectangle rectangle = new Rectangle(0, 0, ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.GREEN);
        return rectangle;
    }

}
