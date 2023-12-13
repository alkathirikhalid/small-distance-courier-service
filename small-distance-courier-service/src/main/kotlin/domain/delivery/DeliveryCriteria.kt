package domain.delivery

/**
 * Represents a criteria-based system for finding an optimal combination of packages for delivery.
 *
 * This class employs backtracking to explore various combinations of undelivered packages and identifies
 * the combination that maximizes the total weight while staying within the weight capacity of the delivery vehicle.
 * The exploration is based on sorting packages by weight (heavier first) and then by distance (shorter first).
 *
 * @property optimalCombination The list of packages representing the optimal combination for delivery.
 * @property maxWeightReached The total weight reached by the optimal combination.
 */
class DeliveryCriteria {
    private var optimalCombination: ArrayList<Package>? = null
    private var maxWeightReached: Double = 0.0

    /**
     * Finds the optimal combination of packages for delivery based on the specified maximum weight.
     *
     * @param maxWeight The maximum weight capacity of the delivery vehicle.
     * @param undeliveredPackages The list of undelivered packages to be considered for optimization.
     * @return The optimal combination of packages for delivery.
     */
    fun findOptimalPackageCombination(maxWeight: Double, undeliveredPackages: ArrayList<Package>): ArrayList<Package> {
        optimalCombination = null
        maxWeightReached = 0.0
        // Sort undelivered packages based on weight (heavier first) and then distance (shorter first)
        val sortedPackages =
            undeliveredPackages.sortedWith(compareByDescending<Package> { it.weight }.thenBy { it.distance })
        backtrack(ArrayList(), maxWeight, 0, ArrayList(sortedPackages))
        // Return the optimal combination found
        return optimalCombination ?: ArrayList()
    }

    private fun backtrack(
        currentCombination: ArrayList<Package>, maxWeight: Double, startIndex: Int,
        undeliveredPackages: ArrayList<Package>
    ) {
        // Check if the current combination is better than the previous
        if (currentCombination.sumOf { it.weight } > maxWeightReached) {
            optimalCombination = ArrayList(currentCombination)
            maxWeightReached = currentCombination.sumOf { it.weight }
        }
        // Explore all possible combinations
        for (i in startIndex..<undeliveredPackages.size) {
            val pkg = undeliveredPackages[i]

            if (pkg.weight + currentCombination.sumOf { it.weight } <= maxWeight) {
                // Include the package in the combination
                currentCombination.add(pkg)
                // Explore further
                backtrack(currentCombination, maxWeight, i + 1, undeliveredPackages)
                // Backtrack: Remove the package for the next iteration
                currentCombination.removeAt(currentCombination.size - 1)
            }
        }
    }
}
