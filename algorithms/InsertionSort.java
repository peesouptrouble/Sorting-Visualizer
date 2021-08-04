package algorithms;

import gui.GraphDisplay;

public class InsertionSort extends Sort {

    public InsertionSort(GraphDisplay graphDisplay) {
        super(graphDisplay);
    }

    @Override
    public void sort() {
        for (int i = 0; i < bars.length; ++i) {
            int j = i;
            while (j > 0 && getValue(j) < getValue(j - 1)) {
                swapBars(j, j - 1);
                --j;
            }
        }
        start();
    }

}
