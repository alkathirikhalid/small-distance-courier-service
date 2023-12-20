package app

import domain.delivery.Delivery
import domain.delivery.Package
import domain.transportation.Vehicle
import domain.util.InputSplitter
import kotlin.system.exitProcess

/**
 * The `CommandLineIO` class manages the command-line interface for the courier service application.
 * It handles user interactions, input validations, and the initiation of package delivery calculations.
 *
 * @property application An instance of the Application class responsible for coordinating application logic.
 * @property commandLineOutPut An instance of the CommandLineOutPut class for displaying messages to the user.
 */
class CommandLineIO(private val application: Application, private val commandLineOutPut: CommandLineOutPut) {
    /**
     * Initiates the execution of the courier service application, guiding the user through the input process
     * and handling exceptions gracefully.
     */
    fun run() {
        try {
            commandLineOutPut.appLaunch()
            getBaseDeliveryCostAndNoOfPackages()
        } catch (exception: Exception) {
            commandLineOutPut.genericError(exception)
            exitProcess(1)  // Exit the application with an error code
        }
    }

    /**
     * Obtains the base delivery cost and the number of packages from the user, initiating the input process.
     */
    private fun getBaseDeliveryCostAndNoOfPackages() {
        commandLineOutPut.baseDeliveryAndPackages()
        var userInput = readln().trim()
        var validInput = application.validateBaseDeliveryCostNoOfPackages(userInput)
        while (!validInput) {
            commandLineOutPut.baseDeliveryAndPackagesError()
            commandLineOutPut.baseDeliveryAndPackages()
            // Keep prompting until valid input is received
            userInput = readln().trim()
            validInput = application.validateBaseDeliveryCostNoOfPackages(userInput)
        }
        val parts = InputSplitter.splitInput(userInput, 2)
        val delivery = Delivery()
        delivery.baseCost = (InputSplitter.getPartAtIndex(parts, 0)).toDouble()
        getPackageIdWeightDistanceAndOfferCode(delivery, InputSplitter.getPartAtIndex(parts, 1))
    }

    /**
     * Obtains package details (ID, weight, distance, and offer code) for the specified number of packages.
     */
    private fun getPackageIdWeightDistanceAndOfferCode(delivery: Delivery, noOfPackages: String) {
        for (count in 1..noOfPackages.toInt()) {
            commandLineOutPut.packageCount(count)
            var item = processPackages()
            while (delivery.packages.find { it.id == item.id } != null) {
                commandLineOutPut.packageExistsError()
                item = processPackages()
            }
            delivery.packages.add(item)
        }
        getNumberOfVehiclesSpeedAndWeight(delivery)
    }

    /**
     * Obtains package details from the user input, handling input validation.
     *
     * @return The created Package object based on user input.
     */
    private fun processPackages(): Package {
        commandLineOutPut.packageWeightDistanceAndOffer()
        var userInput = readln().trim()
        var validInput = application.validatePackageIdWeightDistanceOfferCode(userInput)
        while (!validInput) {
            commandLineOutPut.packageWeightDistanceAndOfferError()
            commandLineOutPut.packageWeightDistanceAndOffer()
            // Keep prompting until valid input is received
            userInput = readln().trim()
            validInput = application.validatePackageIdWeightDistanceOfferCode(userInput)
        }
        val parts = InputSplitter.splitInput(userInput, 4)
        return getPackage(parts)
    }

    /**
     * Obtains the number of vehicles, their maximum speed, and maximum weight from the user.
     * Initiates calculations and prints the results.
     */
    private fun getNumberOfVehiclesSpeedAndWeight(delivery: Delivery) {
        commandLineOutPut.vehicleSpeedAndWeight()
        var userInput = readln().trim()
        var validInput = application.validateNoOfVehiclesMaxSpeedMaxWeight(userInput)
        while (!validInput) {
            commandLineOutPut.vehicleSpeedAndWeightError()
            commandLineOutPut.vehicleSpeedAndWeight()
            // Keep prompting until valid input is received
            userInput = readln().trim()
            validInput = application.validateNoOfVehiclesMaxSpeedMaxWeight(userInput)
        }
        val parts = InputSplitter.splitInput(userInput, 3)
        for (count in 1..InputSplitter.getPartAtIndex(parts, 0).toInt()) {
            val vehicle = Vehicle(
                InputSplitter.getPartAtIndex(parts, 1).toDouble(),
                InputSplitter.getPartAtIndex(parts, 2).toDouble()
            )
            delivery.vehicles.add(vehicle)
        }
        // Start Calculations
        application.performCalculations(delivery)
        // Print results
        commandLineOutPut.printResults(delivery)
    }

    /**
     * Creates a Package object based on the specified parts.
     *
     * @param parts A list containing package details (ID, weight, distance, and offer code).
     * @return The created Package object.
     */
    private fun getPackage(parts: List<String>): Package {
        return Package(
            InputSplitter.getPartAtIndex(parts, 0),
            InputSplitter.getPartAtIndex(parts, 1).toDouble(),
            InputSplitter.getPartAtIndex(parts, 2).toDouble(),
            InputSplitter.getPartAtIndex(parts, 3), 0.0, 0.0, 0.0, 0.0, 0.0
        )
    }
}
