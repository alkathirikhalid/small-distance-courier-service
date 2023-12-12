package usecase

import data.Coupons
import domain.delivery.Delivery
import domain.delivery.Package
import domain.offer.OfferCriteria

class PackageOperationsUseCase {
    private val offerCriteria = OfferCriteria(Coupons.offers)
    fun validatePackageOffer(item: Package): Boolean {
        return offerCriteria.isValidPackageOffer(item)
    }

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
