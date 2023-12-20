package com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.data

import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.model.DepartmentMessage
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_employee_list_repoisitory.data.EmployeeRepository

object DepartmentInfoRepository {
    fun getDepartmentMessage(departmentId: String): DepartmentMessage {
        return DepartmentMessage(
            departmentId = departmentId,
            deptImageUrl = "https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
            message = "I warmly welcome you to the Department of Computer Science and Engineering (CSE) at Jashore " +
                    "University of Science and Technology (JUST). JUST started its journey on June 10, 2009 with four " +
                    "departments among which the department of CSE was the first department. Since then," +
                    " CSE has been widely recognized for its excellent research and teaching capabilities. " +
                    "The department provides an outstanding opportunity to the students to get quality " +
                    "education in Computer Science and Engineering. " +
                    "The graduates from JUST-CSE are heavily recruited by both acade..."

        )
    }
    suspend fun getTeacherList(departmentId: String)= EmployeeRepository.getEmployees(departmentId)
  suspend  fun getStaffList(departmentId: String)= EmployeeRepository.getEmployees(departmentId)
}