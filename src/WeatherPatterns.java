import java.util.ArrayList;
import java.util.List;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Eisha Yadav
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     *
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    // GRAPH THEORY SOLUTION:
    public static int longestWarmingTrend(int[] temperatures) {

        // Build adjacency list
        // Array of ArrayLists
        int n = temperatures.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // Add Edges to the Graph
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    graph.get(i).add(j);
                }
            }
        }
        // Find longest path using memoization
        int[] memo = new int[n];
        int maxPath = 0;

        // Recurse to calculate longest path
        for (int i = 0; i < n; i++) {
            maxPath = Math.max(maxPath, calcMaxPath(graph, i, memo));
        }

        return maxPath;
    }

    // Helper Function
    private static int calcMaxPath(List<List<Integer>> graph, int node, int[] memo) {
        // Base Case
        if (memo[node] != 0) {
            return memo[node];
        }
        // Set length equal to one
        int longestPath = 1;
        // Recursively Continue
        for (int neighbor : graph.get(node)) {
            // Uses old max path-> that's the dynamic programming approach
            longestPath = Math.max(longestPath, 1 + calcMaxPath(graph, neighbor, memo));
        }
        // Save LIS value to be used to calculate further value
        // This saving is what allows us to calculate less as we go on
        memo[node] = longestPath;
        return longestPath;
    }
}

    // NON GRAPH-THEORY SOLUTION (still V squared time though..):
//    public static int longestWarmingTrend(int[] temperatures) {
//            int n = temperatures.length;
//            // Initialize Array of Temperatures Length
//            int lis[] = new int[n];
//
//            // Initialize longest length to 1
//            // At default each node is connected to itself
//            for (int i = 0; i < n; i++)
//                lis[i] = 1;
//
//            // Compute Run Length Sequence Values from the bottom up
//            for (int i = 1; i < n; i++)
//                for (int prev = 0; prev < i; prev++)
//                    if (temperatures[i] > temperatures[prev] && lis[i] < lis[prev] + 1)
//                        lis[i] = lis[prev] + 1;
//
//            // Pick the longest run length
//            int max = 1;
//            for (int i = 0; i < n; i++)
//                max = Math.max(max, lis[i]);
//
//            return max;
//        }
//    }
    // REMENANTS OF OLD 2^N solution
//    private static int helper(int[] temperatures, int prevIndex, int currentIndex) {
//        if (currentIndex == temperatures.length) return 0;
//        int skip = helper(temperatures, prevIndex, currentIndex + 1);
//        int take = (prevIndex == -1 || temperatures[currentIndex] > temperatures[prevIndex]) ?
//                1 + helper(temperatures, currentIndex, currentIndex + 1) : 0;
//        return Math.max(skip, take);
//    }

