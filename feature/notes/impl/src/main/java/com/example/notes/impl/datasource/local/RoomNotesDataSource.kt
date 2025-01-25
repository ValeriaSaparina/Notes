package com.example.notes.impl.datasource.local

import com.example.db.room.dao.NotesDao
import com.example.db.room.entity.NoteEntity
import com.example.db.room.entity.NoteWithFolderEntity
import com.example.notes.api.datasource.LocalNotesDataSource

class RoomNotesDataSource(
    private val notesDao: NotesDao
) : LocalNotesDataSource {
    override suspend fun getNotesByFolderId(folderId: Long): List<NoteWithFolderEntity> {
        return notesDao.getNotesByFolderId(folderId)
    }

    override suspend fun getNoteById(noteId: Long): NoteWithFolderEntity {
        return notesDao.getNoteById(noteId)
    }

    override suspend fun createNote(note: NoteEntity): Long {
        return notesDao.createNote(note)
    }
}