package usecase

import domain.cost.Discount
import domain.cost.TotalCost
import domain.delivery.Delivery
import domain.delivery.DeliveryCost

/**
 * The `CostDiscountUseCase` class provides use case-specific operations related to calculating package costs and discounts.
 *
 * This class encapsulates functionality such as calculating delivery costs, package discounts, and total costs for a given delivery.
 *
 * @property deliveryCost An instance of the [DeliveryCost] class used for calculating delivery costs.
 * @property discount An instance of the [Discount] class used for calculating package discounts.
 * @property totalCost An instance of the [TotalCost] class used for calculating total costs.
 */
class CostDiscountUseCase {
    private val deliveryCost = DeliveryCost()
    private val discount = Discount()
    private val totalCost = TotalCost()

    /**
     * Calculates the delivery cost for each package within a delivery based on the base cost, weight, and distance.
     *
     * @param delivery The delivery containing packages to calculate delivery costs for.
     * @return The modified delivery with updated delivery costs for each package.
     */
    fun calculatePackageCost(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.deliveryCost = deliveryCost.calculateDeliveryCost(delivery.baseCost, item.weight, item.distance)
        }
        return delivery
    }

    /**
     * Calculates the discount for each package within a delivery based on the package's offer and delivery cost.
     *
     * @param delivery The delivery containing packages to calculate discounts for.
     * @return The modified delivery with updated discounts for each package.
     */
    fun calculatePackageDiscount(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.discount = discount.calculateDiscount(item.offer, item.deliveryCost)
        }
        return delivery
    }

    /**
     * Calculates the total cost for each package within a delivery based on the delivery cost and discount.
     *
     * @param delivery The delivery containing packages to calculate total costs for.
     * @return The modified delivery with updated total costs for each package.
     */
    fun calculateTotalCost(delivery: Delivery): Delivery {
        for (item in delivery.packages) {
            item.totalCost = totalCost.calculateTotalCost(item.deliveryCost, item.discount)
        }
        return delivery
    }
}