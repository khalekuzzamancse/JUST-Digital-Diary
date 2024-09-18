package database.local.schema.notebook

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


/**
 * Represents the schema for a note in the Realm database.
 * This class is internal to avoid exposing the database implementation details.
 */
internal class NoteSchema : RealmObject {
    @PrimaryKey
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var timeStamp: String = ""

    fun toEntity() = NoteEntityLocal(
        id = id,
        title = title,
        description = description,
        timeStamp = timeStamp
    )

    override fun toString(): String {
        return "NoteSchema(id='$id', title='$title', description='$description', timeStamp='$timeStamp')"
    }
}

data class NoteEntityLocal(
    val id:String,
    val title:String,
    val description:String,
    val timeStamp:String
)
