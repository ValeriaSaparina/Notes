package com.example.impl.datasource.local

import com.example.api.datasource.local.LocalNoteDataSource
import com.example.db.room.dao.NotesDao
import com.example.db.room.entity.NoteEntity
import com.example.db.room.entity.NoteWithFolderEntity

class RoomNoteDataSource(
    private val notesDao: NotesDao,
) : LocalNoteDataSource {
    override suspend fun saveNote(note: NoteEntity) {
        notesDao.updateNote(note)
    }

    override suspend fun getNoteById(noteId: Long): NoteWithFolderEntity {
        return notesDao.getNoteById(noteId)
    }
}