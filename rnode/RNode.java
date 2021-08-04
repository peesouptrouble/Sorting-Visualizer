package rnode;

import javafx.scene.shape.Rectangle;

public class RNode extends Rectangle {
    public double value;
    public final int index;

    // used by GraphDisplay
    public RNode(int inIndex) {
        index = inIndex;
    }

}
