package academic.data.faculty.source.local

import faculty.domain.faculties.model.FacultyInfoModel

internal interface FacultyLocalDataSource{
    suspend fun addFaculties(models: List<FacultyInfoModel>)
    suspend fun getFaculties():Result<List<FacultyInfoModel>>
}
