class Solution {
    public int longestPalindrome(String s) {
        int[] freq = new int[128];

        for (char ch : s.toCharArray()) {
            freq[ch]++;
        }

        int length = 0;

        for (int count : freq) {

            length += (count / 2) * 2;

            if (length % 2 == 0 && count % 2 == 1) {
                length++;
            }
        }

        return length;
    }
}