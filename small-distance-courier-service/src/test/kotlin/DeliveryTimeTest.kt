import domain.delivery.*
import domain.transportation.Vehicle
import org.junit.jupiter.api.Test
import usecase.DeliveryTimeUseCase
import usecase.RemainderPackageDeliveryTimeUseCase
import kotlin.test.assertEquals

class DeliveryTimeTest {

    @Test
    fun `Each Vehicle has a limit (L) on maximum weight (kg) that it can carry`() {
        // Arrange & Act
        val vehicle = Vehicle(70.0, 200.0)

        // Assert
        assertEquals(200.0, vehicle.maxWeight, "Total vehicle max weight is incorrect")
    }

    @Test
    fun `All Vehicles travel at the same speed and in the same route`() {
        // Arrange & Act
        val vehicle = Vehicle(70.0, 200.0)

        // Assert
        assertEquals(70.0, vehicle.maxSpeed, "Total vehicle max speed is incorrect")
    }

    @Test
    fun `Shipment should contain max packages vehicle can carry in a trip - prefer heavier`() {
        // Arrange
        val deliveryCriteria = DeliveryCriteria()
        val delivery = deliveryWithFivePackagesInput()

        // Act
        val packages = deliveryCriteria.findOptimalPackageCombination(delivery.vehicles[1].maxWeight, delivery.packages)

        // Assert
        assertEquals("PKG4", packages[0].id, "Expected PKG is incorrect")
        assertEquals("PKG2", packages[1].id, "Expected PKG is incorrect")
    }

    @Test
    fun `If package weights are the same, preference should be given to the package which can be delivered first`() {
        // Arrange
        val deliveryCriteria = DeliveryCriteria()
        val delivery = deliveryWithSameWeightInput()

        // Act
        val packages = deliveryCriteria.findOptimalPackageCombination(delivery.vehicles[1].maxWeight, delivery.packages)

        // Assert
        assertEquals("PKG1", packages[0].id, "Expected PKG is incorrect")
        assertEquals("PKG3", packages[1].id, "Expected PKG is incorrect")
        assertEquals("PKG4", packages[2].id, "Expected PKG is incorrect")
        assertEquals("PKG5", packages[3].id, "Expected PKG is incorrect")
    }

    @Test
    fun `Delivery time = Distance to cover divided by vehicle max speed`() {
        // Arrange
        val deliveryTime = DeliveryTime()

        // Act
        val deliveryTimeResult = deliveryTime.estimateDeliveryTime(30.0, 70.0)

        // Assert
        assertEquals(0.42, deliveryTimeResult, "Expected PKG delivery time is incorrect")
    }

