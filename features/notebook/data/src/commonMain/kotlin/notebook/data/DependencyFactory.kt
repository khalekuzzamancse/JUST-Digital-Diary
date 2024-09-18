package notebook.data

import notebook.data.repository.NotesRepositoryImpl
import notebook.data.sources.local.LocalDataSource
import notebook.data.sources.local.LocalDataSourceImpl
import notebook.domain.repository.NotesRepository

object DependencyFactory {
    /**
     * Should not access from the outside or client model
     */
  internal  fun localDataSource(): LocalDataSource = LocalDataSourceImpl()

    /**
     * Client does not need to change on change in the different implementation of repository
     */
    fun repository():NotesRepository=NotesRepositoryImpl()
}