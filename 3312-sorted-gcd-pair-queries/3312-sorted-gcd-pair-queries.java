class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        int[] freq = new int[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        long[] gcdCount = new long[max + 1];

        // Count pairs whose GCD is exactly i
        for (int i = max; i >= 1; i--) {
            long cnt = 0;
            for (int j = i; j <= max; j += i) {
                cnt += freq[j];
            }

            gcdCount[i] = cnt * (cnt - 1) / 2;

            for (int j = i * 2; j <= max; j += i) {
                gcdCount[i] -= gcdCount[j];
            }
        }

        // Prefix sum of pair counts
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + gcdCount[i];
        }

        int[] ans = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {
            long target = queries[k] + 1; // 0-indexed query

            int l = 1;
            int r = max;

            while (l < r) {
                int mid = l + (r - l) / 2;

                if (prefix[mid] >= target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            ans[k] = l;
        }

        return ans;
    }
}