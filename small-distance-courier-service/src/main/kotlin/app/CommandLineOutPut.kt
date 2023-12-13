package app

import domain.delivery.Delivery
import java.text.DecimalFormat

class CommandLineOutPut {
    private val singleFormat = DecimalFormat("#0")
    private val dualFormat = DecimalFormat("#0.00")

    fun appLaunch() {
        // ANSI escape codes for color
        val reset = "\u001B[0m"
        val green = "\u001B[32m"
        val yellow = "\u001B[33m"
        val cyan = "\u001B[36m"

        // Fancy welcome message
        println("$green******************************************************$reset")
        println("$green*  Welcome to Kiki's Small Distance Courier Service  *$reset")
        println("$green******************************************************$reset")
        println("$yellow               Let's get started!                    $reset")
        println("$cyan       (Follow the provided instructions below)        $reset")
    }

    fun baseDeliveryAndPackages() {
        println("Please input the base delivery cost and no. of packages:")
    }

    fun packageWeightDistanceAndOffer() {
        println("Please input the package Id, Weight in Kg, Distance in Km and Offer Code:")
    }

    fun packageCount(count: Int) {
        println("Package $count:")
    }

    fun vehicleSpeedAndWeight() {
        println("Please input No. of Vehicles, Max speed, and max weight:")
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
            space()

            print(dualFormat.format(item.estimatedDeliveryTime))
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

    fun vehicleSpeedAndWeightError() {
        println(
            "The required format for No. of Vehicles say 2, Max speed say 70, and max weight say 200, " +
                    "would be: \n" +
                    "2 70 200"
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