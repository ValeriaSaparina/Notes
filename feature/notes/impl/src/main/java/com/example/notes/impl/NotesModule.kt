package com.example.notes.impl

import com.example.notes.api.datasource.LocalNotesDataSource
import com.example.notes.api.repository.NotesRepository
import com.example.notes.api.usecase.CreateNoteUseCase
import com.example.notes.api.usecase.GetNotesByFolderIdUseCase
import com.example.notes.impl.datasource.local.RoomNotesDataSource
import com.example.notes.impl.presentation.NotesViewModel
import com.example.notes.impl.repository.NotesRepositoryImpl
import com.example.notes.impl.usecase.CreateNoteUseCaseImpl
import com.example.notes.impl.usecase.GetNotesByFolderIdUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule = module {
    single<NotesRepository> { NotesRepositoryImpl(get()) }
    single<LocalNotesDataSource> { RoomNotesDataSource(get()) }

    single<CoroutineDispatcher> { Dispatchers.IO }

    factory<GetNotesByFolderIdUseCase> { GetNotesByFolderIdUseCaseImpl(get(), get()) }
    factory<CreateNoteUseCase> { CreateNoteUseCaseImpl(get(), get()) }

    viewModel { (folderId: Long) -> NotesViewModel(get(), get(), folderId) }
}