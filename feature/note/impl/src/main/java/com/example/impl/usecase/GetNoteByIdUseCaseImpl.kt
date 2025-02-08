package com.example.impl.usecase

import com.example.api.repository.NoteRepository
import com.example.api.usecase.GetNoteByIdUseCase
import com.example.notes.api.model.NoteDomainModel
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNoteByIdUseCaseImpl(
    private val repository: NoteRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GetNoteByIdUseCase {
    override suspend fun invoke(noteId: String): Result<NoteDomainModel> {
        return runSuspendCatching {
            withContext(dispatcher) {
                repository.getNoteById(noteId)
            }
        }
    }
}