import java.sql.Time;

public class TowerOfHanoiSolver {

     public int towerOfHanoi(int n, int from, int to, int aux) {
        //return towerOfHanoiRecursion(n, from, to, aux);
        // Time Complexity: O(1)
        // Space Complexity: O(1)
         return (1 << n) - 1; // equivalent to 2^n - 1
    }
    // 1: Time Complexity:
    // O(2^n) - exponential time complexity
    // 2: Space Complexity:
    // O(n) - due to recursion stack

    private int towerOfHanoiRecursion(int n, int from, int to, int aux) {
        if (n == 0) {
            return 0;
        }
        
        // Move n-1 disks from 'from' → 'aux'
        int moves1 = towerOfHanoiRecursion(n - 1, from, aux, to);
        
        // Move the largest disk from 'from' → 'to' (1 move)
        int moves2 = 1;
        
        // Move n-1 disks from 'aux' → 'to'
        int moves3 = towerOfHanoiRecursion(n - 1, aux, to, from);
        
        return moves1 + moves2 + moves3;
    }
}
