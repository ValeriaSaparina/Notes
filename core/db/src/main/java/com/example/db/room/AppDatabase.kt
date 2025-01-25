package com.example.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.db.room.dao.FoldersDao
import com.example.db.room.dao.NotesDao
import com.example.db.room.entity.FolderEntity
import com.example.db.room.entity.NoteEntity

@Database(
    version = 1,
    entities = [FolderEntity::class, NoteEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foldersDao(): FoldersDao
    abstract fun notesDao(): NotesDao
}