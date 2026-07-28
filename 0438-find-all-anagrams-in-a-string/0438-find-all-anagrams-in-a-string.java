class Solution {

    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<>();

        if (s.length() < p.length())
            return ans;

        int[] pFreq = new int[26];
        int[] window = new int[26];

        for (char ch : p.toCharArray())
            pFreq[ch - 'a']++;

        int left = 0;

        for (int right = 0; right < s.length(); right++) {

            window[s.charAt(right) - 'a']++;

            if (right - left + 1 > p.length()) {
                window[s.charAt(left) - 'a']--;
                left++;
            }

            if (Arrays.equals(window, pFreq))
                ans.add(left);
        }

        return ans;
    }
}