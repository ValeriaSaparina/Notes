package com.example.notes.api.model


data class NoteDomainModel(
    val id: Long,
    val title: String,
    val text: String,
    val createDate: Long,
    val editDate: Long,
    val folder: FolderModel,
)
