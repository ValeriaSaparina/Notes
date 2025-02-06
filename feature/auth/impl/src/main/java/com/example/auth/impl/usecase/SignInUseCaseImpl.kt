package com.example.auth.impl.usecase

import com.example.db.room.entity.UserEntity
import com.example.auth.api.repository.UserRepository
import com.example.auth.api.usecase.SignInUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignInUseCaseImpl(
    private val repository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SignInUseCase {
    override suspend fun invoke(email: String, password: String): Result<UserEntity> {
        return runSuspendCatching {
            withContext(dispatcher) {
                repository.signIn(email, password)
            }
        }
    }
}