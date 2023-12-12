import org.junit.jupiter.api.Test
import usecase.ValidationUseCase
import kotlin.test.assertFalse

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
}