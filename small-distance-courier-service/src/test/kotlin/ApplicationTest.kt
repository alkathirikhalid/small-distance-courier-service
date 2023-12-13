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
            appendLine("100 5")  // Base delivery cost and no. of packages
            appendLine("PKG1 50 30 OFR001")  // Package 1 details
            appendLine("PKG2 75 125 OFR008")  // Package 2 details
            appendLine("PKG3 175 100 OFR003")  // Package 3 details
            appendLine("PKG4 110 60 OFR002")  // Package 4 details
            appendLine("PKG5 155 95 NA")  // Package 5 details
            appendLine("2 70 200")  // Package 5 details
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
            appendLine("\u001B[32m******************************************************\u001B[0m")
            appendLine("\u001B[32m*  Welcome to Kiki's Small Distance Courier Service  *\u001B[0m")
            appendLine("\u001B[32m******************************************************\u001B[0m")
            appendLine("\u001B[33m               Let's get started!                    \u001B[0m")
            appendLine("\u001B[36m       (Follow the provided instructions below)        \u001B[0m")
            appendLine("Please input the base delivery cost and no. of packages:")
            appendLine("Please input the package Id, Weight in Kg, Distance in Km and Offer Code:")
            appendLine("Package 2:")
            appendLine("Package 3:")
            appendLine("Package 4:")
            appendLine("Package 5:")
            appendLine("Please input No. of Vehicles, Max speed, and max weight:")
            appendLine("PKG1 0 750 3.98")
            appendLine("PKG2 0 1475 1.78")
            appendLine("PKG3 0 2350 1.42")
            appendLine("PKG4 105 1395 0.85")
            appendLine("PKG5 0 2125 4.19")
        }

        assertEquals(expectedOutput.trim(), outputCapture.toString().trim())
    }
}