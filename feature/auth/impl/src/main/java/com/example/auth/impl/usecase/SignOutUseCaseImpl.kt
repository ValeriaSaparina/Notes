package com.example.auth.impl.usecase

import com.example.auth.api.repository.UserRepository
import com.example.auth.api.usecase.SignOutUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignOutUseCaseImpl(
    private val repository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SignOutUseCase {
    override suspend fun invoke(): Result<Unit> {
        return runSuspendCatching {
            withContext(dispatcher) {
                repository.signOut()
            }
        }
    }
}