package domain.delivery

/**
 * The `VehicleNextAvailableTime` class provides functionality to calculate the estimated return time
 * for a delivery vehicle based on the furthest delivery time for a package.
 *
 * @constructor Creates a new instance of the [VehicleNextAvailableTime] class.
 */

class VehicleNextAvailableTime {

    /**
     * Calculates the estimated return time for the delivery vehicle based on the furthest delivery
     * time for a package. The estimated return time is determined by doubling (x2) the furthest delivery
     * time, as it represents the round trip going time.
     *
     * @param furthestDeliveryTimePackage The furthest delivery time for a package, expressed as a Double.
     * @return The estimated return time for the delivery vehicle.
     */
    fun estimatedReturnTime(furthestDeliveryTimePackage: Double): Double {
        return furthestDeliveryTimePackage * 2
    }
}
