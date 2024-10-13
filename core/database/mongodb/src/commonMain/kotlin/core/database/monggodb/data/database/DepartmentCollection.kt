package core.database.monggodb.data.database

import com.mongodb.client.model.Filters
import core.database.monggodb.data.database.MongoDBClient.COLLECTION_DEPARTMENT
import core.database.monggodb.data.database.MongoDBClient.DATABASE_NAME
import core.database.monggodb.domain.model.DepartmentEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class DepartmentCollection {

    private companion object {
        const val ID_KEY = "_id"
        const val FACULTY_ID_KEY = "faculty_id"
        const val DEPT_ID_KEY = "dept_id"
        const val PRIORITY_KEY = "priority"
        const val NAME_KEY = "name"
        const val SHORTNAME_KEY = "shortname"
    }

    suspend fun create(facultyId: String, entity: DepartmentEntity) = withExceptionHandle {
        MongoDBClient.executeWriteOperation(DATABASE_NAME, COLLECTION_DEPARTMENT) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)
            val id = entity.name.replace(" ", "").lowercase()

            val facultyDoc = Document()
                .append(ID_KEY, id)
                .append(FACULTY_ID_KEY, facultyId)
                .append(DEPT_ID_KEY, id)
                .append(PRIORITY_KEY, entity.priority)
                .append(NAME_KEY, entity.name)
                .append(SHORTNAME_KEY, entity.shortName)

             collection.insertOne(facultyDoc)
        }

    }

    @Throws(Throwable::class)
    suspend fun readAll(): List<DepartmentEntity> {
        return MongoDBClient.executeReadOperation(
            DATABASE_NAME,
            COLLECTION_DEPARTMENT
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)
            collection.find().toList().map { document ->
                DepartmentEntity(
                    priority = document.getInteger(PRIORITY_KEY),
                    deptId = document.getString(DEPT_ID_KEY),
                    name = document.getString(NAME_KEY),
                    shortName = document.getString(SHORTNAME_KEY),
                )
            }
        }
    }

    @Throws(Throwable::class)
    suspend fun readUnderFaculty(facultyId: String): List<DepartmentEntity> {
        return MongoDBClient.executeReadOperation(
            DATABASE_NAME,
            COLLECTION_DEPARTMENT
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)
            collection.find(Filters.eq(FACULTY_ID_KEY, facultyId)).toList().map { document ->
                DepartmentEntity(
                    priority = document.getInteger(PRIORITY_KEY),
                    deptId = document.getString(DEPT_ID_KEY),
                    name = document.getString(NAME_KEY),
                    shortName = document.getString(SHORTNAME_KEY),
                )
            }
        }
    }

    @Throws(Throwable::class)
    suspend fun readById(deptId: String): DepartmentEntity {
        return MongoDBClient.executeReadOperation(
            DATABASE_NAME,
            COLLECTION_DEPARTMENT
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)

            val document = collection.find(Document(DEPT_ID_KEY, deptId)).firstOrNull()
                ?: throw NoSuchElementException("No department found ")

            DepartmentEntity(
                priority = document.getInteger(PRIORITY_KEY),
                deptId = document.getString(DEPT_ID_KEY),
                name = document.getString(NAME_KEY),
                shortName = document.getString(SHORTNAME_KEY),
            )
        }
    }


}
