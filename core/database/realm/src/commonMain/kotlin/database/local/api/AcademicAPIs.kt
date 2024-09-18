package database.local.api

import database.local.DB
import database.local.DB.addEntity
import database.local.DB.retrieveEntities
import database.local.schema.academic.DepartmentEntityLocal
import database.local.schema.academic.DepartmentSchema
import database.local.schema.academic.DesignationSchema
import database.local.schema.academic.FacultyEntityLocal
import database.local.schema.academic.FacultySchema
import database.local.schema.academic.TeacherEntityLocal
import database.local.schema.academic.TeacherSchema
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

object AcademicAPIs {

    private val db = DB.db


    // Faculty Related APIs
    suspend fun addFaculty(model: FacultyEntityLocal): Result<FacultyEntityLocal> {
        val result = addEntity(FacultySchema().apply {
            this.id = model.id
            this.facultyId = model.facultyId
            this.name = model.name
            this.departmentCount = model.deptCount
        }) { this.toEntity() }
        return result
    }

    suspend fun addFaculties(models: List<FacultyEntityLocal>) {
        models.map { model -> CoroutineScope(Dispatchers.Default).async { addFaculty(model) } }
            .awaitAll()
    }


    // Department Related APIs
    suspend fun addDepartments(facultyId: String, models: List<DepartmentEntityLocal>) =
        models.map { model ->
            CoroutineScope(Dispatchers.Default).async { addDepartment(facultyId, model) }
        }.awaitAll()

    suspend fun addDepartment(facultyId: String, model: DepartmentEntityLocal) =
        addEntity(DepartmentSchema().apply {
            this.employeeCount = model.employeeCount
            this.deptId = model.deptId
            this.name = model.name
            this.shortname = model.shortname
            this.facultyId = facultyId
        }) { this.toEntity() }

    // Teacher Related APIs
    suspend fun addTeacher(model: TeacherEntityLocal) =
        addEntity(TeacherSchema().apply {
            this.uid = model.uid
            this.achievement = model.achievement
            this.name = model.name
            this.email = model.email
            this.additionalEmail = model.additionalEmail
            this.phone = model.phone
            this.profileImage = model.profileImage
            this.roomNo = model.roomNo
            this.designations.clear()
            model.designations.forEach { designation ->
                this.designations.add(DesignationSchema().apply {
                    this.name = designation.name
                })
            }
            this.deptId = model.deptId
            this.departmentName = model.departmentName
            this.shortname = model.shortName
        }) { this.toEntity() }

    suspend fun addTeachers(models: List<TeacherEntityLocal>) {
        models.map { model -> CoroutineScope(Dispatchers.Default).async { addTeacher(model) } }
            .awaitAll()
    }
    //
    //
    //For retrieve

    fun retrieveFaculties(): Result<List<FacultyEntityLocal>> {
        return retrieveEntities(
            query = { db.query<FacultySchema>().find() },
            transform = FacultySchema::toEntity
        )
    }

    fun retrieveDepartments(facultyId: String): Result<List<DepartmentEntityLocal>> {
        val res: Result<List<DepartmentEntityLocal>> = retrieveEntities(
            query = {
                db.query<DepartmentSchema>("facultyId=$0", facultyId).find()
            },
            transform = DepartmentSchema::toEntity
        )
       return res
    }



    fun retrieveTeachers(deptId: String): Result<List<TeacherEntityLocal>> {
        return retrieveEntities(
            query = { db.query<TeacherSchema>("deptId=$0", deptId).find() },
            transform = TeacherSchema::toEntity
        )
    }
}

