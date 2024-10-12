package core.database.monggodb.services

import java.io.File

class FileLoader(
    private val path: String
) {
    fun loadFromFile(): String? {
        return try {
            File(path).readText()
        } catch (e: Exception) {

            null
        }
    }

    fun saveToFile(content: String) {
        try {
            File(path).writeText(content)
        } catch (e: Exception) {

        }
    }
}
