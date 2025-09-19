// 代码生成时间: 2025-09-19 09:13:28
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Service class to implement sorting algorithms.
 */
@Service
public class SortingAlgorithmService {

    /**
     * Sorts a list of integers using the built-in sort method from Collections framework.
     *
     * @param numbers The list of integers to be sorted.
     * @return A sorted list of integers.
     */
    public List<Integer> sortNumbers(List<Integer> numbers) {
        // Check for null input
        if (numbers == null) {
            throw new IllegalArgumentException("The list of numbers cannot be null.");
        }

        // Sorting the list in ascending order
        Collections.sort(numbers);
        return numbers;
    }

    /**
     * Sorts a list of integers using a simple Bubble Sort algorithm.
     *
     * @param numbers The list of integers to be sorted.
     * @return A sorted list of integers.
     */
    public List<Integer> bubbleSort(List<Integer> numbers) {
        // Check for null input
        if (numbers == null) {
            throw new IllegalArgumentException("The list of numbers cannot be null.");
        }

        int n = numbers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element found is greater than the next element
                if (numbers.get(j) > numbers.get(j + 1)) {
                    // Swapping the elements
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                }
            }
        }
        return numbers;
    }

    /**
     * Sorts a list of integers using the Selection Sort algorithm.
     *
     * @param numbers The list of integers to be sorted.
     * @return A sorted list of integers.
     */
    public List<Integer> selectionSort(List<Integer> numbers) {
        // Check for null input
        if (numbers == null) {
            throw new IllegalArgumentException("The list of numbers cannot be null.");
        }

        int n = numbers.size();
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in the unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (numbers.get(j) < numbers.get(min_idx)) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = numbers.get(min_idx);
            numbers.set(min_idx, numbers.get(i));
            numbers.set(i, temp);
        }
        return numbers;
    }
}
