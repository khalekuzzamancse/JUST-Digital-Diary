package faculty.domain.model

import common.docs.domain_layer.ModelDoc

/**
 * Further discussion on:
- `Model`: see [ModelDoc]
 */
data class FacultyModel(
    val id: Int,
    val facultyId: String,
    val name: String,
    val departmentsCount: Int
)
