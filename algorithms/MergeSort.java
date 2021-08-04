package algorithms;

import gui.GraphDisplay;

public class MergeSort extends Sort {

    public MergeSort(GraphDisplay graphDisplay) {
        super(graphDisplay);
    }

    @Override
    public void sort() {
        mergeSort(new double[bars.length], 0, bars.length - 1);
        start();
    }

    // sorts sub arrays of bars
    // temp is used for subarrays
    // left and right are indexes
    private void mergeSort(double[] temp, int left, int right) {
        // if there is more than one element
        if (left < right) {
            int leftEnd = (right + left) / 2;

            mergeSort(temp, left, leftEnd);
            mergeSort(temp, leftEnd + 1, right);

            merge(temp, left, leftEnd, right);
        }
    }

    // temp holds subarrays of values
    // indexes in subarrays are the same as in bars
    private void merge(double[] temp, int left, int leftEnd, int rightEnd) {

        // assigning values to subarrays in temp
        for (int i = left; i <= leftEnd; ++i) {
            temp[i] = getValue(i);
        }
        for (int i = leftEnd + 1; i <= rightEnd; ++i) {
            temp[i] = getValue(i);
        }

        // iterators of subarrays
        int l = left;
        int r = leftEnd + 1;
        // iterator of bars
        int b = left;

        // merge
        while (l <= leftEnd && r <= rightEnd) {
            if (temp[l] < temp[r]) {
                // from l index in bars
                insertBar(temp[l], b);
                ++l;
            } else {
                // from r index in bars
                insertBar(temp[r], b);
                ++r;
            }
            ++b;
        }

        // if there are leftovers in subarrays: insert
        while (l <= leftEnd) {
            insertBar(temp[l], b);
            ++l;
            ++b;
        }
        while (r <= rightEnd) {
            insertBar(temp[r], b);
            ++r;
            ++b;
        }
    }

}
