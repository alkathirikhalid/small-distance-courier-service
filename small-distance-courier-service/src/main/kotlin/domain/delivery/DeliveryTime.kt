package domain.delivery

import domain.util.TruncateDecimal

/**
 * Represents a class for estimating the delivery time for a package based on distance and vehicle speed.
 *
 * This class provides a method to calculate the estimated delivery time by dividing the package distance
 * by the maximum speed of the delivery vehicle. The result is truncated to provide a realistic estimate.
 *
 * @property truncateDecimal An instance of the [TruncateDecimal] class used for decimal truncation.
 */
class DeliveryTime {
    private val truncateDecimal = TruncateDecimal()

    /**
     * Estimates the delivery time for a package based on its distance and the maximum speed of the delivery vehicle.
     *
     * @param packageDistance The distance the package needs to travel for delivery.
     * @param vehicleMaxSpeed The maximum speed of the delivery vehicle.
     * @return The estimated delivery time in hours.
     */
    fun estimateDeliveryTime(packageDistance: Double, vehicleMaxSpeed: Double): Double {
        return truncateDecimal.truncate(packageDistance / vehicleMaxSpeed)
    }
}
