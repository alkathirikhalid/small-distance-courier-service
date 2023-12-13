package app

import domain.delivery.Delivery
import usecase.*

/**
 * The `Application` class serves as the main orchestrator for the delivery application.
 *
 * This class encapsulates various use cases for validating inputs, performing package and delivery-related operations,
 * and calculating costs, discounts, and estimated delivery times.
 *
 * @property validationUseCase An instance of the [ValidationUseCase] class for input validation.
 * @property packageOperationsUseCase An instance of the [PackageOperationsUseCase] class for package-related operations.
 * @property costDiscountUseCase An instance of the [CostDiscountUseCase] class for calculating package costs, discounts, and total costs.
 * @property deliveryTimeUseCase An instance of the [DeliveryTimeUseCase] class for estimating delivery times and handling package delivery by vehicles.
 * @property remainderPackageDeliveryTimeUseCase An instance of the [RemainderPackageDeliveryTimeUseCase] class for estimating delivery times of remaining packages.
 */
class Application {
    private val validationUseCase = ValidationUseCase()
    private val packageOperationsUseCase = PackageOperationsUseCase()
    private val costDiscountUseCase = CostDiscountUseCase()
    private val deliveryTimeUseCase = DeliveryTimeUseCase()
    private val remainderPackageDeliveryTimeUseCase = RemainderPackageDeliveryTimeUseCase()

    /**
     * Validates the format of the base delivery cost and the number of packages.
     *
     * @param input The input string to be validated.
     * @return `true` if the input matches the expected format, `false` otherwise.
     */
    fun validateBaseDeliveryCostNoOfPackages(input: String): Boolean {
        return validationUseCase.validateBaseDeliveryCostNoOfPackages(input)
    }

    /**
     * Validates the format of the package details, including ID, weight, distance, and an optional offer code.
     *
     * @param input The input string to be validated.
     * @return `true` if the input matches the expected format, `false` otherwise.
     */
    fun validatePackageIdWeightDistanceOfferCode(input: String): Boolean {
        return validationUseCase.validatePackageIdWeightDistanceOfferCode(input)
    }

    /**
     * Validates the format of the delivery details, including number of vehicles, max speed, and max weight.
     *
     * @param input The input string to be validated.
     * @return `true` if the input matches the expected format, `false` otherwise.
     */
    fun validateNoOfVehiclesMaxSpeedMaxWeight(input: String): Boolean {
        return validationUseCase.validateNoOfVehiclesMaxSpeedMaxWeightRegex(input)
    }

    private fun getPackageOfferPercentage(delivery: Delivery): Delivery {
        return packageOperationsUseCase.getPackageOfferPercentage(delivery)
    }

    private fun calculatePackageCost(delivery: Delivery): Delivery {
        return costDiscountUseCase.calculatePackageCost(delivery)
    }

    private fun calculatePackageDiscount(delivery: Delivery): Delivery {
        return costDiscountUseCase.calculatePackageDiscount(delivery)
    }

    private fun calculateTotalCost(delivery: Delivery): Delivery {
        return costDiscountUseCase.calculateTotalCost(delivery)
    }

    private fun calculateEstimatedDeliveryTime(delivery: Delivery): Delivery {
        return deliveryTimeUseCase.calculateEstimatedDeliveryTime(delivery)
    }

    private fun calculatePackageDeliveryPerVehicle(delivery: Delivery): Delivery {
        return deliveryTimeUseCase.calculatePackageDeliveryPerVehicle(delivery)
    }

    private fun calculateVehicleNextAvailableTime(delivery: Delivery): Delivery {
        return deliveryTimeUseCase.calculateVehicleNextAvailableTime(delivery)
    }

    private fun calculateEstimatedRemainderPackagesDeliveryTime(delivery: Delivery) {
        remainderPackageDeliveryTimeUseCase.calculateEstimatedRemainderPackagesDeliveryTime(delivery)
    }

    /**
     * Performs calculations for each package within a delivery, including calculating costs, discounts, total costs,
     * and estimating delivery times.
     *
     * @param delivery The delivery containing packages to perform calculations for.
     */
    fun performCalculations(delivery: Delivery) {
        for (item in delivery.packages) {
            // Offer
            getPackageOfferPercentage(delivery)
            // Cost
            calculatePackageCost(delivery)
            // Discount
            calculatePackageDiscount(delivery)
            // Total Cost
            calculateTotalCost(delivery)
            // Individual estimated delivery time
            calculateEstimatedDeliveryTime(delivery)
        }
        // Each vehicle to carry max weight equivalent of packages heavier first
        // Load packages to vehicle to be delivered
        calculatePackageDeliveryPerVehicle(delivery)
        // Update remainder packages delivery time
        calculateVehicleNextAvailableTime(delivery)
        calculateEstimatedRemainderPackagesDeliveryTime(delivery)
    }
}