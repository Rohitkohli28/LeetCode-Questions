class Solution {
    public String longestPalindrome(String s) {

        // Brute force approach
        //     int n = s.length();
        //     String result = "";

        //     for (int i = 0; i < n; i++) {
        //         for (int j = i; j < n; j++) {
        //             if (isPalindrome(s, i, j)) {
        //                 if ((j - i + 1) > result.length()) {
        //                     result = s.substring(i, j + 1);
        //                 }
        //             }
        //         }
        //     }
        //     return result;
        // }

        // private boolean isPalindrome(String s, int left, int right) {
        //     while (left < right) {
        //         if (s.charAt(left) != s.charAt(right)) {
        //             return false;
        //         }
        //         left++;
        //         right--;
        //     }
        //     return true;

        // Optimise approach

        int n = s.length();
        int start = 0;
        int end = 0;

        for (int i = 0; i < n; i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}