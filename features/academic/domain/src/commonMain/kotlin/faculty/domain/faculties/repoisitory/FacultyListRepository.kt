package faculty.domain.faculties.repoisitory

import faculty.domain.faculties.model.FacultyInfoModel

interface FacultyListRepository{
    suspend fun getFaculties(): Result<List<FacultyInfoModel>>

}