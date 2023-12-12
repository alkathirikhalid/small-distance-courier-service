import domain.delivery.Package
import org.junit.jupiter.api.Test
import usecase.PackageOperationsUseCase
import usecase.ValidationUseCase
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
    fun `Base Delivery Cost and Number of Packages must be positive integers`() {
        // Arrange
        val validationUseCase = ValidationUseCase()

        // Act
        val isValidBaseCost = validationUseCase.validatePackageIdWeightDistanceOfferCode("PKG1 0 1 OFR001")
        val isValidNoOfPackages = validationUseCase.validatePackageIdWeightDistanceOfferCode("PKG1 1 0 OFR001")

        // Assert
        assertFalse(isValidBaseCost, "Negative integers and decimal numbers should not be valid for Base Cost")
        assertFalse(isValidNoOfPackages, "Negative integers and decimal numbers should not be valid for No of Packages")
    }

    @Test
    fun `Package should meet the required mentioned offer criteria`() {
        // Arrange
        val packageOperationsUseCase = PackageOperationsUseCase()

        val isValidPackage = Package("PKG1", 50.0, 30.0, "OFR001")
        val isInValidPackage = Package("PKG1", 200.0, 30.0, "OFR001")

        // Act
        val isValid = packageOperationsUseCase.validatePackageOffer(isValidPackage)
        val isInValid = packageOperationsUseCase.validatePackageOffer(isInValidPackage)

        // Assert
        assertFalse(isValid, "Package should not be valid for the offer")
        assertTrue(isInValid, "Package should not be valid for the offer")
    }
}