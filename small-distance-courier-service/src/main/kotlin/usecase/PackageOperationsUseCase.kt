package usecase

import data.Coupons
import domain.delivery.Delivery
import domain.delivery.Package
import domain.offer.OfferCriteria

/**
 * The `PackageOperationsUseCase` class provides use case-specific operations related to packages and offers.
 *
 * This class encapsulates functionality such as validating package offers and calculating offer percentages
 * for packages within a delivery.
 *
 * @property offerCriteria An instance of the [OfferCriteria] class used for validating package offers.
 */
class PackageOperationsUseCase {
    private val offerCriteria = OfferCriteria(Coupons.offers)

    /**
     * Validates if a given package satisfies the criteria of a predefined discount offer.
     *
     * @param item The package to be validated.
     * @return `true` if the package satisfies the criteria, `false` otherwise.
     */
    fun validatePackageOffer(item: Package): Boolean {
        return offerCriteria.isValidPackageOffer(item)
    }

    /**
     * Calculates the offer percentage for each package within a delivery based on predefined discount offers.
     *
     * If a package satisfies the criteria of a discount offer, its offer percentage is set accordingly.
     * If not, the offer percentage is set to 0.0.
     *
     * @param delivery The delivery containing packages to calculate offer percentages for.
     * @return The modified delivery with updated offer percentages for each package.
     */
    fun getPackageOfferPercentage(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            if (validatePackageOffer(item)) {
                val validOffer = Coupons.offers.find { it.code == item.offerCode }
                item.offer = validOffer?.discountPercentage ?: 0.0
            } else {
                item.offer = 0.0
            }
        }
        return delivery
    }
}
