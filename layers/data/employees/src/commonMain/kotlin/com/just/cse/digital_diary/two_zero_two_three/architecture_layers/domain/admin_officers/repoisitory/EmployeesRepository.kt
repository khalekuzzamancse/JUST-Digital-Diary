package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.data_sources.remote.entity.EmployeeEntity
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.data_sources.remote.source.RemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.EmployeeModel

class EmployeesRepositoryImpl : EmployeesRepository {
    override suspend fun getEmployees(): Result<List<EmployeeModel>> {
        val res = RemoteDataSource().getEmployees()
        return if (res.isSuccess) {
            Result.success(res.getOrDefault(emptyList()).map { it.toModel() })
        } else Result.failure(Throwable("Can not fetch"))
    }


}