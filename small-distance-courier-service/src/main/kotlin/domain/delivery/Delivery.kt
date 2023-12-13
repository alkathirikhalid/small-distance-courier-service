package domain.delivery

import domain.transportation.Vehicle

/**
 * The `Delivery` class represents a delivery or shipment containing a base cost and a list of packages.
 *
 * This data class encapsulates information about a delivery, including its base cost, a list of packages to be delivered,
 * and a list of vehicles available for transportation.
 *
 * @property baseCost The base cost of the delivery. Default value is 0.0.
 * @property packages The list of packages included in the delivery. Default is an empty ArrayList.
 */
data class Delivery(
    var baseCost: Double = 0.0,
    var packages: ArrayList<Package> = ArrayList(),
    var vehicles: ArrayList<Vehicle> = ArrayList()
)
