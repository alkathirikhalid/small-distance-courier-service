package usecase

import app.Validation

class ValidationUseCase {
    private val packageIdWeightDistanceOfferCodeRegex = Validation.packageId_weight_distance_offerCode
    private val baseDeliveryCostNoOfPackagesRegex = Validation.baseDeliveryCost_noOfPackages
    fun validatePackageIdWeightDistanceOfferCode(input: String): Boolean {
        return packageIdWeightDistanceOfferCodeRegex.matches(input)
    }

    fun validateBaseDeliveryCostNoOfPackages(input: String): Boolean {
        return baseDeliveryCostNoOfPackagesRegex.matches(input)
    }
}