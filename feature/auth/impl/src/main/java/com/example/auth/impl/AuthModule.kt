package com.example.auth.impl

import android.content.Context
import android.content.SharedPreferences
import com.example.auth.api.datasource.LocalDataSource
import com.example.auth.api.datasource.PreferencesDataSource
import com.example.auth.api.datasource.RemoteDataSource
import com.example.auth.api.repository.UserRepository
import com.example.auth.api.usecase.IsAuthUseCase
import com.example.auth.api.usecase.SignInUseCase
import com.example.auth.api.usecase.SignOutUseCase
import com.example.auth.api.usecase.SignUpUseCase
import com.example.auth.api.usecase.validators.IsEmailValidUseCase
import com.example.auth.api.usecase.validators.IsNameValidUseCase
import com.example.auth.api.usecase.validators.IsPasswordValidUseCase
import com.example.auth.impl.datasource.local.RoomDataSource
import com.example.auth.impl.datasource.local.SharedPreferencesDataSource
import com.example.auth.impl.datasource.remote.FirebaseDataSource
import com.example.auth.impl.presentation.SignInViewModel
import com.example.auth.impl.presentation.SignUpViewModel
import com.example.auth.impl.repository.UserRepositoryImpl
import com.example.auth.impl.usecase.IsAuthUseCaseImpl
import com.example.auth.impl.usecase.SignInUseCaseImpl
import com.example.auth.impl.usecase.SignOutUseCaseImpl
import com.example.auth.impl.usecase.SignUpUseCaseImpl
import com.example.auth.impl.usecase.validation.IsEmailValidUseCaseImpl
import com.example.auth.impl.usecase.validation.IsNameValidUseCaseImpl
import com.example.auth.impl.usecase.validation.IsPasswordValidUseCaseImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single<LocalDataSource> { RoomDataSource(get()) }
    single<RemoteDataSource> { FirebaseDataSource(get(), get()) }
    single<PreferencesDataSource> { SharedPreferencesDataSource(get()) }

    single<CoroutineDispatcher> { Dispatchers.IO }

    single<FirebaseAuth> {
        Firebase.auth
    }
    single<FirebaseFirestore> {
        Firebase.firestore
    }

    single<SharedPreferences> {
        androidContext().getSharedPreferences("storage", Context.MODE_PRIVATE)
    }

    factory<IsAuthUseCase> { IsAuthUseCaseImpl(get(), get()) }
    factory<SignInUseCase> { SignInUseCaseImpl(get(), get()) }
    factory<SignUpUseCase> { SignUpUseCaseImpl(get(), get()) }
    factory<SignOutUseCase> { SignOutUseCaseImpl(get(), get()) }

    factory<IsEmailValidUseCase> { IsEmailValidUseCaseImpl() }
    factory<IsNameValidUseCase> { IsNameValidUseCaseImpl() }
    factory<IsPasswordValidUseCase> { IsPasswordValidUseCaseImpl() }

    viewModel { SignUpViewModel(get(), get(), get(), get()) }
    viewModel { SignInViewModel(get(), get()) }
}