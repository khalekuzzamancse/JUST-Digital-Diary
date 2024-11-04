package core.roomdb.factory

import core.roomdb.dao.DepartmentDao
import core.roomdb.dao.FacultyDao
import core.roomdb.dao.FacultyMemberDao
import core.roomdb.schema.DepartmentSchema
import core.roomdb.schema.DepartmentSubSchema
import core.roomdb.schema.FacultyMemberSchema
import core.roomdb.schema.FacultySchema
import core.roomdb.withExceptionHandle
import domain.api.AcademicApi
import core.data.entity.academic.*
import domain.factory.ContractFactory

class RoomAcademicApi internal  constructor(
    private val facultyDao: FacultyDao,
    private val departmentDao: DepartmentDao,
    private val teacherDao: FacultyMemberDao
):AcademicApi{
    private val feedbackService=ContractFactory.feedbackService()
    private val insertionService = ContractFactory.insertionService()
    private val readEntityService = ContractFactory.academicReadEntityService()

    override suspend fun readFaculties()=withExceptionHandle {
        val schemas = facultyDao.getAllFaculties()
        val entities =
            schemas.map { schema->
                FacultyReadEntity(
                    priority = schema.id,
                    faculty_id = schema.facultyId,
                    name = schema.name,
                    number_of_dept = schema.departmentCount
                )
            }

        readEntityService.parseAsFacultyListOrThrow(entities)
    }

    /**
     * - Since it do just caching(storing), it need not extra logic so pass the [FacultyReadEntity] list json
     * that is fetched from remote data source,passing directly [FacultyReadEntity] will reduce the
     * extra task such as converting to [FacultyWriteEntity]
     */
    override suspend fun insertFaculty(json: String)=withExceptionHandle {
            facultyDao.upsertFaculties( readEntityService
                .parseAsFacultyListOrThrow(json)
                .map { entity->
                    FacultySchema(
                        id = entity.priority,
                        facultyId = entity.faculty_id,
                        name = entity.name,
                        departmentCount = entity.number_of_dept
                    )
                })
       feedbackService.toFeedbackMessage("Faculty cache updated")
    }

    override suspend fun upsetFacultyList(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readFacultyById(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFaculty(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertDept(facultyId: String, json: String)= withExceptionHandle {
        departmentDao.upsertDepartments(readEntityService
            .parseAsDeptReadEntitiesOrThrow(json)
            .map { entity->
                DepartmentSchema(
                    deptId = entity.dept_id,
                    id = entity.priority,
                    facultyId = facultyId,
                    name = entity.name,
                    membersCount = entity.number_of_employee,
                    shortname = entity.shortname
                )
            })
        feedbackService.toFeedbackMessage("Department cache updated")
    }

    override suspend fun readAllDept(): String {
        TODO("Not yet implemented")
    }

    override suspend fun readDeptsUnderFaculty(facultyId: String): String {
        val schemas = departmentDao.getDepartmentsByFaculty(facultyId)
        val entities =
            schemas.map { schema->
                DepartmentReadEntity(
                    dept_id = schema.deptId,
                    priority = schema.id,
                    faculty_id = schema.facultyId,
                    name = schema.name,
                    shortname = schema.shortname,
                    number_of_employee = schema.membersCount
                )
            }
      return  readEntityService.parseFromDeptListOrThrow(entities)
    }

    override suspend fun readDeptById(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartment(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacher(deptId: String, json: String)= withExceptionHandle {
        teacherDao.upsertFacultyMembers(readEntityService
            .parseAsTeacherReadEntitiesOrThrow(json)
            .map { entity->
                FacultyMemberSchema(
                  uid = entity.id,
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
                            present = 0)
                    )

                )
            })
        feedbackService.toFeedbackMessage("Teacher cache updated")
    }

    override suspend fun readAllTeacher(): String {
        TODO("Not yet implemented")
    }

    override suspend fun readTeachersUnderDept(deptId: String)= withExceptionHandle {
        val schemas = teacherDao.getFacultyMembersByDeptId(deptId)
        val entities =
            schemas.map { schema->
                TeacherReadEntity(
                    id = schema.uid,
                    dept_id = schema.deptId,
                    priority = schema.type,
                    name = schema.name,
                    email = schema.email?:"",
                    additional_email = schema.additionalEmail,
                    phone = schema.phone?:"",
                    image_link = schema.profile,
                    achievements = schema.achievement?:"",
                    designations = schema.departments.firstOrNull()?.designation?:"",
                    room_no = schema.departments.firstOrNull()?.designation,
                )
            }
          readEntityService.toJsonOrThrow(entities)

    }

    override suspend fun readTeacherById(teacherId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateFaculty(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateDept(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeacher(teacherId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeacher(id: String): String {
        TODO("Not yet implemented")
    }
}