package queryservice.data.repoisitory

import queryservice.data.data_sources.remote.source.RemoteDataSource
import queryservice.domain.model.EmployeeModel
import queryservice.domain.repoisitory.EmployeesRepository

class EmployeesRepositoryImpl : EmployeesRepository {
    override suspend fun getEmployees(): Result<List<EmployeeModel>> {
        val res = RemoteDataSource().getEmployees()
        return if (res.isSuccess) {
            Result.success(res.getOrDefault(emptyList()).map { it.toModel() })
        } else Result.failure(Throwable("Can not fetch"))
    }


}