package com.example.feature.folders.impl

import com.example.feature.folders.api.datasources.LocalFoldersDataSource
import com.example.feature.folders.api.datasources.RemoteFoldersDataSource
import com.example.feature.folders.api.repository.FoldersRepository
import com.example.feature.folders.api.usecase.CreateFolderUseCase
import com.example.feature.folders.api.usecase.GetAllFoldersUseCase
import com.example.feature.folders.impl.datasource.local.RoomFoldersDataSource
import com.example.feature.folders.impl.datasource.remote.FirebaseFoldersDataSource
import com.example.feature.folders.impl.presentation.FoldersViewModel
import com.example.feature.folders.impl.repository.FoldersRepositoryImpl
import com.example.feature.folders.impl.usecase.CreateFolderUseCaseImpl
import com.example.feature.folders.impl.usecase.GetAllFoldersUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val foldersModule = module {

    single<FoldersRepository> { FoldersRepositoryImpl(get(), get()) }
    single<LocalFoldersDataSource> { RoomFoldersDataSource(get()) }
    single<RemoteFoldersDataSource> { FirebaseFoldersDataSource(get(), get()) }

    single<CoroutineDispatcher> { Dispatchers.IO }

    factory<GetAllFoldersUseCase> { GetAllFoldersUseCaseImpl(get(), get()) }
    factory<CreateFolderUseCase> { CreateFolderUseCaseImpl(get(), get()) }

    viewModel { FoldersViewModel(get(), get()) }

}