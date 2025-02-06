package com.example.auth.impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.auth.api.usecase.SignUpUseCase
import com.example.auth.api.usecase.validators.IsEmailValidUseCase
import com.example.auth.api.usecase.validators.IsNameValidUseCase
import com.example.auth.api.usecase.validators.IsPasswordValidUseCase
import com.example.auth.impl.presentation.model.SignUpContract.Effect
import com.example.auth.impl.presentation.model.SignUpContract.Event
import com.example.auth.impl.presentation.model.SignUpContract.UiState
import com.example.designsystem.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val isNameValidUseCase: IsNameValidUseCase,
    private val isPasswordValidUseCase: IsPasswordValidUseCase,
) : BaseViewModel<Event, UiState, Effect>() {
    override fun setInitialState(): UiState {
        return UiState(
            isWrongEmail = false,
            isWrongName = false,
            isWrongPassword = false,
            isLoading = false,
            isError = false
        )
    }

    override fun handleEvents(event: Event) {
        when (event) {
            Event.OnSignInClicked -> setEffect { Effect.Navigation.ToSignIn }
            is Event.OnSignUpClicked -> {
                signUp(event.email, event.name, event.password)
            }
        }
    }

    private fun signUp(email: String, name: String, password: String) {
        if (validate(email, name, password)) {
            viewModelScope.launch {
                setState { copy(isLoading = true, isError = false) }
                signUpUseCase.invoke(email, name, password)
                    .onSuccess { user ->
                        setEffect { Effect.Navigation.ToFolders(user.id) }
                    }
                    .onFailure {
                        Log.d("SIGN UP", it.message ?: "hehehe")
                        setState { copy(isLoading = false, isError = true) }
                    }
            }
        }
    }

    private fun validate(email: String, name: String, password: String): Boolean {
        validateEmail(email)
        validateName(name)
        validatePassword(password)
        return !viewState.value.isWrongEmail && !viewState.value.isWrongName && !viewState.value.isWrongPassword
    }

    private fun validateEmail(email: String) {
        setState { copy(isLoading = true, isError = false) }
        val isValid = isEmailValidUseCase.invoke(email)
        setState { copy(isLoading = false, isWrongEmail = !isValid) }
    }

    private fun validateName(name: String) {
        setState { copy(isLoading = true, isError = false) }
        val isValid = isNameValidUseCase.invoke(name)
        setState { copy(isLoading = false, isWrongName = !isValid) }
    }

    private fun validatePassword(password: String) {
        setState { copy(isLoading = true, isError = false) }
        val isValid = isPasswordValidUseCase.invoke(password)
        setState { copy(isLoading = false, isWrongPassword = !isValid) }
    }
}