package com.example.impl

import com.example.api.datasource.local.LocalNoteDataSource
import com.example.api.repository.NoteRepository
import com.example.api.usecase.GetNoteByIdUseCase
import com.example.api.usecase.UpdateNoteUseCase
import com.example.impl.datasource.local.RoomNoteDataSource
import com.example.impl.presentation.NoteViewModel
import com.example.impl.repository.NoteRepositoryImpl
import com.example.impl.usecase.GetNoteByIdUseCaseImpl
import com.example.impl.usecase.UpdateNoteUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteModule = module {
    viewModel { (folderId: String, noteId: String) ->
        NoteViewModel(
            get(),
            get(),
            folderId,
            noteId
        )
    }
    single<NoteRepository> { NoteRepositoryImpl(get(), get()) }
    single<LocalNoteDataSource> { RoomNoteDataSource(get()) }

    single<CoroutineDispatcher> { Dispatchers.IO }

    factory<GetNoteByIdUseCase> { GetNoteByIdUseCaseImpl(get(), get()) }
    factory<UpdateNoteUseCase> { UpdateNoteUseCaseImpl(get(), get()) }
}