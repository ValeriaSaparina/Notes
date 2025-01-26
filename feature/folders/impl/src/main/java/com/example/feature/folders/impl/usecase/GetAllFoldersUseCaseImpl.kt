package com.example.feature.folders.impl.usecase

import com.example.feature.folders.api.model.FoldersListModel
import com.example.feature.folders.api.repository.FoldersRepository
import com.example.feature.folders.api.usecase.GetAllFoldersUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllFoldersUseCaseImpl(
    private val repository: FoldersRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetAllFoldersUseCase {
    override suspend fun invoke(): Result<List<FoldersListModel>> {
        return runSuspendCatching {
            withContext(dispatcher) { repository.getAllFolders() }
        }
    }
}