package core.database.realm.academic

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

object AcademicLocalDataStore {
    private val configuration = RealmConfiguration.create(
        schema = setOf(
            FacultyEntityRealm::class,
            DepartmentInfoEntityRealm::class,
            TeacherEntityRealm::class,
            DesignationRealm::class // Add this line
        )
    )

    private val realm = Realm.open(configuration)
    suspend fun addFaculties(models: List<FacultyLocalModel>) {
        models.forEach { model ->
            addFaculty(model)
        }
    }

    suspend fun addFaculty(model: FacultyLocalModel): Result<FacultyLocalModel> {
        val entity = FacultyEntityRealm().apply {
            this.id = model.id
            this.faculty_id = model.facultyId
            this.name = model.name
            this.department_count = model.deptCount
        }
        val job: Deferred<Result<FacultyLocalModel>> = CoroutineScope(Dispatchers.Default).async {
            try {
                val result: Result<FacultyLocalModel> = realm.write {
                    val createdEntity = copyToRealm(entity)
                    Result.success(createdEntity.toModel())
                }
                //  realm.close() don't close ,because multiple can be added
                result
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
        return job.await()

    }

    suspend fun addDepartments(facultyId: String, models: List<DepartmentEntityLocalModel>) {
        models.forEach { model ->
            addDepartment(facultyId, model)
        }
    }

    suspend fun addDepartment(
        facultyId: String,
        model: DepartmentEntityLocalModel
    ): Result<DepartmentEntityLocalModel> {
        val entity = DepartmentInfoEntityRealm().apply {
            this.employeeCount = model.employeeCount
            this.dept_id = model.deptId
            this.name = model.name
            this.shortname = model.shortname
            this.facultyId = facultyId
        }
        val job: Deferred<Result<DepartmentEntityLocalModel>> =
            CoroutineScope(Dispatchers.Default).async {
                try {
                    val result: Result<DepartmentEntityLocalModel> = realm.write {
                        val createdEntity = copyToRealm(entity)
                        Result.success(createdEntity.toModel())
                    }
                    //  realm.close() don't close ,because multiple can be added
                    result
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }
        return job.await()

    }
    suspend fun addTeachers(models: List<TeacherLocalModel>) {
        models.forEach { model ->
            addTeacher(model)
        }
    }

    suspend fun addTeacher(model: TeacherLocalModel): Result<TeacherLocalModel> {
        val entity = TeacherEntityRealm().apply {
            this.uid = model.uid
            this.achievement = model.achievement
            this.name = model.name
            this.email = model.email
            this.additional_email = model.additionalEmail
            this.phone = model.phone
            this.profile_image = model.profileImage
            this.room_no = model.roomNo
            // Convert each DesignationLocalModel to DesignationRealm and add it to the RealmList
            this.designations.clear()
            model.designations.forEach { designation ->
                this.designations.add(DesignationRealm().apply {
                    this.name = designation.name
                })
            }
            this.dept_id = model.deptId
            this.department_name = model.departmentName
            this.shortname = model.shortName
        }

        val job: Deferred<Result<TeacherLocalModel>> = CoroutineScope(Dispatchers.Default).async {
            try {
                val result: Result<TeacherLocalModel> = realm.write {
                    val createdEntity = copyToRealm(entity)
                    Result.success(createdEntity.toModel())
                }
                //  realm.close() don't close ,because multiple can be added
                result
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
        return job.await()

    }

    fun retrieveFaculties(): Result<List<FacultyLocalModel>> {
        return try {
            val response: RealmResults<FacultyEntityRealm> =
                realm.query<FacultyEntityRealm>().find()
            Result.success(response.distinct().map { it.toModel() })

        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    fun retrieveDepartments(facultyId: String): Result<List<DepartmentEntityLocalModel>> {
        return try {
            val response =
                realm.query<DepartmentInfoEntityRealm>().find().filter { it.facultyId == facultyId }
            Result.success(response.distinct().map { it.toModel() })

        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    fun retrieveTeachers(deptId: String): Result<List<TeacherLocalModel>> {
        return try {
            val response = realm.query<TeacherEntityRealm>().find().filter { it.dept_id == deptId }
            Result.success(response.distinct().map { it.toModel() })

        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }


}
/*
Save info  on sign in ,until sign out
 */