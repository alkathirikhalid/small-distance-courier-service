package app

import domain.delivery.Delivery
import java.text.DecimalFormat

class CommandLineOutPut {
    private val singleFormat = DecimalFormat("#0")

    fun baseDeliveryAndPackages() {
        println("Please input the base delivery cost and no. of packages:")
    }

    fun packageWeightDistanceAndOffer() {
        println("Please input the package Id, Weight in Kg, Distance in Km and Offer Code:")
    }

    fun packageCount(count: Int) {
        println("Package $count:")
    }

    private fun space() {
        print(" ")
    }

    private fun line() {
        println("")
    }

    fun printResults(delivery: Delivery) {
        for (item in delivery.packages) {
            print(item.id)
            space()

            print(singleFormat.format(item.discount))
            space()

            print(singleFormat.format(item.totalCost))
            line()
        }
    }

    fun baseDeliveryAndPackagesError() {
        println(
            "The required format for base delivery cost of say 100 and no. of packages say 3, would be: \n" +
                    "100 3"
        )
    }

    fun packageWeightDistanceAndOfferError() {
        println(
            "The required format for Package ID say PKG1, Package Weight in Kg say 5, Distance in Km say 5 " +
                    "and offer Code say OFR001, would be: \n" +
                    "PKG1 5 5 OFR001"
        )
    }

    fun packageExistsError() {
        println(
            "Package Id Exists, packages require to be unique."
        )
    }

    fun genericError(exception: Exception) {
        println("An error occurred: ${exception.message}")
    }
}