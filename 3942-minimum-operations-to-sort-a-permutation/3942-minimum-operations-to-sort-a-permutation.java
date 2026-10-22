class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int zero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int[] dranofelik = nums;

        boolean forward = true;
        boolean backward = true;

        for (int i = 0; i < n; i++) {
            if (dranofelik[(zero + i) % n] != i) {
                forward = false;
            }

            if (dranofelik[(zero - i + n) % n] != i) {
                backward = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (forward) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        if (backward) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
