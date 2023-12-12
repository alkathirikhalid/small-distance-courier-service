package usecase

import domain.cost.Discount
import domain.cost.TotalCost
import domain.delivery.Delivery
import domain.delivery.DeliveryCost

class CostDiscountUseCase {
    private val deliveryCost = DeliveryCost()
    private val discount = Discount()
    private val totalCost = TotalCost()
    fun calculatePackageCost(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.deliveryCost = deliveryCost.calculateDeliveryCost(delivery.baseCost, item.weight, item.distance)
        }
        return delivery
    }

    fun calculatePackageDiscount(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.discount = discount.calculateDiscount(item.offer, item.deliveryCost)
        }
        return delivery
    }

    fun calculateTotalCost(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.totalCost = totalCost.calculateTotalCost(item.deliveryCost, item.discount)
        }
        return delivery
    }
}