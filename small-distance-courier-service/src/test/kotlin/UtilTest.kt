import domain.util.InputSplitter
import domain.util.TruncateDecimal
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UtilTest {

    @Test
    fun `Truncate delivery time up to 2 digits`() {
        // Arrange
        val truncateDecimal = TruncateDecimal()
        // Act
        val time = truncateDecimal.truncate(3.456)
        // assert
        assertEquals(3.45, time, "Truncated time is invalid")
    }

    @Test
    fun `Input Splitter`() {
        // Arrange & Act
        val parts = InputSplitter.splitInput("2 70 200", 3)
        val noOfVehicles = InputSplitter.getPartAtIndex(parts, 0)
        val maxSpeed = InputSplitter.getPartAtIndex(parts, 1)
        val maxWeight = InputSplitter.getPartAtIndex(parts, 2)

        // assert
        assertEquals("2", noOfVehicles, "Split Input is invalid")
        assertEquals("70", maxSpeed, "Split Input is invalid")
        assertEquals("200", maxWeight, "Split Input is invalid")
    }

}