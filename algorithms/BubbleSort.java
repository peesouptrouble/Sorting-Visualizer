package algorithms;

import gui.GraphDisplay;

public class BubbleSort extends Sort {

    public BubbleSort(GraphDisplay graphDisplay) {
        super(graphDisplay);
    }

    @Override
    public void sort() {
        for (int i = 1; i < bars.length ; ++i) {
            boolean wasSwap = false;
            for (int j = 0; j < bars.length - i; ++j) {
                if (getValue(j + 1) < getValue(j)) {
                    swapBars(j, j + 1);
                    wasSwap = true;
                }
            }
            if (!wasSwap) {
                break;
            }
        }

        start();
    }
}
