package com.example.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.db.room.entity.NoteEntity
import com.example.db.room.entity.NoteWithFolderEntity

@Dao
interface NotesDao {

    @Transaction
    @Query("SELECT * FROM notes WHERE folder_id = :folderId")
    fun getNotesByFolderId(folderId: Long): List<NoteWithFolderEntity>

    @Transaction
    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Long): NoteWithFolderEntity

    @Insert
    fun createNote(note: NoteEntity): Long
}