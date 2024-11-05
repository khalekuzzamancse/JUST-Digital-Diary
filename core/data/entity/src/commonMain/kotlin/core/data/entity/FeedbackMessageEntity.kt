@file:Suppress("unused")
package core.data.entity

import kotlinx.serialization.Serializable

/**
 * - Used by the **data source** or **server** to send feedback messages to the client, such as success, failure, or warning messages
 *
 *
 * - This entity provides a structured way to deliver messages, ensuring that the client can properly handle different types of feedback during operations like data submission or retrieval
 *
 * @property message The **feedback message** sent to the client, which can represent various outcomes (e.g., success, failure, or warning)
 */

@Serializable
 data class FeedbackMessageEntity(val message: String)
