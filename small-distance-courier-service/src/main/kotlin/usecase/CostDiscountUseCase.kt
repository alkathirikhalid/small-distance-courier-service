package usecase

import domain.delivery.Delivery
import domain.delivery.DeliveryCost

class CostDiscountUseCase {
    private val deliveryCost = DeliveryCost()
    fun calculatePackageCost(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.deliveryCost = deliveryCost.calculateDeliveryCost(delivery.baseCost, item.weight, item.distance)
        }
        return delivery
    }
}