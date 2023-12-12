package domain.delivery

class DeliveryCost {
    fun calculateDeliveryCost(baseCost: Double, packageTotalWeight: Double, distanceToDestination: Double): Double {
        return baseCost + (packageTotalWeight * 10) + (distanceToDestination * 5)
    }
}