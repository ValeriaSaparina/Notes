package com.example.impl.usecase

import com.example.api.repository.NoteRepository
import com.example.api.usecase.UpdateNoteUseCase
import com.example.notes.api.model.NoteDomainModel
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateNoteUseCaseImpl(
    private val repository: NoteRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) :
    UpdateNoteUseCase {
    override suspend fun invoke(note: NoteDomainModel): Result<Unit> {
        return runSuspendCatching {
            withContext(coroutineDispatcher) { repository.updateNote(note) }
        }
    }

}
