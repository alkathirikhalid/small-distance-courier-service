package data

import data.UndeliveredPackages.Companion.undeliveredPackages
import domain.delivery.Package

/**
 * The `UndeliveredPackages` class represents a collection of packages that have not been delivered.
 *
 * This class serves as a data container to keep track of undelivered packages during the delivery process.
 * The list of undelivered packages is stored as a static property in the `UndeliveredPackages` companion object.
 * Packages can be added or removed from this collection as the delivery progresses.
 *
 * @property undeliveredPackages The list of undelivered packages stored in the companion object.
 */
class UndeliveredPackages {
    companion object {
        /**
         * The list of undelivered packages.
         */
        val undeliveredPackages: ArrayList<Package> = ArrayList()
    }
}
