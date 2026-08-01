class Solution {
    public int lengthOfLongestSubstring(String s) {

        // Brute force
        // int max = 0;

        // for(int i=0; i<s.length(); i++){
        //     Set<Character> set = new HashSet<>();
        //     for(int j= i; j < s.length(); j++){
        //         char c = s.charAt(j);
        //         if(set.contains(c)){
        //             break;
        //         }
        //         set.add(c);
        //         max = Math.max(max, j - i + 1);
        //     }
        // }
        // return max;

        // Sliding window
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;

        for(int right = 0; right < s.length(); right++){
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while(map.get(ch) > 1){
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}