    /**
     * The following test calculations have dependencies - DeliveryTimeUseCase
     * 1 - calculateEstimatedDeliveryTime(delivery) // Gets time estimates before loading package to vehicle
     * 2 - calculatePackageDeliveryPerVehicle(delivery) // Loading packages into vehicle to distinguish between 'to deliver' and 'pending delivery'
     * 3 - calculateVehicleNextAvailableTime(delivery) // Each vehicle needs to account for the coming back duration
     * 4 - calculateEstimatedRemainderPackagesDeliveryTime(delivery) // Finally calculate the remainder package delivery time
     */
    @Test
    fun `1 - Calculate Estimated Delivery time Vehicle`() {
        // Arrange
        val deliveryTimeUseCase = DeliveryTimeUseCase()
        val delivery = deliveryWithFivePackagesInput()

        // Act
        deliveryTimeUseCase.calculateEstimatedDeliveryTime(delivery)

        // Assert
        assertEquals(
            0.42, delivery.packages[0].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            1.78, delivery.packages[1].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            1.42, delivery.packages[2].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            0.85, delivery.packages[3].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            1.35, delivery.packages[4].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
    }

    @Test
    fun `2 - Calculate Package Delivery Per Vehicle`() {
        // Arrange
        val deliveryTimeUseCase = DeliveryTimeUseCase()
        val delivery = deliveryWithFivePackagesInput()

        // Act
        deliveryTimeUseCase.calculateEstimatedDeliveryTime(delivery)
        deliveryTimeUseCase.calculatePackageDeliveryPerVehicle(delivery)

        // Assert
        assertEquals(
            2, delivery.vehicles[0].packagesToDeliver.size, "Expected PKG to delivery is incorrect"
        )
        assertEquals(
            1, delivery.vehicles[1].packagesToDeliver.size, "Expected PKG to delivery is incorrect"
        )
    }

    @Test
    fun `Vehicle Next Available Time`() {
        // Arrange
        val vehicleNextAvailableTime = VehicleNextAvailableTime()

        // Act
        val estimatedReturnTime = vehicleNextAvailableTime.estimatedReturnTime(1.78)

        // Assert
        assertEquals(
            3.56, estimatedReturnTime, "Expected time to be available is incorrect"
        )
    }

    @Test
    fun `3 - Calculate Vehicle Next Available Time`() {
        // Arrange
        val deliveryTimeUseCase = DeliveryTimeUseCase()
        val delivery = deliveryWithFivePackagesInput()

        // Act
        deliveryTimeUseCase.calculateEstimatedDeliveryTime(delivery)
        deliveryTimeUseCase.calculatePackageDeliveryPerVehicle(delivery)
        deliveryTimeUseCase.calculateVehicleNextAvailableTime(delivery)

        // Assert
        assertEquals(
            3.56, delivery.vehicles[0].availableTime, "Expected time to be available is incorrect"
        )
        assertEquals(
            2.84, delivery.vehicles[1].availableTime, "Expected time to be available is incorrect"
        )
    }

    @Test
    fun `4 - Calculate Estimated Remainder Package Delivery Time`() {
        // Arrange
        val deliveryTimeUseCase = DeliveryTimeUseCase()
        val remainderPackageDeliveryTimeUseCase = RemainderPackageDeliveryTimeUseCase()
        val delivery = deliveryWithFivePackagesInput()

        // Act
        deliveryTimeUseCase.calculateEstimatedDeliveryTime(delivery)
        deliveryTimeUseCase.calculatePackageDeliveryPerVehicle(delivery)
        deliveryTimeUseCase.calculateVehicleNextAvailableTime(delivery)
        remainderPackageDeliveryTimeUseCase.calculateEstimatedRemainderPackagesDeliveryTime(delivery)

        // Assert
        assertEquals(
            3.98, delivery.packages[0].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            1.78, delivery.packages[1].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            1.42, delivery.packages[2].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            0.85, delivery.packages[3].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
        assertEquals(
            4.1899999999999995, delivery.packages[4].estimatedDeliveryTime, "Expected PKG delivery time is incorrect"
        )
    }

    private fun deliveryWithSameWeightInput(): Delivery {
        // Provided Test Data
        val delivery = Delivery()
        delivery.baseCost = 100.00

        val packages: ArrayList<Package> = ArrayList()

        val pkg1 = Package("PKG1", 50.0, 30.0, "OFR001", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg2 = Package("PKG2", 50.0, 125.0, "OFR008", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg3 = Package("PKG3", 50.0, 60.0, "OFR003", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg4 = Package("PKG4", 50.0, 95.0, "OFR002", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg5 = Package("PKG5", 50.0, 100.0, "NA", 0.0, 0.0, 0.0, 0.0, 0.0)

        packages.add(pkg1)
        packages.add(pkg2)
        packages.add(pkg3)
        packages.add(pkg4)
        packages.add(pkg5)

        val vehicle1 = Vehicle(70.0, 200.0)
        val vehicle2 = Vehicle(70.0, 200.0)

        delivery.vehicles.add(vehicle1)
        delivery.vehicles.add(vehicle2)
        delivery.packages = packages

        return delivery
    }

    private fun deliveryWithFivePackagesInput(): Delivery {
        // Provided Test Data
        val delivery = Delivery()
        delivery.baseCost = 100.00

        val packages: ArrayList<Package> = ArrayList()

        val pkg1 = Package("PKG1", 50.0, 30.0, "OFR001", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg2 = Package("PKG2", 75.0, 125.0, "OFR008", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg3 = Package("PKG3", 175.0, 100.0, "OFR003", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg4 = Package("PKG4", 110.0, 60.0, "OFR002", 0.0, 0.0, 0.0, 0.0, 0.0)
        val pkg5 = Package("PKG5", 155.0, 95.0, "NA", 0.0, 0.0, 0.0, 0.0, 0.0)

        packages.add(pkg1)
        packages.add(pkg2)
        packages.add(pkg3)
        packages.add(pkg4)
        packages.add(pkg5)

        val vehicle1 = Vehicle(70.0, 200.0)
        val vehicle2 = Vehicle(70.0, 200.0)

        delivery.vehicles.add(vehicle1)
        delivery.vehicles.add(vehicle2)
        delivery.packages = packages

        return delivery
    }
}