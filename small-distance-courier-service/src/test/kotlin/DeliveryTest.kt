import domain.delivery.Delivery
import domain.delivery.DeliveryCost
import domain.delivery.Package
import org.junit.jupiter.api.Test
import usecase.CostDiscountUseCase
import kotlin.test.assertEquals

class DeliveryTest {
    @Test
    fun `Delivery Cost`() {
        // Delivery Cost = Base Delivery Cost + (Package Total Weight * 10) + (Distance to Destination * 5)
        
        // Arrange
        val deliveryCost = DeliveryCost()
        val costDiscountUseCase = CostDiscountUseCase()

        val baseCost = 100.0
        val packageTotalWeight = 30.0
        val distanceToDestination = 50.0

        val delivery = Delivery()
        val item = Package("PKG1", packageTotalWeight, distanceToDestination, "", 0.0, 0.0)
        delivery.packages.add(item)
        delivery.baseCost = baseCost

        // Act
        val result1 = deliveryCost.calculateDeliveryCost(baseCost, packageTotalWeight, distanceToDestination)
        val result2 = costDiscountUseCase.calculatePackageCost(delivery)

        // Assert
        assertEquals(650.0, result1)
        assertEquals(650.0, result2.packages[0].deliveryCost)
    }
}