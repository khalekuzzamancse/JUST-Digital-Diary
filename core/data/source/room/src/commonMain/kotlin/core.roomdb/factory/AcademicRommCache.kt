package core.roomdb.factory
import core.data.entity.academic.DepartmentReadEntity
import core.data.entity.academic.FacultyReadEntity
import core.data.entity.academic.TeacherReadEntity
import core.roomdb.dao.DepartmentDao
import core.roomdb.dao.FacultyDao
import core.roomdb.dao.FacultyMemberDao
import core.roomdb.schema.DepartmentSchema
import core.roomdb.schema.DepartmentSubSchema
import core.roomdb.schema.FacultyMemberSchema
import core.roomdb.schema.FacultySchema
import domain.api.AcademicCacheApi

class AcademicRommCache internal constructor(
    private val facultyDao: FacultyDao,
    private val departmentDao: DepartmentDao,
    private val teacherDao: FacultyMemberDao
) : AcademicCacheApi {

    override suspend fun readFacultiesOrThrow(): List<FacultyReadEntity> {
        val schemas = facultyDao.getAllFaculties()
        return schemas.map { schema ->
            FacultyReadEntity(
                priority = schema.priority,
                faculty_id = schema.facultyId,
                name = schema.name,
                number_of_dept = 0//TODO:Derived property, refactor later
            )
        }
    }

    override suspend fun insertFacultiesOrThrow(entities: List<FacultyReadEntity>) {
        facultyDao.upsertFaculties(
            entities.map { entity ->
                FacultySchema(
                    priority = entity.priority,
                    facultyId = entity.faculty_id,
                    name = entity.name,
                )
            }
        )
    }

    override suspend fun insertDeptsOrThrow(facultyId: String, entities: List<DepartmentReadEntity>) {
        departmentDao.upsertDepartments(
            entities.map { entity ->
                DepartmentSchema(
                    deptId = entity.dept_id,
                    id = entity.priority,
                    facultyId = facultyId,
                    name = entity.name,
                    shortname = entity.shortname
                )
            }
        )
    }

    override suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity> {
        val schemas = departmentDao.getDepartmentsByFaculty(facultyId)
        return schemas.map { schema ->
            DepartmentReadEntity(
                dept_id = schema.deptId,
                priority = schema.id,
                faculty_id = schema.facultyId,
                name = schema.name,
                shortname = schema.shortname,
                number_of_employee = 0//TODO:Derived property, refactor later
            )
        }
    }

    override suspend fun insertTeachersOrThrow(deptId: String, entities: List<TeacherReadEntity>) {
        teacherDao.upsertFacultyMembers(
            entities.map { entity ->
                FacultyMemberSchema(
                    id = entity.id,
                    deptId = entity.dept_id,
                    name = entity.name,
                    email = entity.email,
                    role = "NULL",
                    phone = entity.phone,
                    achievement = entity.achievements,
                    additionalEmail = entity.additional_email,
                    type = entity.priority,
                    profile = entity.image_link,
                    departments = listOf(
                        DepartmentSubSchema(
                            name = "NULL",
                            shortname = "NULL",
                            designation = entity.designations,
                            roomNo = entity.room_no,
                            present = 0
                        )
                    )
                )
            }
        )
    }

    override suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity> {
        val schemas = teacherDao.getFacultyMembersByDeptId(deptId)
        return schemas.map { schema ->
            TeacherReadEntity(
                id = schema.id,
                dept_id = schema.deptId,
                priority = schema.type,
                name = schema.name,
                email = schema.email ?: "",
                additional_email = schema.additionalEmail,
                phone = schema.phone ?: "",
                image_link = schema.profile,
                achievements = schema.achievement ?: "",
                designations = schema.departments.firstOrNull()?.designation ?: "",
                room_no = schema.departments.firstOrNull()?.designation
            )
        }
    }
}
