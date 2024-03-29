package usecase

import data.UndeliveredPackages
import domain.delivery.Delivery
import domain.delivery.DeliveryCriteria
import domain.delivery.DeliveryTime
import domain.delivery.VehicleNextAvailableTime

/**
 * The `DeliveryTimeUseCase` class provides use case-specific functionality related to the calculation of delivery times
 * and package distribution.
 *
 * This class contains methods to estimate the delivery time for individual packages, optimize package distribution among vehicles,
 * and calculate the next available time for each delivery vehicle based on the estimated delivery times.
 *
 */
class DeliveryTimeUseCase {
    private val deliveryTime = DeliveryTime()
    private val deliveryCriteria = DeliveryCriteria()
    private val vehicleNextAvailableTime = VehicleNextAvailableTime()

    /**
     * Calculates the estimated delivery time for each package in the given delivery.
     *
     * @param delivery The delivery object containing information about packages and vehicles.
     * @return The updated delivery object with estimated delivery times for each package.
     */
    fun calculateEstimatedDeliveryTime(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.estimatedDeliveryTime = deliveryTime.estimateDeliveryTime(
                item.distance,
                delivery.vehicles[0].maxSpeed
            )
        }
        return delivery
    }

    /**
     * Optimizes package distribution among delivery vehicles based on weight constraints.
     *
     * @param delivery The delivery object containing information about packages and vehicles.
     * @return The updated delivery object with optimized package distribution among vehicles.
     */
    fun calculatePackageDeliveryPerVehicle(delivery: Delivery): Delivery {
        val currentUndeliveredPackages = delivery.packages.toMutableList()

        for (vehicle in delivery.vehicles) {
            // Create a copy of the current undelivered packages for each vehicle
            val undelivered = ArrayList(currentUndeliveredPackages)
            vehicle.packagesToDeliver = deliveryCriteria.findOptimalPackageCombination(vehicle.maxWeight, undelivered)
            // Remove the loaded packages from the original list to be used in the next vehicle
            currentUndeliveredPackages.removeAll(vehicle.packagesToDeliver.toSet())
        }
        UndeliveredPackages.undeliveredPackages.clear()
        UndeliveredPackages.undeliveredPackages.addAll(currentUndeliveredPackages)

        return delivery
    }

    /**
     * Calculates the next available time for each delivery vehicle based on the estimated delivery times.
     *
     * @param delivery The delivery object containing information about packages and vehicles.
     * @return The updated delivery object with the next available time for each vehicle.
     */
    fun calculateVehicleNextAvailableTime(delivery: Delivery): Delivery {
        for (vehicle in delivery.vehicles) {
            val maxDeliveryTime =
                vehicle.packagesToDeliver.maxByOrNull { it.estimatedDeliveryTime }?.estimatedDeliveryTime
                    ?: 0.0
            vehicle.availableTime = vehicleNextAvailableTime.estimatedReturnTime(maxDeliveryTime)
        }
        return delivery
    }
}
