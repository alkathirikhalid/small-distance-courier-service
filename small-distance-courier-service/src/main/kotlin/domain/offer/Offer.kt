package domain.offer

/**
 * Represents an offer with specific criteria for discounts based on distance and weight ranges.
 *
 * This data class encapsulates information about a discount offer, including a unique code, the percentage of discount,
 * and the qualifying ranges for both distance and weight.
 *
 * @property code The unique code identifying the offer.
 * @property discountPercentage The percentage of discount associated with the offer.
 * @property distanceRange The range of distances that qualify for the offer.
 * @property weightRange The range of weights that qualify for the offer.
 */
data class Offer(
    val code: String,
    val discountPercentage: Double,
    val distanceRange: ClosedRange<Double>,
    val weightRange: ClosedRange<Double>
)