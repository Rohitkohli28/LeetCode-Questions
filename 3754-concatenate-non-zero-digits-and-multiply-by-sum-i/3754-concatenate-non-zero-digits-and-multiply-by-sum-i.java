class Solution {
    public long sumAndMultiply(int n) {
        int sum = 0;
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            int digit = ch - '0';
            sum += digit;

            if (digit != 0) {
                sb.append(digit);
            }
        }

        if (sb.length() == 0) {
            return 0;
        }

        long num = Long.parseLong(sb.toString());
        return num * sum;
    }
}