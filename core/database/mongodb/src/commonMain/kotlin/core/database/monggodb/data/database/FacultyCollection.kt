package core.database.monggodb.data.database

import core.database.monggodb.data.database.MongoDBClient.COLLECTION_FACULTY
import core.database.monggodb.data.database.MongoDBClient.DATABASE_NAME
import core.database.monggodb.domain.model.FacultyEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class FacultyCollection {

    private companion object {
        const val PRIORITY_FIELD = "priority"
        const val FACULTY_ID_FIELD = "faculty_id"
        const val NAME_FIELD = "name"
    }

    /**
     * Adds a faculty to the database.
     *
     * @param faculty The Faculty object to be added.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun addFaculty(faculty: FacultyEntity) = withExceptionHandle {
        MongoDBClient.executeWriteOperation(DATABASE_NAME, COLLECTION_FACULTY) { database ->
            val collection = database.getCollection<Document>(COLLECTION_FACULTY)
            val primaryKey = faculty.name.replace(" ", "").lowercase()

            val facultyDoc = Document()
                .append("_id", primaryKey)
                .append(FACULTY_ID_FIELD, primaryKey)
                .append(PRIORITY_FIELD, faculty.priority)
                .append(NAME_FIELD, faculty.name)

            collection.insertOne(facultyDoc)
        }
    }

    /**
     * Retrieves all faculties from the database.
     *
     * @return A list of Faculty objects.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun getAllFaculties(): List<FacultyEntity> {
        return MongoDBClient.executeReadOperation(DATABASE_NAME, COLLECTION_FACULTY) { database ->
            val collection = database.getCollection<Document>(COLLECTION_FACULTY)
            collection.find().toList().map { document ->
                FacultyEntity(
                    priority = document.getInteger(PRIORITY_FIELD),
                    facultyId = document.getString(FACULTY_ID_FIELD),
                    name = document.getString(NAME_FIELD),
                )
            }
        }
    }

    @Throws(Throwable::class)
    suspend fun read(id: String): FacultyEntity {
        return MongoDBClient.executeReadOperation(DATABASE_NAME, COLLECTION_FACULTY) { database ->
            val collection = database.getCollection<Document>(COLLECTION_FACULTY)
            val document = collection.find(Document(FACULTY_ID_FIELD, id)).firstOrNull()
                ?: throw NoSuchElementException("No Faculty found")
            FacultyEntity(
                priority = document.getInteger(PRIORITY_FIELD),
                facultyId = document.getString(FACULTY_ID_FIELD),
                name = document.getString(NAME_FIELD),
            )

        }
    }

}
