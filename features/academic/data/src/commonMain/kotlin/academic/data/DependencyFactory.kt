package academic.data

import academic.data.department.source.local.DepartmentLocalDataSource
import academic.data.department.source.local.DepartmentLocalDataSourceImpl
import academic.data.department.source.remote.DepartmentRemoteDataSource
import academic.data.department.source.remote.DepartmentRemoteDataSourceImpl
import academic.data.faculty.source.local.FacultyLocalDataSource
import academic.data.faculty.source.local.FacultyLocalDataSourceImpl
import academic.data.faculty.source.remote.FacultyFacultyRemoteDataSourceImpl
import academic.data.faculty.source.remote.FacultyRemoteDataSource
import academic.data.teacher.sources.local.TeacherLocalDataSource
import academic.data.teacher.sources.local.TeacherLocalDataSourceImpl
import academic.data.teacher.sources.remote.TeacherRemoteDataSource
import academic.data.teacher.sources.remote.TeacherRemoteDataSourceImpl

/**
 * Used to for dependency injection,so that we manage single place of instance creation
 * * more ever some class can be hidden from client module by using the factory pattern.
 * but if we pass the dependency via contractor ,then the we can not make the
 * dependency private from the client,example as
 * *  class FacultyListRepositoryImpl(
 *  *    private val localSource: FacultyLocalDataSource = FacultyLocalDataSourceImpl(),
 *   *   private val remoteSource: RemoteDataSource=FacultyRemoteDataSourceImpl()
 * *  ) : FacultyListRepository
 *
 * * here we want to make the FacultyLocalDataSource,FacultyLocalDataSourceImpl and
 * RemoteDataSource ,FacultyRemoteDataSourceImpl() as internal to the module,but
 * if we do the constructor or function inject injection
 * then these must be public to the client,note that the client is note going to uses these,
 * it need only the FacultyListRepositoryImpl.
 * so making these dependency public is unnecessary and make the client code complex,
 * because now the client need to pass these dependency to create this FacultyListRepositoryImpl.
 *
 * More ever we need must need to use the abstraction of Local and Remote data source  because
 * we may want to pass different implementation of data source.
 *
 * in that case the Factory is best choice.
 * because if we use the factory then:
 * Single place of object creation
 *
 *
 *
 *
 *
 */
internal object DependencyFactory {
    fun facultyRemoteDataSource(): FacultyRemoteDataSource = FacultyFacultyRemoteDataSourceImpl()
    fun facultyLocalDataSource(): FacultyLocalDataSource = FacultyLocalDataSourceImpl()
    fun departmentLocalDataSource(): DepartmentLocalDataSource = DepartmentLocalDataSourceImpl()
    fun departmentRemoteDataSource(facultyId:String): DepartmentRemoteDataSource =
        DepartmentRemoteDataSourceImpl(facultyId)
    fun teacherLocalDataSource(): TeacherLocalDataSource = TeacherLocalDataSourceImpl()
    fun teacherRemoteDataSource(deptId:String): TeacherRemoteDataSource =
        TeacherRemoteDataSourceImpl(deptId)
}