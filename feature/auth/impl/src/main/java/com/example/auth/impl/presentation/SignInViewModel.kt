package com.example.auth.impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.auth.api.usecase.IsAuthUseCase
import com.example.auth.api.usecase.SignInUseCase
import com.example.auth.impl.presentation.model.SignInContract.Effect
import com.example.auth.impl.presentation.model.SignInContract.Event
import com.example.auth.impl.presentation.model.SignInContract.UiState
import com.example.designsystem.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val isAuthUseCase: IsAuthUseCase,
) : BaseViewModel<Event, UiState, Effect>() {

    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId

    init {
        isAuth()
    }

    override fun setInitialState(): UiState {
        return UiState(wrongCredentials = false, isLoading = false, isError = false)
    }

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnSignInClicked -> signIn(event.email, event.password)
            Event.OnSignUpClicked -> setEffect { Effect.Navigation.ToSignUp }
            Event.SendWrongCredentials -> setState { copy(wrongCredentials = true) }
        }
    }

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            signInUseCase.invoke(email, password)
                .onSuccess { user ->
                    setEffect { Effect.Navigation.ToFolders(user.id) }
                }
                .onFailure {
                    Log.d("SIGN IN", "${it.message}")
                    setState { copy(isLoading = false, isError = true) }
                }
        }
    }

    private fun isAuth() {
        viewModelScope.launch {
            isAuthUseCase.invoke()
                .onSuccess { userId ->
                    _userId.value = userId
                }
                .onFailure {
                    Log.d("SIGN IN", "${it.message}")
                }
        }
    }
}