package notebook.data.dto

import notebook.domain.model.NoteModel


data class NoteDTO(
    val title:String,
    val description:String,
    val timeStamp:String
){
    fun toModel()= NoteModel(title, description, timeStamp)
}
