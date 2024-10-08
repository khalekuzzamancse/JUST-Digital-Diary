package backend.services

import java.io.File

class FileLoader(
    private val path: String
) {
    fun loadFromFile(): String? {
        return try {
            File(path).readText()
        } catch (e: Exception) {
            logger.error("Error reading file: {}", e.message)
            null
        }
    }

    fun saveToFile(content: String) {
        try {
            File(path).writeText(content)
        } catch (e: Exception) {
            logger.error("Error saving file: {}", e.message)
        }
    }
}
