package schedule.data.mapper

import schedule.data.entity.DepartmentEntity
import schedule.domain.model.DepartmentModel

internal object ModelMapper {
    fun DepartmentEntity.toModel()=DepartmentModel(
        deptId=deptId,
        name=name,
        shortname=shortname,
    )
}