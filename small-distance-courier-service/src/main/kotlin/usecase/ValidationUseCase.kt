package usecase

import app.Validation

class ValidationUseCase {
    private val packageIdWeightDistanceOfferCodeRegex = Validation.packageId_weight_distance_offerCode
    fun validatePackageIdWeightDistanceOfferCode(input: String): Boolean {
        return packageIdWeightDistanceOfferCodeRegex.matches(input)
    }
}