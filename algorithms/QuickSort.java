package algorithms;

import gui.GraphDisplay;

public class QuickSort extends Sort {

    public QuickSort(GraphDisplay graphDisplay) {
        super(graphDisplay);
    }

    @Override
    public void sort(){
        quickSort(0, bars.length -1);
        start();
    }

    // low and high are indexes
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);

            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    // places pivot in correct spot, returns pivot
    private int partition(int low, int high) {
        // sets pivot as high
        determinePivot(low, high);
        // value that is used to compare
        double pivotValue = getValue(high);

        // best pivot index found so far
        int pivotIndex = low;

        // sorts based on pivotValue to find pivotIndex
        for (int j = low; j < high; ++j) {
            if (getValue(j) < pivotValue) {
                swapBars(pivotIndex, j);
                ++pivotIndex;
            }
        }

        // sets pivot
        swapBars(pivotIndex, high);
        return pivotIndex;
    }

    // randomly selects three elements from low to high
    // sets median of three as pivot
    // swaps pivot with high
    private void determinePivot(int low, int high) {
        int length = high + 1 - low;
        if (length >= 3) {
            // gets three unique indexes
            int[] indexes = new int[3];
            indexes[0] = low + (int) (length * Math.random());
            do {
                indexes[1] = low + (int) (length * Math.random());
            } while (indexes[0] == indexes[1]);
            do {
                indexes[2] = low + (int) (length * Math.random());
            } while (indexes[2] == indexes[0] || indexes[2] == indexes[1]);

            // sorts index array
            for (int i = 0; i < 3; ++i) {
                int j = i;
                if (i == 2) j = 0;
                if (getValue(indexes[j]) > getValue(indexes[j + 1])) {
                    int temp = indexes[j];
                    indexes[j] = indexes[j + 1];
                    indexes[j + 1] = temp;
                }
            }

            // sets median as pivot at index high
            swapBars(indexes[1], high);
        }
    }

}
