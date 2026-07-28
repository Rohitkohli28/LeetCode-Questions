class Solution {
    int count = 0;

    public int countSubstrings(String s) {

        // Apprach 1 Brute Force
        //     int n = s.length();
        //     int count = 0;

        //     for (int i = 0; i < n; i++) {
        //         for (int j = i; j < n; j++) {
        //             if (isPalindrome(s, i, j)) {
        //                 count++;
        //             }
        //         }
        //     }
        //     return count;

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

        // Appraoch 2 : Optimise 

        int n = s.length();

        for (int i = 0; i < n; i++) {
            expand(s, i, i);
            expand(s, i, i + 1);
        }
        return count;
    }

    private void expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;

            left--;
            right++;
        }
    }
}