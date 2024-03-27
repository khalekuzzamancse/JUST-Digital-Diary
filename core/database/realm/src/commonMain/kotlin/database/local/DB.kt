package database.local

import database.local.schema.academic.DepartmentSchema
import database.local.schema.academic.DesignationSchema
import database.local.schema.academic.FacultySchema
import database.local.schema.academic.TeacherSchema
import database.local.schema.administration.OfficeSchema
import database.local.schema.administration.AdminOfficerSchema
import database.local.schema.administration.SubOfficeSchema
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

/*
This will define the database including all supported schemas and will internally expose the database instance.
This allows the module to define APIs using the database instance without exposing the instance publicly.
*/
internal object DB {
    private val configuration = RealmConfiguration.create(
        schema = setOf(
            FacultySchema::class,
            DepartmentSchema::class,
            TeacherSchema::class,
            DesignationSchema::class,
            OfficeSchema::class,
            SubOfficeSchema::class,
            AdminOfficerSchema::class,
        )
    )
    internal val db= Realm.open(configuration)

    /**
     * @param [S] for Schema
     * @param [E] for Entity
     */
     suspend fun <S : RealmObject, E> addEntity(entity: S, transform: S.() -> E): Result<E> {
        val job = CoroutineScope(Dispatchers.Default).async {
            try {
                val result: Result<E> = db.write {
                    val createdEntity = copyToRealm(entity)
                    Result.success(createdEntity.transform())
                }
                result
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
        return job.await()
    }

     fun <S : RealmObject, E> retrieveEntities(
        query: () -> RealmResults<S>,
        transform: (S) -> E
    ): Result<List<E>> {
        return try {
            val response = query()
            Result.success(response.distinct().map(transform))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }


}