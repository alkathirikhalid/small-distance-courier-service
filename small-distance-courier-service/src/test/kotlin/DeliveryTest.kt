import domain.cost.Discount
import domain.delivery.Delivery
import domain.delivery.DeliveryCost
import domain.delivery.Package
import org.junit.jupiter.api.Test
import usecase.CostDiscountUseCase
import kotlin.test.assertEquals

class DeliveryTest {
    @Test
    fun `Delivery Cost Calculation`() {
        // Delivery Cost = Base Delivery Cost + (Package Total Weight * 10) + (Distance to Destination * 5)

        // Arrange
        val deliveryCost = DeliveryCost()
        val costDiscountUseCase = CostDiscountUseCase()

        val baseCost = 100.0
        val packageTotalWeight = 30.0
        val distanceToDestination = 50.0

        val delivery = Delivery()
        val item = Package("PKG1", packageTotalWeight, distanceToDestination, "", 0.0, 0.0, 0.0)
        delivery.packages.add(item)
        delivery.baseCost = baseCost

        // Act
        val result1 = deliveryCost.calculateDeliveryCost(baseCost, packageTotalWeight, distanceToDestination)
        val result2 = costDiscountUseCase.calculatePackageCost(delivery)

        // Assert
        assertEquals(650.0, result1)
        assertEquals(650.0, result2.packages[0].deliveryCost)
    }

    @Test
    fun `Discount Calculation 0 Percent`() {
        // Discount = (Offer / 100) * Delivery Cost.

        // Arrange
        val discount = Discount()
        val packageOffer = 0.0
        val packageCost = 50.0

        // Act
        val result = discount.calculateDiscount(packageOffer, packageCost)

        // Assert
        assertEquals(0.0, result)
    }

    @Test
    fun `Discount Calculation 5 Percent`() {
        // Discount = (Offer / 100) * Delivery Cost.

        // Arrange
        val discount = Discount()
        val packageOffer = 5.0
        val packageCost = 50.0

        // Act
        val result = discount.calculateDiscount(packageOffer, packageCost)

        // Assert
        assertEquals(2.5, result)
    }

    @Test
    fun `Discount Calculation 7 Percent`() {
        // Discount = (Offer / 100) * Delivery Cost.

        // Arrange
        val discount = Discount()
        val packageOffer = 7.0
        val packageCost = 50.0

        // Act
        val result = discount.calculateDiscount(packageOffer, packageCost)

        // Assert
        assertEquals(3.5000000000000004, result)
    }

    @Test
    fun `Discount Calculation 10 Percent`() {
        // Discount = (Offer / 100) * Delivery Cost.

        // Arrange
        val discount = Discount()
        val packageOffer = 10.0
        val packageCost = 50.0

        // Act
        val result = discount.calculateDiscount(packageOffer, packageCost)

        // Assert
        assertEquals(5.0, result)
    }
}