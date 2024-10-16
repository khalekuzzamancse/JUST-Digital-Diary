package faculty.domain.model.public_

import common.docs.domain_layer.ModelDoc

/**
 * Further discussion on:
- `Model`: see [ModelDoc]
 */
data class FacultyModel(
    val facultyId: String,
    val name: String,
    val departmentsCount: Int
)