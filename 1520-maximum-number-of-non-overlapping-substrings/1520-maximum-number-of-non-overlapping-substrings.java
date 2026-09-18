class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Step 1: Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int index = s.charAt(i) - 'a';

            if (first[index] == -1) {
                first[index] = i;
            }

            last[index] = i;
        }

        // Store valid intervals
        List<int[]> intervals = new ArrayList<>();

        // Step 2: Try every character as starting point
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int start = first[c];
            int end = last[c];

            boolean valid = true;

            // Step 3: Expand the interval
            for (int i = start; i <= end; i++) {

                int current = s.charAt(i) - 'a';

                // Character appeared before start
                if (first[current] < start) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences
                end = Math.max(end, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Step 4: Sort intervals by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        // Step 5: Greedy selection
        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > previousEnd) {

                answer.add(s.substring(start, end + 1));

                previousEnd = end;
            }
        }

        return answer;
    }
}