package com.example.notes.impl.usecase

import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.repository.NotesRepository
import com.example.notes.api.usecase.GetNotesByFolderIdUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNotesByFolderIdUseCaseImpl(
    private val repository: NotesRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GetNotesByFolderIdUseCase {
    override suspend fun invoke(folderId: String): Result<List<NoteDomainModel>> {
        return runSuspendCatching {
            withContext(dispatcher) {
                repository.getNotesByFolderId(folderId)
            }
        }
    }
}