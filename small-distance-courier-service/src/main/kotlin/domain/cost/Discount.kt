package domain.cost

/**
 * The `Discount` class provides functionality to calculate the discount amount based on the package offer and cost.
 *
 * This class contains a single function, `calculateDiscount`, which takes the package offer percentage and package cost
 * as input and returns the calculated discount amount.
 *
 * @property calculateDiscount Calculates the discount amount based on the provided package offer and cost.
 */
class Discount {
    /**
     * Calculates the discount amount based on the package offer percentage and package cost.
     *
     * @param packageOffer The percentage of discount offered for the package.
     * @param packageCost The cost of the package before applying the discount.
     * @return The calculated discount amount.
     */
    fun calculateDiscount(packageOffer: Double, packageCost: Double): Double {
        return (packageOffer / 100) * packageCost
    }
}