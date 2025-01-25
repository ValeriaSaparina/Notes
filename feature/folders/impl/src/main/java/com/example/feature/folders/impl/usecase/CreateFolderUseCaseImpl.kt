package com.example.feature.folders.impl.usecase

import com.example.feature.folders.api.repository.FoldersRepository
import com.example.feature.folders.api.usecase.CreateFolderUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateFolderUseCaseImpl(
    private val repository: FoldersRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CreateFolderUseCase {
    override suspend fun invoke(folderName: String): Result<Long> {
        return runSuspendCatching {
            withContext(dispatcher) { repository.createFolder(folderName) }
        }
    }
}