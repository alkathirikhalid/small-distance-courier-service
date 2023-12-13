package usecase

import data.UndeliveredPackages
import domain.delivery.Delivery
import domain.delivery.DeliveryCriteria

/**
 * The `RemainderPackageDeliveryTimeUseCase` class provides use case-specific functionality to calculate
 * the estimated delivery time for the remaining undelivered packages.
 *
 * This class iterates through the available delivery vehicles based on their earliest availability,
 * finds the optimal package combination for each vehicle, and updates the estimated delivery time for the packages
 * to be delivered by that vehicle. The undelivered packages are then updated accordingly.
 *
 * @property calculateEstimatedRemainderPackagesDeliveryTime Calculates the estimated delivery time for remaining undelivered packages.
 */
class RemainderPackageDeliveryTimeUseCase {
    /**
     * Calculates the estimated delivery time for the remaining undelivered packages.
     *
     * @param delivery The delivery object containing information about packages and vehicles.
     */
    fun calculateEstimatedRemainderPackagesDeliveryTime(delivery: Delivery) {
        val deliveryCriteria = DeliveryCriteria()
        // Iterate through all vehicles based on their earliest availability
        for (vehicle in delivery.vehicles.sortedBy { it.availableTime }) {
            // Find the optimal package combination for the current vehicle
            vehicle.packagesToDeliver.clear()
            vehicle.packagesToDeliver =
                deliveryCriteria.findOptimalPackageCombination(
                    vehicle.maxWeight,
                    UndeliveredPackages.undeliveredPackages
                )
            // Update the estimated delivery time for the packages to be delivered by this vehicle
            for (packageToDeliver in vehicle.packagesToDeliver) {
                val matchingPackage = delivery.packages.find { it.id == packageToDeliver.id }
                // Update Estimated Delivery Time
                matchingPackage?.estimatedDeliveryTime =
                    matchingPackage?.estimatedDeliveryTime?.plus(vehicle.availableTime) ?: 0.0
            }
            // Remove the loaded packages from the original list to be used in the next vehicle
            UndeliveredPackages.undeliveredPackages.removeAll(vehicle.packagesToDeliver.toSet())
        }
    }
}
