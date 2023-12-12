import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testCommandLineApp() {
        // Redirect System.out to capture printed output
        val outputCapture = ByteArrayOutputStream()
        System.setOut(PrintStream(outputCapture))

        // Simulate user input
        val simulatedInput = buildString {
            appendLine("100 3")  // Base delivery cost and no. of packages
            appendLine("PKG1 5 5 OFR001")  // Package 1 details
            appendLine("PKG2 15 5 OFR002")  // Package 2 details
            appendLine("PKG3 10 100 OFR003")  // Package 3 details
        }

        // Set System.in to use the simulated input
        System.setIn(ByteArrayInputStream(simulatedInput.toByteArray()))

        // Run the command line application
        main()

        // Restore System.in and System.out
        System.setIn(System.`in`)
        System.setOut(System.out)

        // Verify the output
        val expectedOutput = buildString {
            appendLine("Please input the base delivery cost and no. of packages:")
            appendLine("Please input the package Id, Weight in Kg, Distance in Km and Offer Code:")
            appendLine("Package 2:")
            appendLine("Package 3:")
            appendLine("PKG1 0 175 ")
            appendLine("PKG2 0 275 ")
            appendLine("PKG3 35 665")
        }

        assertEquals(expectedOutput.trim(), outputCapture.toString().trim())
    }
}