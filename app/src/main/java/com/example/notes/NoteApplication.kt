package com.example.notes

import android.app.Application
import com.example.auth.impl.authModule
import com.example.db.room.dbModule
import com.example.feature.folders.impl.foldersModule
import com.example.impl.noteModule
import com.example.notes.impl.notesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
            modules(authModule, foldersModule, notesModule, noteModule, dbModule)
        }
    }
}