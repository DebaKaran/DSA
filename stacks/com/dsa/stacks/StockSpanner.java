import java.util.ArrayList;
import java.util.List;

//LeetCode 901: Online Stock Span
//https://leetcode.com/problems/online-stock-span/description/

//Time Complexity: O(n) for n calls to next()
//Space Complexity: O(n) for n calls to next()
class StockSpanner {
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
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */