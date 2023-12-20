package domain.delivery

/**
 * The `Package` class represents a package with specific attributes for delivery and associated discount information.
 *
 * This data class encapsulates information about a package, including its unique identifier, weight, distance for delivery,
 * associated discount offer code, calculated discount percentage, delivery cost, offer value, total cost, and estimated delivery time.
 *
 * @property id The unique identifier of the package. (User Input)
 * @property weight The weight of the package in units specified by the application. (User Input, set Double for expansion)
 * @property distance The distance the package needs to travel for delivery. (User Input, set Double for expansion)
 * @property offerCode The code identifying the discount offer associated with the package. (User Input)
 * @property discount The calculated discount percentage based on the discount offer associated with the package. (Calculated)
 * @property deliveryCost The calculated cost of the package, taking into account the base cost, weight, and distance. (Calculated)
 * @property offer The calculated value of the discount offer applied to the package. (Calculated)
 * @property totalCost The total cost of the package, including the calculated discount. (Calculated)
 * @property estimatedDeliveryTime The estimated time for delivery of the package. (Calculated)
 */
data class Package(
    var id: String,
    val weight: Double,
    val distance: Double,
    val offerCode: String,
    var offer: Double,
    var deliveryCost: Double,
    var discount: Double,
    var totalCost: Double,
    var estimatedDeliveryTime: Double
)