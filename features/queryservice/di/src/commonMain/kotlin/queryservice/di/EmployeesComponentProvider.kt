package queryservice.di

import queryservice.data.repoisitory.EmployeesRepositoryImpl

object EmployeesComponentProvider {
    fun getEmployeeRepository(): EmployeesRepositoryImpl {
        return EmployeesRepositoryImpl()
    }
}