package greedy;

import java.util.Arrays;

/**
 * GFG - Fractional Knapsack
 *
 * Greedy approach:
 *  - Sort items by descending value-to-weight ratio
 *  - Take full items while capacity allows
 *  - Take fractional part of the next item if needed
 */
public class FractionalKnapsack {

    /**
     * Returns the maximum achievable value for the given knapsack capacity.
     *
     * @param values   value of each item
     * @param weights  weight of each item
     * @param capacity knapsack capacity
     * @return maximum total value
     * 
     * Time Complexity: O(n log n) for sorting items and O(n) for the greedy selection, overall O(n log n)
     * Space Complexity: O(n) for storing item objects
     * 
     */
    public double getMaximumValue(int[] values, int[] weights, int capacity) {

        int n = values.length;
        Item[] items = new Item[n];

        // Build item list
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        // Sort items by descending value/weight ratio
        Arrays.sort(items);

        double totalValue = 0.0;
        int itemIndex = 0;

        // Greedily fill the knapsack
        while (capacity > 0 && itemIndex < n) {

            Item current = items[itemIndex];

            // Take full item
            if (current.weight <= capacity) {
                totalValue += current.value;
                capacity -= current.weight;
            }
            // Take fractional part
            else {
                totalValue += (double) capacity * current.value / current.weight;
                break; // Knapsack is full
            }

            itemIndex++;
        }

        return totalValue;
    }

    /**
     * Represents an item with weight and value.
     * Items are ordered by descending value-to-weight ratio.
     */
    private static class Item implements Comparable<Item> {

        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item other) {
            // Compare value/weight without division (avoids precision issues)
            return Long.compare(
                (long) other.value * this.weight,
                (long) this.value * other.weight
            );
        }
    }
}
