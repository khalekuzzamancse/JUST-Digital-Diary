package common.docs
/**
 * - Converts directly to JSON in the format expected by the backend,If the structure doesn't match,
 * the response will fail.
 *
 * SerialName
 *
 * - if the json uses different name conventions such as snack-case but you want to camel case in that case
 * you can use the SerialName(),example:
 * ```kotlin
 * @Serializable
 * internal data class User(
 *     @SerialName("id") val id: Int,
 *     @SerialName("user_name") val userName: String,
 * )
 * ```
 * here `user_name` is the json property name and we used as it as `userName`
 */
class EntityDocs