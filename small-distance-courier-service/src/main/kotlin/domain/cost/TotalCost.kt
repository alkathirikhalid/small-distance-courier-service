package domain.cost

class TotalCost {
    fun calculateTotalCost(packageCost: Double, packageDiscount: Double): Double {
        return packageCost - packageDiscount
    }
}