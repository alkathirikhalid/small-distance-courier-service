package app

class Validation {
    companion object {
        val packageId_weight_distance_offerCode =
            Regex("""^(PKG\d+)\s+([1-9]\d*)\s+([1-9]\d*)(?:\s+(\w+))?${'$'}""")

        val baseDeliveryCost_noOfPackages =
            Regex("""^([1-9]\d*)\s+([1-9]\d*)${'$'}""")
    }
}