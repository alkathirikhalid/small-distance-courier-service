import data.Coupons
import domain.delivery.Delivery
import domain.delivery.Package
import domain.offer.Offer
import org.junit.jupiter.api.Test
import usecase.PackageOperationsUseCase
import usecase.ValidationUseCase
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OfferTest {
    @Test
    fun `Only one offer code can be applied for any given package`() {
        // Arrange
        val validationUseCase = ValidationUseCase()

        // Act
        val isValid = validationUseCase.validatePackageIdWeightDistanceOfferCode("PKG1 5 5 OFR001 OFR002")

        // Assert
        assertFalse(isValid, "Multiple offers should not be valid for the package")
    }

    @Test
    fun `Weight and Distance must be positive integers`() {
        // Arrange
        val validationUseCase = ValidationUseCase()

        // Act
        val isValid = validationUseCase.validatePackageIdWeightDistanceOfferCode("PKG1 0 0 OFR001")

        // Assert
        assertFalse(isValid, "Negative integers and decimal numbers should not be valid Weight")
    }

    @Test
    fun `Base Delivery Cost and Number of Packages must be positive integers`() {
        // Arrange
        val validationUseCase = ValidationUseCase()

        // Act
        val isValidBaseCost = validationUseCase.validateBaseDeliveryCostNoOfPackages("100 3")
        val isValidNoOfPackages = validationUseCase.validateBaseDeliveryCostNoOfPackages("200 6")

        // Assert
        assertTrue(isValidBaseCost, "Negative integers and decimal numbers should not be valid for Base Cost")
        assertTrue(isValidNoOfPackages, "Negative integers and decimal numbers should not be valid for No of Packages")
    }

    @Test
    fun `Package should meet the required mentioned offer criteria`() {
        // Arrange
        val packageOperationsUseCase = PackageOperationsUseCase()

        val isValidPackage = Package("PKG1", 50.0, 30.0, "OFR001", 0.0, 0.0, 0.0, 0.0, 0.0)
        val isInValidPackage = Package("PKG1", 200.0, 30.0, "OFR001", 0.0, 0.0, 0.0, 0.0, 0.0)

        // Act
        val isValid = packageOperationsUseCase.validatePackageOffer(isValidPackage)
        val isInValid = packageOperationsUseCase.validatePackageOffer(isInValidPackage)

        // Assert
        assertFalse(isValid, "Package should not be valid for the offer")
        assertTrue(isInValid, "Package should not be valid for the offer")
    }

    @Test
    fun `If offer code is not valid or found, discounted amount will be equal to 0`() {
        // Arrange
        val packageOperationsUseCase = PackageOperationsUseCase()
        val delivery = Delivery()
        val item = Package("PKG1", 50.0, 30.0, "", 0.0, 0.0, 0.0, 0.0, 0.0)
        delivery.packages.add(item)

        // Act
        val isValid = packageOperationsUseCase.getPackageOfferPercentage(delivery)

        // Assert
        assertEquals(0.0, isValid.packages[0].offer, "Offer percentage should be 0.0")
    }

    @Test
    fun `Code should be extensible and scalable for more offer codes`() {
        // Assert Existing
        assertEquals(3, Coupons.offers.size, "Total Old offers should be 3")

        // Arrange
        val newCouponCodes = Coupons.offers

        // Act
        val offer = Offer("OFR004", 1.0, 20.0..150.0, 10.0..60.0)
        newCouponCodes.add(offer)

        // Assert
        assertEquals(4, Coupons.offers.size, "Total New offers should be 4")
    }
}