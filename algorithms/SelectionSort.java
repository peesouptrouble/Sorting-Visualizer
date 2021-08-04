package algorithms;

import gui.GraphDisplay;

public class SelectionSort extends Sort{

    public SelectionSort(GraphDisplay graphDisplay) {
        super(graphDisplay);
    }

    @Override
    public void sort() {
        for (int i = 0; i < bars.length - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < bars.length; ++j) {
                if (getValue(j) < getValue(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swapBars(minIndex, i);
            }
        }

        start();
    }

}
