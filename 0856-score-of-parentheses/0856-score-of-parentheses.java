class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int val = stack.pop();

                if (val == 0) {
                    val = 1; // ()
                } else {
                    val = 2 * val; // (A)
                }

                stack.push(stack.pop() + val);
            }
        }

        return stack.pop();
    }
}