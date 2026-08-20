class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;

        int[] a = new int[n];
        int[] b = new int[n];

        int sizeA = 1, sizeB = 1;
        a[0] = nums[0];
        b[0] = nums[1];

        for (int i = 2; i < n; i++) {
            int lastA = a[sizeA - 1];
            int lastB = b[sizeB - 1];

            if (lastA > lastB) {
                a[sizeA++] = nums[i];
            } else {
                b[sizeB++] = nums[i];
            }
        }

        int[] ans = new int[n];

        for (int i = 0; i < sizeA; i++) {
            ans[i] = a[i];
        }

        for (int i = 0; i < sizeB; i++) {
            ans[sizeA + i] = b[i];
        }

        return ans;
    }
}