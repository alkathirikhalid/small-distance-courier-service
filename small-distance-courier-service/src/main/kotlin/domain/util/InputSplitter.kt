package domain.util

/**
 * Utility class for splitting and extracting parts from input strings.
 *
 * This class provides methods for splitting input strings based on a specified limit and retrieving
 * parts at specific indices from the resulting list.
 */
class InputSplitter {
    companion object {
        /**
         * Splits an input string into a list of parts based on a specified limit.
         *
         * @param input The input string to be split.
         * @param limit The maximum number of parts to be returned.
         * @return A list of parts obtained by splitting the input string.
         */
        fun splitInput(input: String, limit: Int): List<String> {
            return input.split(" ", limit = limit)
        }

        /**
         * Retrieves a part from a list at a specific index, providing a default value if the index is out of bounds.
         *
         * @param parts The list of parts obtained from splitting an input string.
         * @param index The index of the part to be retrieved.
         * @param defaultValue The default value to be returned if the index is out of bounds. Default is an empty string.
         * @return The part at the specified index or the default value if the index is out of bounds.
         */
        fun getPartAtIndex(parts: List<String>, index: Int, defaultValue: String = ""): String {
            return if (index < parts.size) parts[index] else defaultValue
        }
    }
}