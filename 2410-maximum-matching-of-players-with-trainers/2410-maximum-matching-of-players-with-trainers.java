class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int ans = 0;
        int i = 0; 
        int j = 0; 
        while (i < players.length && j < trainers.length) {
            if (players[i] <= trainers[j]) {
                ans++;
                i++;
            }
            j++;
        }
        return ans;
    }
}