package stack_queue;

import java.util.Stack;

/**
 * LeetCode 735 — Asteroid Collision
 *
 * Simulate collisions using a stack:
 * - Positive values move right, negative move left.
 * - A collision can only occur when stack.top > 0 and incoming < 0.
 * - Resolve collisions by comparing absolute sizes; the larger survives.
 */
public final class AsteroidCollision {

    private AsteroidCollision() { /* utility class */ }

    /** Public API */
    public int[] asteroidCollision(int[] asteroids) {
        //return asteroidCollisionUsingstack(asteroids);
        return collide(asteroids);
    }

    /**
     * Ultra-fast simulation using primitive int[] stack.
     * Time:  O(n) amortized
     * Space: O(n)
     */
    private int[] collide(int[] asteroids) {
        int n = asteroids.length;

        // manual stack using primitive array
        int[] stack = new int[n];
        int top = -1;  // empty stack

        for (int curr : asteroids) {
            boolean destroyed = false;

            // Only collide if stack.top > 0 and curr < 0
            while (top >= 0 && stack[top] > 0 && curr < 0) {
                int left = stack[top];
                int absCurr = -curr;  // curr < 0 → abs = -curr

                if (left < absCurr) {
                    // left asteroid destroyed
                    top--;
                    continue;
                } else if (left > absCurr) {
                    // curr destroyed
                    destroyed = true;
                    break;
                } else {
                    // both destroyed
                    top--;
                    destroyed = true;
                    break;
                }
            }

            // If curr survived collisions → push it
            if (!destroyed) {
                stack[++top] = curr;
            }
        }

    /**
     * Simulate asteroid collisions using a stack.
     *
     * Time:  O(n) amortized (each asteroid pushed/popped at most once)
     * Space: O(n) worst-case for the stack
     */
    private int[] simulateCollisions(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) return new int[0];

        Stack<Integer> stack = new Stack<>();

        for (int a : asteroids) {
            boolean destroyed = false;

            // Only possible collision: stack top moving right (>0) and incoming moving left (<0)
            while (!stack.isEmpty() && stack.peek() > 0 && a < 0) {
                int top = stack.peek();
                int absA = Math.abs(a);

                if (top < absA) {
                    // top explodes; continue checking with new top
                    stack.pop();
                    continue;
                } else if (top > absA) {
                    // incoming asteroid explodes; stop processing this incoming asteroid
                    destroyed = true;
                    break;
                } else {
                    // equal size: both explode
                    stack.pop();
                    destroyed = true;
                    break;
                }
            }

            // If incoming asteroid survived all collisions, push it onto the stack
            if (!destroyed) stack.push(a);
        }

        // Build result array from stack (bottom -> top)
        int m = stack.size();
        int[] result = new int[m];
        for (int i = m - 1; i >= 0; i--) result[i] = stack.pop();
        return result;
    }
}
