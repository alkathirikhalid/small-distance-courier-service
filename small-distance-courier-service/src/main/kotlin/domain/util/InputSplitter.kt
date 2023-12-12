package domain.util

class InputSplitter {
    companion object {
        fun splitInput(input: String, limit: Int): List<String> {
            return input.split(" ", limit = limit)
        }

        fun getPartAtIndex(parts: List<String>, index: Int, defaultValue: String = ""): String {
            return if (index < parts.size) parts[index] else defaultValue
        }
    }
}