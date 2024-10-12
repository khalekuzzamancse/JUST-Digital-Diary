package core.database.monggodb.services

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**- Outer module should not create direct instance of this but can use it by getting
 * instance via `factory method`
 */
class JsonParserImpl internal constructor() : JsonParser {
    private val jsonParser = Json {
        ignoreUnknownKeys = true // Allows flexibility when the JSON contains unknown keys.
    }


    override fun <T> parse(json: String, serializer: KSerializer<T>): Result<T> {
        return try {
            val result = jsonParser.decodeFromString(serializer, json)
            Result.success(result)
        } catch (ex: Exception) {
            Result.failure(
                Throwable(
                    message = "Failed to parse the JSON at ${this.javaClass.name}",
                    cause = Throwable(
                        message = "Json was:$json\nthe error was:\n$ex"
                    )

                )
            )
        }
    }

    override fun <T> parseOrThrow(json: String, serializer: KSerializer<T>): T {
        return jsonParser.decodeFromString(serializer, json)
    }
}
