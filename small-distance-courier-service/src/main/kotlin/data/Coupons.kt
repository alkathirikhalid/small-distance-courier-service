package data

import data.Coupons.Companion.offers
import domain.offer.Offer

/**
 * Singleton class representing a collection of predefined offers with specific criteria for discounts.
 *
 * The `Coupons` class serves as a data repository, providing access to a list of pre-defined [Offer] instances.
 * This list includes offers with unique codes, associated discount percentages, distance ranges, and weight ranges.
 * The class follows the Singleton pattern, ensuring a single instance of the offers list throughout the application.
 *
 * @property offers The list of predefined offers, each with a unique code, discount percentage, distance range, and weight range.
 */
class Coupons {
    companion object {
        /**
         * The list of predefined offers, each with a unique code, discount percentage, distance range, and weight range.
         */
        val offers: ArrayList<Offer> = arrayListOf(
            Offer("OFR001", 10.0, 0.0..200.0, 70.0..200.0),
            Offer("OFR002", 7.0, 50.0..150.0, 100.0..250.0),
            Offer("OFR003", 5.0, 50.0..250.0, 10.0..150.0)
        )
    }
}