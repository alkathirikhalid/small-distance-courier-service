package app

import domain.delivery.Delivery
import domain.util.InputSplitter

class CommandLineIO(private val application: Application, private val commandLineOutPut: CommandLineOutPut) {
    fun run() {
        try {
            commandLineOutPut.baseDeliveryAndPackages()
            getBaseDeliveryCostAndNoOfPackages(readln().trim())
        } catch (exception: Exception) {
            commandLineOutPut.genericError(exception)
        }
    }

    private fun getBaseDeliveryCostAndNoOfPackages(input: String) {
        if (!application.validateBaseDeliveryCostNoOfPackages(input)) {
            commandLineOutPut.baseDeliveryAndPackagesError()
            return
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

        fun getAndAddPackage(packageIdWeightDistanceAndOfferCode: String) {
            if (!application.validatePackageIdWeightDistanceOfferCode(packageIdWeightDistanceAndOfferCode)) {
                commandLineOutPut.packageWeightDistanceAndOfferError()
                return
            }
            val parts = InputSplitter.splitInput(packageIdWeightDistanceAndOfferCode, 4)
            val item = getPackage(parts)
            delivery.packages.add(item)
        }

        // Process the first package
        commandLineOutPut.packageWeightDistanceAndOffer()
        getAndAddPackage(readln().trim())

        // Process the remaining packages
        for (count in 2..noOfPackages.toInt()) {
            commandLineOutPut.packageCount(count)
            getAndAddPackage(readln().trim())
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
            InputSplitter.getPartAtIndex(parts, 3), 0.0, 0.0, 0.0, 0.0
        )
    }
}