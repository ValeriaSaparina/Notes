package com.example.notes.impl.usecase

import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.repository.NotesRepository
import com.example.notes.api.usecase.CreateNoteUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateNoteUseCaseImpl(
    private val repository: NotesRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CreateNoteUseCase {
    override suspend fun invoke(note: NoteDomainModel, isSync: Boolean): Result<String> {
        return runSuspendCatching {
            withContext(dispatcher) {
                repository.createNote(note, isSync)
            }
        }
    }
}