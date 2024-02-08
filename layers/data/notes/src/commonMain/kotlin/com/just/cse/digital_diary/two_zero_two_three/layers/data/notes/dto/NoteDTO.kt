package com.just.cse.digital_diary.two_zero_two_three.layers.data.notes.dto

import com.just.cse.digital_diary.two_zero_two_three.layers.domain.notes.model.NoteModel

data class NoteDTO(
    val title:String,
    val description:String,
    val timeStamp:String
){
    fun toModel()=NoteModel(title, description, timeStamp)
}
