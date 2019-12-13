import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Your implementation of various sorting algorithms.
 *
 * @author Luis Pastrana
 * @userid lepl3
 * @GTID 903244251
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Can't sort null arrays or"
                    + "null comparator");
        }

        int i = 0;
        boolean swapped = true;
        while (i < arr.length - 1 && swapped) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                    swapped = true;
                }
            }
            i++;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Can't sort null arrays or"
                    + "null comparator");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T swap = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = swap;
                j--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Can't sort null arrays or"
                    + "null comparator");
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (comparator.compare(arr[minIndex], arr[j]) > 0) {
                    minIndex = j;
                }
            }
            T swap = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = swap;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Can't sort null arrays or"
                    + "have a null comparator or have a null random gen");
        }
        quickSortHelper(arr, 0, arr.length, rand, comparator);

    }

    /**
     * Helper method for quickSort
     * @param arr array to be sorted
     * @param left left bound
     * @param right rigth bound
     * @param rand random gen
     * @param comp comparator
     * @param <T> Type of the variable stored in arr
     */
    private static <T> void quickSortHelper(T[] arr, int left,
                                int right, Random rand, Comparator<T> comp) {
        if (right > left) {
            int pivotIndex = rand.nextInt(right - left) + left;
            T pivot = arr[pivotIndex];
            T swap = arr[pivotIndex];
            arr[pivotIndex] = arr[left];
            arr[left] = swap;
            int leftIndex = left + 1;
            int rightIndex = right - 1;
            while (leftIndex <= rightIndex) {
                while (leftIndex <= rightIndex
                        && comp.compare(arr[leftIndex], pivot) <= 0) {
                    ++leftIndex;
                }
                while (leftIndex <= rightIndex
                        && comp.compare(arr[rightIndex], pivot) >= 0) {
                    --rightIndex;
                }
                if (leftIndex <= rightIndex) {
                    swap = arr[leftIndex];
                    arr[leftIndex] = arr[rightIndex];
                    arr[rightIndex] = swap;
                    --rightIndex;
                    ++leftIndex;
                }
            }
            swap = arr[left];
            arr[left] = arr[rightIndex];
            arr[rightIndex] = swap;
            quickSortHelper(arr, left, rightIndex, rand, comp);
            quickSortHelper(arr, rightIndex + 1, right, rand, comp);
        } else {
            return;
        }
    }


    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Can't sort null arrays or"
                    + "null comparator");
        }
        if (arr.length <= 1) {
            return;
        } else {
            int mid = arr.length / 2;
            T[] left = (T[]) new Object[mid];
            T[] right = (T[]) new Object[arr.length - mid];

            for (int i = 0; i < arr.length; i++) {
                if (i < mid) {
                    left[i] = arr[i];
                } else {
                    right[i - mid] = arr[i];
                }
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            int leftIndex = 0;
            int rightIndex = 0;
            int currIndex = 0;

            while (leftIndex < mid && rightIndex < arr.length - mid) {
                if (comparator,
                        .compare(left[leftIndex], right[rightIndex]) <= 0) {
                    arr[currIndex] = left[leftIndex];
                    leftIndex++;
                } else {
                    arr[currIndex] = right[rightIndex];
                    rightIndex++;
                }
                currIndex++;
            }

            while (leftIndex < mid) {
                arr[currIndex] = left[leftIndex];
                leftIndex++;
                currIndex++;
            }

            while (rightIndex < arr.length - mid) {
                arr[currIndex] = right[rightIndex];
                rightIndex++;
                currIndex++;
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw  new IllegalArgumentException("Can't sort null arrays");
        }
        Queue[] digits =  new LinkedList[19];
        if (arr.length == 0) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int length;
        for (length = 0; max >= 1; length++) {
            max = max / 10;
        }
        int ten = 10;
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int bucket = (arr[j] % ten) / (ten / 10);
                if (digits[bucket + 9] == null) {
                    digits[bucket + 9] = (Queue<Integer>) new LinkedList();
                }
                digits[bucket + 9].add(arr[j]);
            }
            int index = 0;
            for (Queue digit : digits) {
                while (digit != null && !digit.isEmpty()) {
                    arr[index] = (int) digit.remove();
                    index++;
                }
            }
            ten = ten * 10;
        }
    }
}
