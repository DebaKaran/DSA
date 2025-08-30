import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//LeetCode 901: Online Stock Span
//https://leetcode.com/problems/online-stock-span/description/

//Time Complexity: O(n) for n calls to next()
//Space Complexity: O(n) for n calls to next()
/** class StockSpanner {
    List<Integer> list;
    public StockSpanner() {
        this.list = new ArrayList<>();
    }
    
    public int next(int price) {
        list.add(price);
        int total = 0;
        for(int i = list.size() - 1; i >= 0; i--) {
            if(list.get(i) > price) {
                return total;
            }
            total++;
        }
        return total;
    }
} */

public class StockSpanner {

     //Using stack as we need the past details
    //Time Complexity: Amortized O(1) per query, O(n) for n queries.
    // Space Complexity: O(n).
    Stack<int[]> stack;

     public StockSpanner() {
        this.stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;

        while(!stack.isEmpty() && stack.peek()[0] <= price) {
            int[] top = stack.pop();
            span += top[1];
        }

        
        stack.push(new int[]{price, span});

        return span;
    } 
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */