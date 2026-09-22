import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    private Deque<int[]> stack;
    public StockSpanner() {
        stack = new ArrayDeque<>();
    }
    
    public int next(int price) {
        int span = 1;

        while(!stack.isEmpty() && stack.peek()[0] <= price){
            int[] pairs = stack.pop();
            span += pairs[1];
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