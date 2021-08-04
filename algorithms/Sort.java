// sorting algorithm superclass
// sorting class must use getValue() in order for access count

package algorithms;

import gui.GraphDisplay;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import rnode.RNode;

import java.util.ArrayList;

public abstract class Sort {

    private final GraphDisplay graphDisplay;
    // rectangles as a bar graph
    protected final RNode[] bars;
    // list of movements of bars
    private final ArrayList<Transition> transitions = new ArrayList<>();
    // duration of transition, depends on bars.length
    private final Duration DURATION;

    protected Sort(GraphDisplay inGraphDisplay) {
        graphDisplay = inGraphDisplay;
        bars = graphDisplay.getBars();
        // TODO: make duration in graph display, so it can be on the menu
        double durationVar = 200;
        DURATION = Duration.millis(durationVar / bars.length);
    }

    abstract public void sort();

    // swaps indexes of bars
    // adds animations
    protected final void swapBars(int index1, int index2) {
        if (index1 != index2) {
            graphDisplay.addSwap();

            swapAnimation(index1, index2);
            swapAnimation(index2, index1);

            RNode r = bars[index1];
            bars[index1] = bars[index2];
            bars[index2] = r;
        }
    }

    // adds animation for one bar's movement
    private void swapAnimation(int fromIndex, int toIndex) {
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(DURATION);
        tt.setByX(graphDisplay.getX(toIndex) - graphDisplay.getX(fromIndex));
        tt.setNode(bars[fromIndex]);
        transitions.add(tt);
    }


    protected void insertBar(double value, int toIndex) {
        graphDisplay.addInsertion();

        bars[toIndex].value = value;

        PauseTransition pt = new PauseTransition(DURATION);
        pt.setOnFinished((event -> {
            bars[toIndex].setHeight(value);
            bars[toIndex].setY(graphDisplay.getY(value));
        }));

        transitions.add(pt);
    }

    // plays animations of graph changes
    // on finish, updates x values of bars
    protected void start() {
        SequentialTransition seqT = new SequentialTransition(
                transitions.toArray(new Transition[0]));
        seqT.setOnFinished(event -> {
            for (int i = 0; i < bars.length; ++i) {
                bars[i].setTranslateX(0);
                bars[i].setX(graphDisplay.getX(i));
            }
            graphDisplay.setBeingSorted(false);
            System.out.println("Sort.sorted(): " + sorted(bars));
            System.out.println();
        });
        graphDisplay.setBeingSorted(true);
        seqT.play();
    }

    protected double getValue(int index) {
        graphDisplay.addAccess();
        return bars[index].value;
    }

    // checks heights, x positions, and translateX values
    public static boolean sorted(final RNode[] rNodes) {
        boolean sorted = true;
        for (int i = 0; i < rNodes.length - 1; ++i) {
            if (rNodes[i].getHeight() > rNodes[i + 1].getHeight()
                || rNodes[i].getX() > rNodes[i].getX()
                || rNodes[i].getTranslateX() != 0) {
                sorted = false;
            }
        }
        if (rNodes[rNodes.length - 1].getTranslateX() != 0) {
            sorted = false;
        }
        return sorted;
    }



}
