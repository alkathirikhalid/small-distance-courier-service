package domain.data

import domain.offer.Offer

class Coupons {
    companion object {
        val offers: ArrayList<Offer> = arrayListOf(
            Offer("OFR001", 10.0, 0.0..200.0, 70.0..200.0),
            Offer("OFR002", 7.0, 50.0..150.0, 100.0..250.0),
            Offer("OFR003", 5.0, 50.0..250.0, 10.0..150.0)
        )
    }
}