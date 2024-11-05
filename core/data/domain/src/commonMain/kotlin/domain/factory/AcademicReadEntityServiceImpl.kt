@file:Suppress("unused")
package domain.factory
import core.data.entity.academic.*
import domain.service.AcademicReadEntityService
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class AcademicReadEntityServiceImpl : AcademicReadEntityService {
    private val parser = Json {
        ignoreUnknownKeys = true // Filters the unknown keys such as properties that do not exist in the read entity
        prettyPrint = true
    }

    override fun parseAsFacultyOrThrow(json: String): String {
        val entity = parser.decodeFromString(FacultyReadEntity.serializer(), json)
        return parser.encodeToString(FacultyReadEntity.serializer(), entity)
    }

    override fun parseAsFacultyListOrThrow(json: String): List<FacultyReadEntity> {
       return parser.decodeFromString(ListSerializer(FacultyReadEntity.serializer()), json)
    }

    override fun isFacultyListReadEntity(json: String)= tryParse{
        parser.decodeFromString(ListSerializer(FacultyReadEntity.serializer()), json)
        true
    }


    override fun isDeptListReadEntity(json: String)= tryParse {
        parser.decodeFromString(ListSerializer(DepartmentReadEntity.serializer()), json)
        true
    }

    override fun isTeacherListReadEntity(json: String)=tryParse {
        parser.decodeFromString(ListSerializer(TeacherReadEntity.serializer()), json)
        true
    }

    override fun parseAsTeacherReadEntitiesOrThrow(json: String)=
        parser.decodeFromString(ListSerializer(TeacherReadEntity.serializer()), json)

    override fun toJsonOrThrow(teachers: List<TeacherReadEntity>)=
        parser.encodeToString(value = teachers, serializer = ListSerializer(TeacherReadEntity.serializer()))


    override fun parseFromDeptListOrThrow(entity: FacultyReadEntity): String {
        return  parser.encodeToString(value = entity, serializer = FacultyReadEntity.serializer())
    }
    override fun parseAsFacultyListOrThrow(faculties: List<FacultyReadEntity>): String {
        return  parser.encodeToString(value = faculties, serializer = ListSerializer(FacultyReadEntity.serializer()))
    }

    override fun parseAsDeptReadEntitiesOrThrow(json: String)=
        parser.decodeFromString(ListSerializer(DepartmentReadEntity.serializer()), json)


    override fun parseFromDeptListOrThrow(entities: List<DepartmentReadEntity>)=
        parser.encodeToString(value = entities, serializer = ListSerializer(DepartmentReadEntity.serializer()))

    override fun parseAsDeptOrThrow(json: String): String {
        val entity = parser.decodeFromString(DepartmentReadEntity.serializer(), json)
        return parser.encodeToString(DepartmentReadEntity.serializer(), entity)
    }

    override fun parseAsTeacherOrThrow(json: String): String {
        val entity = parser.decodeFromString(TeacherReadEntity.serializer(), json)
        return parser.encodeToString(TeacherReadEntity.serializer(), entity)
    }
    private fun tryParse(block:()->Boolean):Boolean{
        return try {
          block()
        }
        catch (e:Exception){
            false
        }
    }
}
