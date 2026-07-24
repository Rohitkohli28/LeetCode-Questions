class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] one = new boolean[MAX];
        boolean[] two = new boolean[MAX];
        boolean[] three = new boolean[MAX];

        for (int num : nums) {

            // Save previous states
            boolean[] oldOne = one.clone();
            boolean[] oldTwo = two.clone();

            // One element
            one[num] = true;

            // Two equal elements: num ^ num = 0
            two[0] = true;

            // Pair: previous single + current
            for (int x = 0; x < MAX; x++) {
                if (oldOne[x]) {
                    two[x ^ num] = true;
                }
            }

            // Three equal elements: num ^ num ^ num = num
            three[num] = true;

            // Triplet: previous pair + current
            for (int x = 0; x < MAX; x++) {
                if (oldTwo[x]) {
                    three[x ^ num] = true;
                }
            }
        }

        int ans = 0;

        for (boolean b : three) {
            if (b) {
                ans++;
            }
        }

        return ans;
    }
}