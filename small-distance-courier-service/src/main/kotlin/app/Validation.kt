package app

/**
 * This class provides regular expressions (Regex) for input validation related to the application.
 *
 * It contains three regular expressions:
 * - [baseDeliveryCost_noOfPackages]: Validates the format for base delivery cost and the number of packages.
 * - [packageId_weight_distance_offerCode]: Validates the format for package details including ID, weight, distance, and optional offer code.
 */
class Validation {
    companion object {
        /**
         * Validates the format for package details including ID, weight, distance, and optional offer code.
         * - ID: Should start with "PKG" followed by a number.
         * - Weight: Should be a positive integer.
         * - Distance: Should be a positive integer.
         * - Offer Code: Optional alphanumeric code.
         */
        val packageId_weight_distance_offerCode =
            Regex("""^(PKG\d+)\s+([1-9]\d*)\s+([1-9]\d*)(?:\s+(\w+))?${'$'}""")

        /**
         * Validates the format for base delivery cost and the number of packages.
         * The format should be: "baseDeliveryCost noOfPackages" where both values must be positive integers.
         */
        val baseDeliveryCost_noOfPackages =
            Regex("""^([1-9]\d*)\s+([1-9]\d*)${'$'}""")
    }
}