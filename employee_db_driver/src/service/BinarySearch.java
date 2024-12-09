package service;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        int target = 34;

        bubbleSort(array);
        int result = binarySearch(array, target);

        if (result == -1) {
            System.out.println("Element not found in the array.");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            // Check if the target is present at mid
            if (array[middle] == target) {
                return middle;
            }

            // If target greater, ignore left half
            if (array[middle] < target) {
                left = middle + 1;
            }
            // If target is smaller, ignore right half
            else {
                right = middle - 1;
            }
        }

        // Target not found
        return -1;
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) { // TODO: n - 1 !!!!!!!!!!
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1]; //Swap array[ j]and array[ j + 1]
                    array[j + 1] = temp;
                }
            }
        }
    }
}
