package domain.offer

import domain.delivery.Package

class OfferCriteria(private val offers: ArrayList<Offer>) {
    fun isValidPackageOffer(item: Package): Boolean {
        val validOffer = offers.find { it.code == item.offerCode }
        return validOffer != null && item.distance in validOffer.distanceRange && item.weight in validOffer.weightRange
    }
}