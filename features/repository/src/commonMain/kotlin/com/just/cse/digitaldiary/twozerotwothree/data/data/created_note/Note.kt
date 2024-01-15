package com.just.cse.digitaldiary.twozerotwothree.data.data.created_note

import java.util.UUID

data class Note (
    val title:String,
    val description:String,
    val timeString: String,
    val creatorName:String,
    val creatorImageUrl:String,
    val creatorMoreInfo:String,
    val id:String=UUID.randomUUID().toString()
)