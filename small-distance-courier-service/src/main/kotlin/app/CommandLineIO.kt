package app

import domain.delivery.Delivery
import domain.transportation.Vehicle
import domain.util.InputSplitter
import kotlin.system.exitProcess

class CommandLineIO(private val application: Application, private val commandLineOutPut: CommandLineOutPut) {
    fun run() {
        try {
            commandLineOutPut.appLaunch()
            commandLineOutPut.baseDeliveryAndPackages()
            getBaseDeliveryCostAndNoOfPackages(readln().trim())
        } catch (exception: Exception) {
            commandLineOutPut.genericError(exception)
            exitProcess(1)  // Exit the application with an error code
        }
    }

    private fun getBaseDeliveryCostAndNoOfPackages(input: String) {
        if (!application.validateBaseDeliveryCostNoOfPackages(input)) {
            commandLineOutPut.baseDeliveryAndPackagesError()
            exitProcess(1)  // Exit the application with an error code
        }
        val parts = InputSplitter.splitInput(input, 2)
        val baseDeliveryCost = InputSplitter.getPartAtIndex(parts, 0)
        val noOfPackages = InputSplitter.getPartAtIndex(parts, 1)

        getPackageIdWeightDistanceAndOfferCode(baseDeliveryCost, noOfPackages)
    }

    // Usage
    private fun getPackageIdWeightDistanceAndOfferCode(baseDeliver: String, noOfPackages: String) {
        processPackageInput(baseDeliver, noOfPackages)
    }

    private fun processPackageInput(baseDeliver: String, noOfPackages: String) {
        val delivery = Delivery()
        delivery.baseCost = baseDeliver.toDouble()

        fun getAndAddPackage(packageIdWeightDistanceAndOfferCode: String): Boolean {
            if (!application.validatePackageIdWeightDistanceOfferCode(packageIdWeightDistanceAndOfferCode)) {
                commandLineOutPut.packageWeightDistanceAndOfferError()
                return false
            }
            val parts = InputSplitter.splitInput(packageIdWeightDistanceAndOfferCode, 4)
            val item = getPackage(parts)
            val existingPackage = delivery.packages.find { it.id == item.id }
            if (existingPackage != null) {
                commandLineOutPut.packageExistsError()
                return false
            }
            delivery.packages.add(item)
            return true
        }

        // Process the first package
        commandLineOutPut.packageWeightDistanceAndOffer()
        if (!getAndAddPackage(readln().trim())) {
            exitProcess(1)  // Exit the application with an error code
        }

        // Process the remaining packages
        for (count in 2..noOfPackages.toInt()) {
            commandLineOutPut.packageCount(count)
            if (!getAndAddPackage(readln().trim())) {
                exitProcess(1)  // Exit the application with an error code
            }
        }
        commandLineOutPut.vehicleSpeedAndWeight()
        getNumberOfVehiclesSpeedAndWeight(readln().trim(), delivery)
    }

    private fun getNumberOfVehiclesSpeedAndWeight(input: String, delivery: Delivery) {
        if (!application.validateNoOfVehiclesMaxSpeedMaxWeight(input)) {
            commandLineOutPut.vehicleSpeedAndWeightError()
            exitProcess(1)  // Exit the application with an error code
        }
        val parts3 = InputSplitter.splitInput(input, 3)
        for (count in 1..InputSplitter.getPartAtIndex(parts3, 0).toInt()) {
            val vehicle = Vehicle(
                InputSplitter.getPartAtIndex(parts3, 1).toDouble(),
                InputSplitter.getPartAtIndex(parts3, 2).toDouble()
            )
            delivery.vehicles.add(vehicle)
        }

        // Start Calculations
        application.performCalculations(delivery)
        // Print results
        commandLineOutPut.printResults(delivery)
    }

    private fun getPackage(parts: List<String>): domain.delivery.Package {
        return domain.delivery.Package(
            InputSplitter.getPartAtIndex(parts, 0),
            InputSplitter.getPartAtIndex(parts, 1).toDouble(),
            InputSplitter.getPartAtIndex(parts, 2).toDouble(),
            InputSplitter.getPartAtIndex(parts, 3), 0.0, 0.0, 0.0, 0.0, 0.0
        )
    }
}
