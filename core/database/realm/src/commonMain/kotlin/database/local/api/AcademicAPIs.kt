package database.local.api

import database.local.db.DB
import database.local.db.DB.addEntity
import database.local.db.DB.retrieveEntities
import database.local.schema.DepartmentEntityLocal
import database.local.schema.DepartmentSchema
import database.local.schema.DesignationSchema
import database.local.schema.FacultyEntityLocal
import database.local.schema.FacultySchema
import database.local.schema.TeacherEntityLocal
import database.local.schema.TeacherSchema
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

//object AcademicAPIs {
//
//
//    private val db = DB.db
//
//    //
//    //Faculty Related APIs
//    suspend fun addFaculty(model: FacultyEntity): Result<FacultyEntity> {
//        val entity = FacultySchema().apply {
//            this.id = model.id
//            this.facultyId = model.facultyId
//            this.name = model.name
//            this.departmentCount = model.deptCount
//        }
//        val job: Deferred<Result<FacultyEntity>> = CoroutineScope(Dispatchers.Default).async {
//            try {
//                val result: Result<FacultyEntity> = db.write {
//                    val createdEntity = copyToRealm(entity)
//                    Result.success(createdEntity.toEntity())
//                }
//                //  realm.close() don't close ,because multiple can be added
//                result
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//        }
//        return job.await()
//
//    }
//
//    suspend fun addFaculties(models: List<FacultyEntity>) {
//        models.forEach { model ->
//            addFaculty(model)
//        }
//    }
//
//    fun retrieveFaculties(): Result<List<FacultyEntity>> {
//        return try {
//            val response: RealmResults<FacultySchema> =
//                db.query<FacultySchema>().find()
//            Result.success(response.distinct().map { it.toEntity() })
//
//        } catch (ex: Exception) {
//            Result.failure(ex)
//        }
//
//    }
//
//    //
//    //Department Related APIs
//    suspend fun addDepartments(facultyId: String, models: List<DepartmentEntity>) {
//        models.forEach { model ->
//            addDepartment(facultyId, model)
//        }
//    }
//
//    suspend fun addDepartment(
//        facultyId: String,
//        model: DepartmentEntity
//    ): Result<DepartmentEntity> {
//        val entity = DepartmentSchema().apply {
//            this.employeeCount = model.employeeCount
//            this.deptId = model.deptId
//            this.name = model.name
//            this.shortname = model.shortname
//            this.facultyId = facultyId
//        }
//        val job: Deferred<Result<DepartmentEntity>> =
//            CoroutineScope(Dispatchers.Default).async {
//                try {
//                    val result: Result<DepartmentEntity> = db.write {
//                        val createdEntity = copyToRealm(entity)
//                        Result.success(createdEntity.toEntity())
//                    }
//                    //  realm.close() don't close ,because multiple can be added
//                    result
//                } catch (e: Exception) {
//                    Result.failure(e)
//                }
//            }
//        return job.await()
//
//    }
//
//    //
//    //Department Related APIs
//    suspend fun addTeacher(model: TeacherEntity): Result<TeacherEntity> {
//        val entity = TeacherSchema().apply {
//            this.uid = model.uid
//            this.achievement = model.achievement
//            this.name = model.name
//            this.email = model.email
//            this.additionalEmail = model.additionalEmail
//            this.phone = model.phone
//            this.profileImage = model.profileImage
//            this.roomNo = model.roomNo
//            // Convert each DesignationLocalModel to DesignationRealm and add it to the RealmList
//            this.designations.clear()
//            model.designations.forEach { designation ->
//                this.designations.add(DesignationSchema().apply {
//                    this.name = designation.name
//                })
//            }
//            this.deptId = model.deptId
//            this.departmentName = model.departmentName
//            this.shortname = model.shortName
//        }
//
//        val job: Deferred<Result<TeacherEntity>> = CoroutineScope(Dispatchers.Default).async {
//            try {
//                val result: Result<TeacherEntity> = db.write {
//                    val createdEntity = copyToRealm(entity)
//                    Result.success(createdEntity.toEntity())
//                }
//                //  realm.close() don't close ,because multiple can be added
//                result
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//        }
//        return job.await()
//
//    }
//
//    suspend fun addTeachers(models: List<TeacherEntity>) {
//        models.forEach { model ->
//            addTeacher(model)
//        }
//    }
//
//
//    fun retrieveDepartments(facultyId: String): Result<List<DepartmentEntity>> {
//        return try {
//            val response =
//                db.query<DepartmentSchema>().find().filter { it.facultyId == facultyId }
//            Result.success(response.distinct().map { it.toEntity() })
//
//        } catch (ex: Exception) {
//            Result.failure(ex)
//        }
//
//    }
//
//    fun retrieveTeachers(deptId: String): Result<List<TeacherEntity>> {
//        return try {
//            val response = db.query<TeacherSchema>().find().filter { it.deptId == deptId }
//            Result.success(response.distinct().map { it.toEntity() })
//
//        } catch (ex: Exception) {
//            Result.failure(ex)
//        }
//
//    }
//
//
//}
