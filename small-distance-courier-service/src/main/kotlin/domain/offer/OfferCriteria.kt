package domain.offer

import domain.delivery.Package

/**
 * The `OfferCriteria` class provides criteria for validating deliveries/packages based on predefined discount offers.
 * It checks if a given package satisfies the criteria of a predefined discount offer.
 *
 * @property offers The list of predefined [Offer] instances used for validation.
 */
class OfferCriteria(private val offers: ArrayList<Offer>) {
    /**
     * Validates if a given delivery/package satisfies the criteria of a predefined discount offer.
     *
     * @param item The package to be validated.
     * @return `true` if the package satisfies the criteria, `false` otherwise.
     */
    fun isValidPackageOffer(item: Package): Boolean {
        val validOffer = offers.find { it.code == item.offerCode }
        return validOffer != null && item.distance in validOffer.distanceRange && item.weight in validOffer.weightRange
    }
}