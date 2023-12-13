/**
 * The main entry point for the delivery application.
 *
 * This Kotlin script initializes the necessary components of the delivery application and launches the command-line interface.
 * It creates instances of the `Application`, `CommandLineOutPut`, and `CommandLineIO` classes to handle the core logic,
 * command-line output, and user input, respectively. The `run` method of the `CommandLineIO` class is then invoked to start
 * the interactive session, allowing users to input delivery details and observe the results.
 */
import app.Application
import app.CommandLineIO
import app.CommandLineOutPut

fun main() {
    // Create an instance of the Application class
    val application = Application()

    // Create an instance of the CommandLineOutPut class for command-line output
    val commandLineOutPut = CommandLineOutPut()

    // Create an instance of the CommandLineIO class, passing the Application and CommandLineOutPut instances
    val commandLineIO = CommandLineIO(application, commandLineOutPut)

    // Run the command-line interface to start the interactive session
    commandLineIO.run()
}
