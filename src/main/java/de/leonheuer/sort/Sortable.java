package de.leonheuer.sort;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Sortable {

    private final int[] list;
    private final int length;

    @Contract(pure = true)
    public Sortable(int @NotNull [] list) {
        this.list = list;
        length = list.length;
    }

    public void sort(@NotNull Algorithm algorithm) {
        switch (algorithm) {
            case QUICK -> quickSort(0, length - 1);
            case RADIX -> radixSort();
            case MERGE -> System.arraycopy(mergeSort(list), 0, list, 0, length);
            case HEAP -> heapSort();
        }
    }

    /**
     * Sorts the integer array by using the quick sort algorithm
     */
    private void quickSort(int left, int right) {
        if (left < right) {
            int pivot = split(left, right);
            quickSort(left, pivot - 1);
            quickSort(pivot + 1, right);
        }
    }

    private int split(int left, int right) {
        int pivot = list[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (list[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, right);
        return i + 1;
    }

    /**
     * Sorts the integer array by using the radix sort algorithm
     */
    private void radixSort() {
        int max = 0;
        for (int j : list) {
            if (j > max) max = j;
        }

        for (int place = 1; max / place > 0; place *= 10) countingSort(place);
    }

    private void countingSort(int place) {
        int[] digitCount = new int[10];
        for (int i = 0; i < 10; i++) digitCount[i] = 0;

        for (int j : list) {
            int digit = (j / place) % 10;
            digitCount[digit]++;
        }

        for (int i = 1; i < 10; i++) digitCount[i] += digitCount[i-1];

        int[] output = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            int digit = (list[i] / place) % 10;
            int index = digitCount[digit] - 1;
            output[index] = list[i];
            digitCount[digit]--;
        }
        System.arraycopy(output, 0, list, 0, length);
    }

    /**
     * Sorts the integer array by using the heap sort algorithm
     */
    private void heapSort() {
        for (int i = (length / 2 - 1); i >= 0; i--) {
            heapify(length, i);
        }

        for (int i = length - 1; i >= 0; i--) {
            swap(0, i);
            heapify(i, 0);
        }
    }

    private void heapify(int length, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < length && list[left] > list[largest]) largest = left;
        if (right < length && list[right] > list[largest]) largest = right;

        if (i != largest) {
            swap(i, largest);
            heapify(length, largest);
        }
    }

    /**
     * Sorts the integer array by using the merge sort algorithm
     */
    private int @NotNull [] mergeSort(int @NotNull [] array) {
        if (array.length <= 1) {
            return array;
        }

        int half = array.length / 2;

        int[] left = new int[half];
        System.arraycopy(array, 0, left, 0, half);

        int[] right = new int[array.length - half];
        System.arraycopy(array, half, right, 0, array.length - half);

        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    @Contract(pure = true)
    private int @NotNull [] merge(int @NotNull [] left, int @NotNull [] right) {
        int[] newList = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int index = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                newList[index] = left[i];
                i++;
            } else {
                newList[index] = right[j];
                j++;
            }
            index++;
        }

        while (i < left.length) {
            newList[index] = left[i];
            i++;
            index++;
        }
        while (j < right.length) {
            newList[index] = right[j];
            j++;
            index++;
        }
        return newList;
    }

    /**
     * Swaps the values of the two given indexes.
     * @param i First index
     * @param j Second index
     */
    private void swap(int i, int j) {
        int memory = list[i];
        list[i] = list[j];
        list[j] = memory;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return Arrays.toString(list);
    }
}
