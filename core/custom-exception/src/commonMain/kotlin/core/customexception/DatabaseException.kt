@file:Suppress("unused", "spellCheckingInspection")

package core.customexception

interface DatabaseException {
    /**
     * - This exception informs the user that the record already exists in the database, so it cannot be inserted again
     *   If changes are needed, the user should try updating the record instead of inserting it
     * - It helps the user decide whether to update the record or delete the existing one
     *
     *
     * When to throw this exception:
     * - **Case 1:**  In the case of a relational (SQL) database, when a record with the same primary key already exists, and an insertion request is made
     *   with the same primary key.
     * - **Case 1.1:** In the case of a NoSQL database, when a document with the same document ID (which acts as the primary key) already exists,
     *   and an insertion request is made with the same document ID.
     * - **Case 2:** It can also be used to prevent the insertion of duplicate data, where duplication may or may not depend on the primary key or document ID.
     *   If you determine that a record is a duplicate, based on your specific use case, this exception can be thrown to prevent the duplicate insertion.
     *
     *   @param message see [CustomException] docs
     *   @param debugMessage see [CustomException]  docs
     */

    class DuplicateRecordException(
        override val message: String = "Exists already, Consider updating or deleting old one",
        override val debugMessage: String = "Attempt to insert an existing record for entity"
    ) : CustomException(
        message = message,
        debugMessage = debugMessage
    ) {

        override val code = "DE-DRE"

    }

    /**
     * Exception to be thrown when a record is not found in the database during any operation.
     *
     * - This exception informs the user that the requested record (for read, insert, update, or delete) does not exist.
     * - It is designed to handle cases where the primary key, document ID, or any unique field does not match any record.
     * - Additionally, it can handle custom query scenarios where specific field or attribute values are not found.
     *
     * ## When to throw this exception:
     * - **Case 1:** When the requested data for any operation (read, insert, update, delete) cannot be found.
     *   - **Relational Database (SQL):** When no record exists with the provided primary key or unique identifier.
     *   - **NoSQL Database:** When the document ID does not match any document in the database.
     * - **Case 2:** When a custom query is made (e.g., searching by a non-key attribute or field) and no matching record is found.
     *   - This may involve searching based on specific fields or conditions that are not related to the primary key or document ID.
     *
     * ## Recommendations:
     * - Override the `message` and `debugMessage` based on the specific operation type (e.g., read, insert, update, delete) and the context of the data being queried.
     * - Provide a clear `message` to end users or API consumers, and a more detailed `debugMessage` for internal logging and debugging purposes.
     *
     *   @param message see [CustomException] docs
     *   @param debugMessage see [CustomException]  docs
     */
    class RecordNotFoundException(
        override val message: String = "No record found for the given criteria.",
        override val debugMessage: String = "Query returned no results for the provided primary key, document ID, or custom query."
    ) : CustomException(
        message = message,
        debugMessage = debugMessage
    ) {

        override val code = "DE-RNFE"

    }

    /**
     * - Should thrown when database instance can not create
     *
     * - for [message],[debugMessage] and [code] see the [CustomException] docs
     */
    class DatabaseCanNotCreateException(
        override val message: String = "DE-DICNCE:Something went wrong",
        override val debugMessage: String = "Failed to create or configure the database instance"
    ) : CustomException(
        message = message,
        debugMessage = debugMessage
    ) {
        override val code = "DE-DICNCE"

    }

}