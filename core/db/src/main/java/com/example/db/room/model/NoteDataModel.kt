package com.example.db.room.model

data class NoteDataModel(
    val id: Long = -1,
    val title: String,
    val text: String,
    val createDate: Long,
    val editDate: Long,
    val folderId: Long,
)