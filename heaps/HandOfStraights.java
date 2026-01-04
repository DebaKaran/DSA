package heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * LeetCode 846 - Hand of Straights
 *
 * Determines whether the hand can be rearranged into groups
 * of consecutive numbers of fixed size.
 */
class HandOfStraights {

    /**
     * Entry method.
     *
     * @param hand      array of card values
     * @param groupSize size of each consecutive group
     * @return true if hand can be rearranged into valid groups
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {

        // Quick sanity checks
        if (hand == null || hand.length == 0) {
            return true;
        }

        if (hand.length % groupSize != 0) {
            return false;
        }

        // return canFormConsecutiveGroupsUsingHeap(hand, groupSize);
         return canFormConsecutiveGroups(hand, groupSize);
    }

    /**
     * Greedy helper that forms consecutive groups using TreeMap.
     */
    private boolean canFormConsecutiveGroups(int[] cards, int groupSize) {

        // Step 1: Count frequency of each card
        TreeMap<Integer, Integer> frequencyMap = new TreeMap<>();
        for (int card : cards) {
            frequencyMap.put(card, frequencyMap.getOrDefault(card, 0) + 1);
        }

        // Step 2: Form groups greedily
        while (!frequencyMap.isEmpty()) {

            // Always start from the smallest available card
            int startCard = frequencyMap.firstKey();

            // Try to build one group of size = groupSize
            for (int currentCard = startCard;
                 currentCard < startCard + groupSize;
                 currentCard++) {

                if (!frequencyMap.containsKey(currentCard)) {
                    return false;
                }

                int count = frequencyMap.get(currentCard);

                // Decrease frequency or remove card
                if (count == 1) {
                    frequencyMap.remove(currentCard);
                } else {
                    frequencyMap.put(currentCard, count - 1);
                }
            }
        }

        return true;
    }

    /**
     * Uses a Min Heap to always start forming groups
     * from the smallest available card.
     *
     * Time Complexity: O(N log U)
     * Space Complexity: O(U)
     * where:
     *  N = total number of cards
     *  U = number of unique card values
     */
    private boolean canFormConsecutiveGroupsUsingHeap(int[] hand, int groupSize) {

        // Step 1: Count frequency of each card
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int card : hand) {
            frequencyMap.put(card, frequencyMap.getOrDefault(card, 0) + 1);
        }

        // Step 2: Min heap ordered by card value
        PriorityQueue<CardCount> minHeap =
                new PriorityQueue<>((a, b) -> a.card - b.card);

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(new CardCount(entry.getKey(), entry.getValue()));
        }

        // Step 3: Form groups
        while (!minHeap.isEmpty()) {

            int previousCard = -1;
            int cardsNeeded = groupSize;
            List<CardCount> usedInGroup = new ArrayList<>();

            // Try to build one group of size = groupSize
            while (cardsNeeded > 0 && !minHeap.isEmpty()) {

                CardCount current = minHeap.poll();

                // Cards must be consecutive
                if (previousCard != -1 && current.card != previousCard + 1) {
                    return false;
                }

                previousCard = current.card;

                // Decrease frequency
                current.count--;

                // Keep card if it still has remaining count
                if (current.count > 0) {
                    usedInGroup.add(current);
                }

                cardsNeeded--;
            }

            // If we could not form a full group
            if (cardsNeeded != 0) {
                return false;
            }

            // Push remaining cards back into heap
            for (CardCount cardCount : usedInGroup) {
                minHeap.offer(cardCount);
            }
        }

        return true;
    }

    /**
     * Helper class representing a card value and its frequency.
     */
    static class CardCount {
        int card;
        int count;

        CardCount(int card, int count) {
            this.card = card;
            this.count = count;
        }
    }
}
