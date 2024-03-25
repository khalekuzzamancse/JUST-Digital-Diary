package queryservice.domain.repoisitory

import queryservice.domain.model.EmployeeModel

interface EmployeesRepository{
    suspend fun getEmployees():Result<List<EmployeeModel>>

}