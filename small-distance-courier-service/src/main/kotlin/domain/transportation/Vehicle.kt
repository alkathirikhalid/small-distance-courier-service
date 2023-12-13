package domain.transportation

import domain.delivery.Package

/**
 * Represents a vehicle used for transportation in the delivery system.
 *
 * @property maxSpeed The maximum speed of the vehicle.
 * @property maxWeight The maximum weight capacity of the vehicle.
 * @property packagesToDeliver The list of packages assigned to the vehicle for delivery.
 * @property availableTime The available time for the vehicle to complete its assigned deliveries. Default is 0.0.
 */
data class Vehicle(
    val maxSpeed: Double,
    val maxWeight: Double,
    var packagesToDeliver: ArrayList<Package> = ArrayList(),
    var availableTime: Double = 0.0
)
