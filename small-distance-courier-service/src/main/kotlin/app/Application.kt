package app

import domain.delivery.Delivery
import usecase.CostDiscountUseCase
import usecase.PackageOperationsUseCase
import usecase.ValidationUseCase

class Application {

    private val validationUseCase = ValidationUseCase()
    private val packageOperationsUseCase = PackageOperationsUseCase()
    private val costDiscountUseCase = CostDiscountUseCase()

    fun validateBaseDeliveryCostNoOfPackages(input: String): Boolean {
        return validationUseCase.validateBaseDeliveryCostNoOfPackages(input)
    }

    fun validatePackageIdWeightDistanceOfferCode(input: String): Boolean {
        return validationUseCase.validatePackageIdWeightDistanceOfferCode(input)
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
        }
    }
}
