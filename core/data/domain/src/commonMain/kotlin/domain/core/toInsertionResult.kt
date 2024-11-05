package domain.core

import domain.model.InsertionResult
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

internal fun toInsertionResult(
    json: String,
    fields: List<Pair<String, String>>,
    primaryKey: String
): InsertionResult {
    if (fields.isEmpty())
        return InsertionResult(primaryKey = primaryKey, json = json)

    var jsonObject = Json.parseToJsonElement(json).jsonObject
    for ((field, value) in fields) {
        jsonObject = JsonObject(jsonObject + (field to JsonPrimitive(value)))
    }
    val updatedJson = jsonObject.toString()
    return InsertionResult(primaryKey = primaryKey, json = updatedJson)
}