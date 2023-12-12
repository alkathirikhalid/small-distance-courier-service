package domain.delivery

/**
 * The `DeliveryCost` class provides functionality to calculate the total cost of a delivery based on specified parameters.
 *
 * This class contains a single function, `calculateDeliveryCost`, which takes the base cost, package total weight,
 * and distance to the destination as input and returns the calculated total cost of the delivery.
 *
 * @property calculateDeliveryCost Calculates the total cost of a delivery based on the provided parameters.
 */
class DeliveryCost {
    /**
     * Calculates the total cost of a delivery based on the specified parameters.
     *
     * @param baseCost The base cost of the delivery.
     * @param packageTotalWeight The total weight of packages included in the delivery.
     * @param distanceToDestination The distance to the destination for the delivery.
     * @return The calculated total cost of the delivery.
     */
    fun calculateDeliveryCost(baseCost: Double, packageTotalWeight: Double, distanceToDestination: Double): Double {
        return baseCost + (packageTotalWeight * 10) + (distanceToDestination * 5)
    }
}