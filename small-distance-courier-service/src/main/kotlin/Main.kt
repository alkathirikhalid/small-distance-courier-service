import app.Application
import app.CommandLineIO
import app.CommandLineOutPut

fun main() {
    val application = Application()
    val commandLineOutPut = CommandLineOutPut()
    val commandLineIO = CommandLineIO(application, commandLineOutPut)
    commandLineIO.run()
}