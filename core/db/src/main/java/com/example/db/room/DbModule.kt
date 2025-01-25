package com.example.db.room

import android.app.Application
import androidx.room.Room
import com.example.db.room.dao.FoldersDao
import com.example.db.room.dao.NotesDao
import org.koin.dsl.module

fun provideDb(application: Application): AppDatabase =
    Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "note_app"
    ).build()

fun provideFoldersDao(db: AppDatabase): FoldersDao = db.foldersDao()
fun provideNotesDao(db: AppDatabase): NotesDao = db.notesDao()

val dbModule = module {
    single { provideDb(get()) }
    single { provideFoldersDao(get()) }
    single { provideNotesDao(get()) }
}