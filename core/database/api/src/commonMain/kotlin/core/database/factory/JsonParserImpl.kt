package core.database.factory

import core.database.network.JsonParser
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/**- Outer module should not create direct instance of this but can use it by getting
 * instance via `factory method`
 */
class JsonParserImpl internal constructor() : JsonParser {
    private val jsonParser = Json {
        ignoreUnknownKeys = true // Allows flexibility when the JSON contains unknown keys.
        prettyPrint=true
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

    override fun <T> toJsonOrThrow(value: T, serializer: KSerializer<T>): String {
        val parser = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
        return parser.encodeToString(serializer, value)
    }
    override fun <T> toJsonListOrThrow(valueList: List<T>, serializer: KSerializer<T>): String {
        return jsonParser.encodeToString(ListSerializer(serializer), valueList)
    }
}
