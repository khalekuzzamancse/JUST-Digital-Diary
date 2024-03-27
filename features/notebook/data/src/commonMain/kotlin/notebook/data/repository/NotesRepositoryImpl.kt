package notebook.data.repository

import database.local.schema.notebook.NoteEntityLocal
import notebook.data.DependencyFactory
import notebook.domain.model.NoteModel
import notebook.domain.repository.NotesRepository

class NotesRepositoryImpl : NotesRepository {
    private val localSource = DependencyFactory.localDataSource()
    override suspend fun addNote(model: NoteModel): Result<Unit> {
        return localSource.addNote(model.toEntity())
    }

    override suspend fun getNotes(): Result<List<NoteModel>> {
        val res = localSource.getNotes()
        return if (res.isSuccess) Result.success(res.getOrDefault(emptyList()).map(::toModel))
        else Result.failure(Throwable("Failed to Load List:NotesRepositoryImpl\nContact to developer"))
    }
}

private fun NoteModel.toEntity() = NoteEntityLocal(
    id = title + timeStamp,
    title = title,
    description = description,
    timeStamp = timeStamp
)

private fun toModel(entity: NoteEntityLocal) = NoteModel(
    title = entity.title,
    description = entity.description,
    timeStamp = entity.timeStamp,
    id = entity.id
)