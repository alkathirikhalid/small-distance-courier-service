package usecase

import domain.data.Coupons
import domain.delivery.Package
import domain.offer.OfferCriteria

class PackageOperationsUseCase {
    private val offerCriteria = OfferCriteria(Coupons.offers)
    fun validatePackageOffer(item: Package): Boolean {
        return offerCriteria.isValidPackageOffer(item)
    }
}
