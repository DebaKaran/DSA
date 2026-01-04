package heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 621 - Task Scheduler
 *
 * Uses a Max Heap to simulate task execution with cooldown.
 */
class TaskScheduler {

    /**
     * Returns the minimum number of intervals required
     * to execute all tasks with a cooldown constraint.
     *
     * @param tasks array of task identifiers
     * @param cooldown cooldown interval between same tasks
     * @return minimum time units required
     */
    public int leastInterval(char[] tasks, int cooldown) {
        return scheduleTasksUsingMaxHeap(tasks, cooldown);
    }

    /**
     * Schedules tasks using a max heap simulation.
     *
     * Time Complexity: O(N log C)
     *  - N = number of tasks
     *  - C = number of unique task types (≤ 26)
     *
     * Space Complexity: O(C)
     *  - Max heap + frequency map
     */
    private int scheduleTasksUsingMaxHeap(char[] tasks, int cooldown) {

        // Count frequency of each task
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for (char task : tasks) {
            taskFrequencyMap.put(task, taskFrequencyMap.getOrDefault(task, 0) + 1);
        }

        // Max Heap ordered by remaining frequency
        PriorityQueue<Task> maxHeap =
                new PriorityQueue<>((a, b) -> b.remainingCount - a.remainingCount);

        for (Map.Entry<Character, Integer> entry : taskFrequencyMap.entrySet()) {
            maxHeap.offer(new Task(entry.getKey(), entry.getValue()));
        }

        int totalTime = 0;

        // Process tasks in cycles of (cooldown + 1)
        while (!maxHeap.isEmpty()) {

            List<Task> executedInCycle = new ArrayList<>();
            int slots = cooldown + 1;

            // Execute up to (cooldown + 1) tasks
            while (slots > 0 && !maxHeap.isEmpty()) {
                Task current = maxHeap.poll();
                current.remainingCount--;

                executedInCycle.add(current);
                totalTime++;
                slots--;
            }

            // Reinsert tasks that still have remaining executions
            for (Task task : executedInCycle) {
                if (task.remainingCount > 0) {
                    maxHeap.offer(task);
                }
            }

            // Add idle time only if tasks remain
            if (!maxHeap.isEmpty() && slots > 0) {
                totalTime += slots;
            }
        }

        return totalTime;
    }

    /**
     * Helper class representing a task and its remaining executions.
     */
    static class Task {
        char id;
        int remainingCount;

        Task(char id, int remainingCount) {
            this.id = id;
            this.remainingCount = remainingCount;
        }
    }
}
