package usecase

import app.Validation

/**
 * The `ValidationUseCase` class provides use case-specific validation methods using regular expressions.
 *
 * This class utilizes regular expressions from the [Validation] class to validate different types of input.
 *
 * @property baseDeliveryCostNoOfPackagesRegex The regex for validating the format of base delivery cost and the number of packages.
 * @property packageIdWeightDistanceOfferCodeRegex The regex for validating the format of package details, including ID, weight, distance, and an optional offer code.
 * @property noOfVehiclesMaxSpeedMaxWeightRegex The regex for validating the format of the number of vehicles, max speed, and max weight.
 */
class ValidationUseCase {
    private val baseDeliveryCostNoOfPackagesRegex = Validation.baseDeliveryCost_noOfPackages
    private val packageIdWeightDistanceOfferCodeRegex = Validation.packageId_weight_distance_offerCode
    private val noOfVehiclesMaxSpeedMaxWeightRegex = Validation.noOfVehicles_maxSpeed_maxWeight

    /**
     * Validates the format of the first input, which should be in the form of "baseDeliveryCost noOfPackages".
     *
     * @param input The first input string to be validated.
     * @return `true` if the input matches the expected format, `false` otherwise.
     */
    fun validateBaseDeliveryCostNoOfPackages(input: String): Boolean {
        return baseDeliveryCostNoOfPackagesRegex.matches(input)
    }

    /**
     * Validates the format of the second input, which should be in the form of "PKGId weight distance offerCode".
     *
     * @param input The second input string to be validated.
     * @return `true` if the input matches the expected format, `false` otherwise.
     */
    fun validatePackageIdWeightDistanceOfferCode(input: String): Boolean {
        return packageIdWeightDistanceOfferCodeRegex.matches(input)
    }

    /**
     * Validates the format of the third input, which should be in the form of "numberOfVehicles maxSpeed maxWeight".
     *
     * @param input The third input string to be validated.
     * @return `true` if the input matches the expected format, `false` otherwise.
     */
    fun validateNoOfVehiclesMaxSpeedMaxWeightRegex(input: String): Boolean {
        return noOfVehiclesMaxSpeedMaxWeightRegex.matches(input)
    }
}
