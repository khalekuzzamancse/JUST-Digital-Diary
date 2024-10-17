package faculty.domain.model

import common.docs.ModelDoc

/**
 * Further discussion on:
- `Model`: see [ModelDoc]
* @property priority need by admin while editing so that can edit the priority
 */
data class FacultyReadModel(
    val facultyId: String,
    val name: String,
    val departmentsCount: Int,
    val priority:Int,
)
