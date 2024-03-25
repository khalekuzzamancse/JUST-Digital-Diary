package queryservice.data.data_sources.remote.source

import queryservice.data.dto.EmployeeDTO

class RemoteDataSource {
    suspend fun getEmployees(): Result<List<EmployeeDTO>> {
        return Result.success(createDummyEmployeeList())
    }
}

private fun createDummyEmployeeList(): List<EmployeeDTO> {
    return listOf(
        EmployeeDTO(
            name = "John Doe",
            email = "john.doe@example.com",
            additionalEmail = "john.doe@example.com",
            profileImageLink = "https://example.com/john_doe_profile.jpg",
            achievements = "Employee of the Month, 2023",
            phone = "+1 (555) 123-4567",
            designations = "Senior Software Engineer",
            roomNo = "A-123"
        ),
        EmployeeDTO(
            name = "Jane Smith",
            email = "jane.smith@example.com",
            additionalEmail = "john.doe@example.com",
            profileImageLink = "https://example.com/jane_smith_profile.jpg",
            achievements = "Outstanding Team Leader Award",
            phone = "+1 (555) 987-6543",
            designations = "Project Manager",
            roomNo = "B-456"
        ),

        )
}

