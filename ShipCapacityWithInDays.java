
//TC-O(N) SC-O(1)
public class ShipCapacityWithInDays {
    public int shipWithinDays(int[] weights, int days) {
        // Edge case: if input is null or empty, no shipping needed
        if (weights == null || weights.length == 0) return 0;

        int low = 0;  // Minimum possible capacity: must at least hold the heaviest package
        int high = 0; // Maximum possible capacity: sum of all weights (i.e., shipping all in one day)

        // Calculate the lower and upper bounds for binary search
        for (int w : weights) {
            low = Math.max(low, w); // Ensure ship can handle the largest single package
            high += w;              // Sum of all weights is the upper bound
        }

        // Binary search to find the minimal ship capacity that meets the 'days' constraint
        while (low <= high) {
            int mid = low + (high - low) / 2; // Try a mid-point capacity

            int currDays = 1;  // Start with day 1
            int currSum = 0;   // Track current day's total weight

            // Simulate shipping process using current mid (capacity)
            for (int i = 0; i < weights.length; i++) {
                // If adding this package exceeds current capacity, start new day
                if (currSum + weights[i] > mid) {
                    currDays++;      // One more day needed
                    currSum = 0;     // Reset daily weight
                }

                // Early exit optimization: break if we've already exceeded the allowed days
                if (currDays > days) break;

                currSum += weights[i]; // Add current package to the day's shipment
            }

            // If more days are needed than allowed, increase capacity
            if (currDays > days) {
                low = mid + 1;
            } else {
                // Try smaller capacity to minimize it further
                high = mid - 1;
            }
        }

        // 'low' now holds the minimum ship capacity to deliver within 'days'
        return low;
    }
}
