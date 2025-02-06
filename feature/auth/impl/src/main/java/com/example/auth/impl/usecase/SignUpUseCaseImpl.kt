package com.example.auth.impl.usecase

import com.example.db.room.entity.UserEntity
import com.example.auth.api.repository.UserRepository
import com.example.auth.api.usecase.SignUpUseCase
import com.example.utils.extention.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpUseCaseImpl(
    private val repository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SignUpUseCase {
    override suspend fun invoke(
        email: String,
        name: String,
        password: String,
    ): Result<UserEntity> {
        return runSuspendCatching {
            withContext(dispatcher) {
                repository.signUp(email, name, password)
            }
        }
    }
}