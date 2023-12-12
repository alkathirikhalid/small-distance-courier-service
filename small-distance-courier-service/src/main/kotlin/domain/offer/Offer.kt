package domain.offer

data class Offer(
    val code: String,
    val discountPercentage: Double,
    val distanceRange: ClosedRange<Double>,
    val weightRange: ClosedRange<Double>
)