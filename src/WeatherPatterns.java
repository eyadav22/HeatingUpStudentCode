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
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        return helper(temperatures, -1, 0);
    }

    private static int helper(int[] temperatures, int prevIndex, int currentIndex) {
        if (currentIndex == temperatures.length) return 0;
        int skip = helper(temperatures, prevIndex, currentIndex + 1);
        int take = (prevIndex == -1 || temperatures[currentIndex] > temperatures[prevIndex]) ?
                1 + helper(temperatures, currentIndex, currentIndex + 1) : 0;
        return Math.max(skip, take);
    }
}
