package core.database.monggodb.data.database

import com.mongodb.client.model.Filters
import core.database.monggodb.data.database.MongoDBClient.COLLECTION_TEACHER
import core.database.monggodb.data.database.MongoDBClient.DATABASE_NAME
import core.database.monggodb.domain.model.TeacherEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class TeacherCollection {

   private companion object {
        const val ID_KEY = "_id"
        const val DEPT_ID_KEY = "dept_id"
        const val PRIORITY_KEY = "priority"
        const val NAME_KEY = "name"
        const val EMAIL_KEY = "email"
        const val PHONE_KEY = "phone"
        const val ADDITIONAL_EMAIL_KEY = "additional_email"
        const val ACHIEVEMENTS_KEY = "achievements"
        const val DESIGNATIONS_KEY = "designations"
        const val ROOM_NO_KEY = "room_no"
    }

    suspend fun add(deptId: String,entity: TeacherEntity) = withExceptionHandle {
        MongoDBClient.executeWriteOperation(DATABASE_NAME, COLLECTION_TEACHER) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)

            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */

            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */

            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */

            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */
            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */
            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */
            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */
            /**
             * - each no two teacher has the same name+same email
             * - TODO:Refactor it later because a teacher may change his email
             */
            val primaryKey = "${entity.name}_${entity.email}"
            val teacherDoc = Document()
                .append(ID_KEY, primaryKey)
                .append(DEPT_ID_KEY,deptId)
                .append(PRIORITY_KEY, entity.priority)
                .append(NAME_KEY, entity.name)
                .append(EMAIL_KEY, entity.email)
                .append(PHONE_KEY, entity.phone)
                .append(ADDITIONAL_EMAIL_KEY, entity.additionalEmail)
                .append(ACHIEVEMENTS_KEY, entity.achievements)
                .append(DESIGNATIONS_KEY, entity.designations)
                .append(ROOM_NO_KEY, entity.roomNo)

            collection.insertOne(teacherDoc)

        }
    }

    @Throws(Throwable::class)
    suspend fun read(): List<TeacherEntity> {
        return MongoDBClient.executeReadOperation(DATABASE_NAME, COLLECTION_TEACHER) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)
            collection.find().toList().map { document ->
                TeacherEntity(
                    priority = document.getInteger(PRIORITY_KEY),
                    id = document.getString(ID_KEY),
                    name = document.getString(NAME_KEY),
                    email = document.getString(EMAIL_KEY),
                    phone = document.getString(PHONE_KEY),
                    achievements = document.getString(ACHIEVEMENTS_KEY),
                    additionalEmail = document.getString(ADDITIONAL_EMAIL_KEY),
                    designations = document.getString(DESIGNATIONS_KEY),
                    roomNo = document.getString(ROOM_NO_KEY)
                )
            }
        }
    }
    @Throws(Throwable::class)
    suspend fun read(deptId:String): List<TeacherEntity> {
        return MongoDBClient.executeReadOperation(DATABASE_NAME, COLLECTION_TEACHER) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)
            collection.find(Filters.eq(DEPT_ID_KEY, deptId)).toList().map { document ->
                TeacherEntity(
                    priority = document.getInteger(PRIORITY_KEY),
                    id = document.getString(ID_KEY),
                    name = document.getString(NAME_KEY),
                    email = document.getString(EMAIL_KEY),
                    phone = document.getString(PHONE_KEY),
                    achievements = document.getString(ACHIEVEMENTS_KEY),
                    additionalEmail = document.getString(ADDITIONAL_EMAIL_KEY),
                    designations = document.getString(DESIGNATIONS_KEY),
                    roomNo = document.getString(ROOM_NO_KEY)
                )
            }
        }
    }
    @Throws(Throwable::class)
    suspend fun readById(teacherId: String): TeacherEntity {
        return MongoDBClient.executeReadOperation(DATABASE_NAME, COLLECTION_TEACHER) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)

            val document = collection.find(Document(ID_KEY, teacherId)).firstOrNull()
                ?: throw NoSuchElementException("No teacher found")

            TeacherEntity(
                priority = document.getInteger(PRIORITY_KEY),
                id = document.getString(ID_KEY),
                name = document.getString(NAME_KEY),
                email = document.getString(EMAIL_KEY),
                phone = document.getString(PHONE_KEY),
                achievements = document.getString(ACHIEVEMENTS_KEY),
                additionalEmail = document.getString(ADDITIONAL_EMAIL_KEY),
                designations = document.getString(DESIGNATIONS_KEY),
                roomNo = document.getString(ROOM_NO_KEY)
            )
        }
    }


}
