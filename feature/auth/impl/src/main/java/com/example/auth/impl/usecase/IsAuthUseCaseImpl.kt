package com.example.auth.impl.usecase

import com.example.auth.api.repository.UserRepository
import com.example.auth.api.usecase.IsAuthUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IsAuthUseCaseImpl(
    private val repository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : IsAuthUseCase {
    override suspend fun invoke(): Result<String?> {
        return runSuspendCatching { withContext(dispatcher) { (repository.isAuth()) } }
    }
}