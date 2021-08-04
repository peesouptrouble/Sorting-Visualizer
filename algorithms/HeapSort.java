package algorithms;

import gui.GraphDisplay;

public class HeapSort extends Sort {

    // TODO: UNDERSTAND FULLY
    public HeapSort(GraphDisplay graphDisplay) {
        super(graphDisplay);
    }

    @Override
    public void sort() {

        for (int i = bars.length / 2 - 1; i >= 0; --i) {
            heapify(bars.length, i);
        }

        for (int n = bars.length - 1; n > 0; --n) {
            swapBars(0, n);
            heapify(n, 0);
        }

        start();
    }

    // n is size of heap
    // i is parent of current heapify call
    private void heapify(int n, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && getValue(left) > getValue(max)) {
            max = left;
        }
        if (right < n && getValue(right) > getValue(max)) {
            max = right;
        }

        if (max != i) {
            swapBars(max, i);
            heapify(n, max);
        }
    }
}
