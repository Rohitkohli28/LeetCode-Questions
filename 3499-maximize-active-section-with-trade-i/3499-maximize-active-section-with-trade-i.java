class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        ArrayList<Integer> zeroGroups = new ArrayList<>();

        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '1') {
                ones++;
                i++;
            } else {
                int len = 0;
                while (i < s.length() && s.charAt(i) == '0') {
                    len++;
                    i++;
                }
                zeroGroups.add(len);
            }
        }

        int best = 0;
        for (i = 0; i + 1 < zeroGroups.size(); i++) {
            best = Math.max(best, zeroGroups.get(i) + zeroGroups.get(i + 1));
        }

        return ones + best;
    }
}