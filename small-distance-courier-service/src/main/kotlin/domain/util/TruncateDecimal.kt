package domain.util

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Represents a utility class for truncating decimal numbers.
 *
 * This class provides a method to truncate a decimal number to a specified number of decimal places.
 *
 * @property decimalFormat The instance of [DecimalFormat] used for formatting and rounding decimals.
 */
class TruncateDecimal {
    private val decimalFormat = DecimalFormat("#.##")

    init {
        decimalFormat.roundingMode = RoundingMode.DOWN
    }

    /**
     * Truncates a decimal number to two decimal places.
     *
     * @param number The decimal number to be truncated.
     * @return The truncated decimal number.
     */
    fun truncate(number: Double): Double {
        return decimalFormat.format(number).toDouble()
    }
}
