// controls positions of objects in displayGroup
// displays RNodes
// displays info

package gui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import rnode.RNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GraphDisplay {

    // index 0 has background rectangle
    // index 1 has info GridPane
    // indexes ...n holds bars
    private final Group displayGroup = new Group();

    private final int numElements;
    private final RNode[] bars;

    private boolean beingSorted = false;

    private double distanceBetweenBars;
    private double paddingWidth;
    private double graphBottom;

    private final Text accesses = new Text("0");
    private final Text swaps = new Text("0");
    private final Text insertions = new Text("0");

    public GraphDisplay(int inNumElements) {
        numElements = inNumElements;
        bars = new RNode[numElements];
        displayGroup.relocate(0, 10);
    }

    // formats graph
    // if randomHeights is true, height difference is random
    public Group displayGroup(boolean randomHeights) {
        // background
        Rectangle background = new Rectangle(Main.DISPLAY_WIDTH, Main.DISPLAY_HEIGHT);
        background.setFill(Color.WHITE);
        displayGroup.getChildren().add(background);

        // setting width helpers
        double graphWidth = 3 * Main.DISPLAY_WIDTH / 4;
        paddingWidth = Main.DISPLAY_WIDTH / 8;
        double barSpace = (graphWidth / 5) / (numElements - 1);
        double barWidth = (4 * graphWidth / 5) / numElements;
        distanceBetweenBars = barSpace + barWidth;

        // setting height helpers
        double graphHeight = 4 * Main.DISPLAY_HEIGHT / 5;
        double paddingHeight = Main.DISPLAY_HEIGHT / 10;
        graphBottom = graphHeight + paddingHeight;

        // assigns RNode values
        // displays bars
        Random random = new Random();
        for (int i = 0; i < numElements; ++i) {
            bars[i] = new RNode(i);
            bars[i].setFill(Color.BLACK);
            bars[i].setWidth(barWidth);

            double height;
            if (randomHeights) {
                height = random.nextInt((int) graphHeight) + 1;
            } else {
                double increment = graphHeight / numElements;
                height = graphHeight - i * increment;
            }
            bars[i].setHeight(height);
            bars[i].value = height;

            double xPos = paddingWidth + i * distanceBetweenBars;
            bars[i].setX(xPos);
            double yPos = paddingHeight + graphHeight - height;
            bars[i].setY(yPos);
            displayGroup.getChildren().add(bars[i]);
        }
        if (!randomHeights) {
            shuffle();
        }

        createInformationDisplay();
        return displayGroup;
    }

    // shuffles bars via Collections.shuffle()
    // shuffles if sorted or canShuffle
    public void shuffle() {
        if (!beingSorted) {
            ArrayList<RNode> barList = new ArrayList<>();
            Collections.addAll(barList, bars);
            Collections.shuffle(barList);
            for (int i = 0; i < bars.length; ++i) {
                bars[i] = barList.get(i);
                bars[i].setX(getX(i));
            }
        }
    }

    private void createInformationDisplay() {
        HBox info = new HBox();
        info.relocate(paddingWidth, Main.DISPLAY_HEIGHT - 30);
        info.setAlignment(Pos.CENTER);
        info.setSpacing(20);
        info.getChildren().add(new Text("Accesses:"));
        info.getChildren().add(accesses);
        info.getChildren().add(new Text("   Swaps:"));
        info.getChildren().add(swaps);
        info.getChildren().add(new Text("   Insertions:"));
        info.getChildren().add(insertions);

        displayGroup.getChildren().add(info);
    }

    public void addAccess() {
        int num = Integer.parseInt(accesses.getText());
        accesses.setText(String.valueOf(++num));
    }

    public void addSwap() {
        int num = Integer.parseInt(swaps.getText());
        swaps.setText(String.valueOf(++num));
    }

    public void addInsertion() {
        int num = Integer.parseInt(insertions.getText());
        insertions.setText(String.valueOf(++num));
    }

    // returns x position of index
    // used in swaps
    public double getX(int index) { return paddingWidth + index * distanceBetweenBars; }

    // returns y position of height
    // used in insertions
    public double getY(double height) { return graphBottom - height; }

    public RNode[] getBars() { return bars; }

    public void setBeingSorted(boolean b) { beingSorted = b; }

}
