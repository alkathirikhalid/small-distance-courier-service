package domain.cost

/**
 * The `TotalCost` class provides functionality to calculate the total cost of a package after applying the discount.
 *
 * This class contains a single function, `calculateTotalCost`, which takes the package cost and discount amount as input
 * and returns the calculated total cost.
 *
 * @property calculateTotalCost Calculates the total cost of a package after applying the discount.
 */
class TotalCost {
    /**
     * Calculates the total cost of a package after applying the discount.
     *
     * @param packageCost The cost of the package before applying the discount.
     * @param packageDiscount The amount of discount applied to the package.
     * @return The calculated total cost of the package.
     */
    fun calculateTotalCost(packageCost: Double, packageDiscount: Double): Double {
        return packageCost - packageDiscount
    }
}