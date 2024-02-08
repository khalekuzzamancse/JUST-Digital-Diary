package com.just.cse.digital_diary.two_zero_two_three.layers.domain.notes.model


interface NoteModelResponse {
    data class Success(val data: List<NoteModel>): NoteModelResponse
    data class Failure(val reason: String?): NoteModelResponse
}