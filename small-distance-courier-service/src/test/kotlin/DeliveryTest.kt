import domain.cost.Discount
import domain.cost.TotalCost
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
        val item = Package("PKG1", packageTotalWeight, distanceToDestination, "", 0.0, 0.0, 0.0, 0.0)
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
    fun `Discount Calculation`() {
        // Arrange
        val costDiscountUseCase = CostDiscountUseCase()
        val delivery = Delivery()

        val item10 = Package("", 0.0, 0.0, "", 10.0, 50.0, 0.0, 0.0)
        val item07 = Package("", 0.0, 0.0, "", 7.0, 50.0, 0.0, 0.0)
        val item05 = Package("", 0.0, 0.0, "", 5.0, 50.0, 0.0, 0.0)
        val item00 = Package("", 0.0, 0.0, "", 0.0, 50.0, 0.0, 0.0)

        delivery.packages.add(item10)
        delivery.packages.add(item07)
        delivery.packages.add(item05)
        delivery.packages.add(item00)

        // Act
        costDiscountUseCase.calculatePackageDiscount(delivery)

        // Assert
        assertEquals(5.0, delivery.packages[0].discount)
        assertEquals(3.5000000000000004, delivery.packages[1].discount)
        assertEquals(2.5, delivery.packages[2].discount)
        assertEquals(0.0, delivery.packages[3].discount)
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

    @Test
    fun `Total Cost Calculation`() {
        // Arrange
        val costDiscountUseCase = CostDiscountUseCase()
        val delivery = Delivery()

        val item10 = Package("", 0.0, 0.0, "", 0.0, 50.0, 5.0, 0.0)
        val item07 = Package("", 0.0, 0.0, "", 0.0, 50.0, 3.5, 0.0)
        val item05 = Package("", 0.0, 0.0, "", 0.0, 50.0, 2.5, 0.0)
        val item00 = Package("", 0.0, 0.0, "", 0.0, 50.0, 0.0, 0.0)

        delivery.packages.add(item10)
        delivery.packages.add(item07)
        delivery.packages.add(item05)
        delivery.packages.add(item00)

        // Act
        costDiscountUseCase.calculateTotalCost(delivery)

        // Assert
        assertEquals(45.0, delivery.packages[0].totalCost)
        assertEquals(46.5, delivery.packages[1].totalCost)
        assertEquals(47.5, delivery.packages[2].totalCost)
        assertEquals(50.0, delivery.packages[3].totalCost)

    }

    @Test
    fun `Total Cost`() {
        // Discount = (Offer / 100) * Delivery Cost.

        // Arrange
        val totalCostCalculator = TotalCost()

        // Act
        val packageCost = 100.0
        val packageDiscount = 20.0
        val totalCost = totalCostCalculator.calculateTotalCost(packageCost, packageDiscount)

        // Assert
        assertEquals(80.0, totalCost, "Total cost calculation is incorrect")
    }
}