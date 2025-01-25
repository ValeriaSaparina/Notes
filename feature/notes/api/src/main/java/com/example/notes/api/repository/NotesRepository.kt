package com.example.notes.api.repository

import com.example.notes.api.model.NoteDomainModel

interface NotesRepository {
    suspend fun getNotesByFolderId(folderId: Long): List<NoteDomainModel>
    suspend fun getNoteById(noteId: Long): NoteDomainModel
    suspend fun createNote(note: NoteDomainModel, isSync: Boolean): Long
}