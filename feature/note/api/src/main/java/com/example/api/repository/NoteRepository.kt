package com.example.api.repository

import com.example.notes.api.model.NoteDomainModel

interface NoteRepository {

    suspend fun updateNote(note: NoteDomainModel)

    suspend fun getNoteById(noteId: Long) : NoteDomainModel

}
