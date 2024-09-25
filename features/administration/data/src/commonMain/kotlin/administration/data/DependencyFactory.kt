package administration.data

import administration.data.offices.sources.local.OfficeLocalDataSource
import administration.data.offices.sources.local.OfficeLocalDataSourceImpl
import administration.data.offices.sources.remote.OfficeListRemoteDataSourceImpl
import administration.data.offices.sources.remote.OfficeRemoteDataSource
import administration.data.sub_office.data_sources.remote.SubOfficeRemoteDataSource
import administration.data.sub_office.data_sources.remote.SubOfficeRemoteDataSourceImpl

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
    fun officeRemoteDataSource(): OfficeRemoteDataSource = OfficeListRemoteDataSourceImpl()
    fun officeLocalDataSource(): OfficeLocalDataSource = OfficeLocalDataSourceImpl()
    fun subOfficeRemoteDataSource(officeId:String): SubOfficeRemoteDataSource = SubOfficeRemoteDataSourceImpl(officeId)
//    fun  subOfficeLocalDataSource(): SubOfficeLocalDataSource = SubOfficeLocalDataSourceImpl()

}