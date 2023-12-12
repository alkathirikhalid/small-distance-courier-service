package domain.delivery

data class Package(
    val id: String,
    val weight: Double,
    val distance: Double,
    val offerCode: String,
    var offer: Double,
    var deliveryCost: Double
)