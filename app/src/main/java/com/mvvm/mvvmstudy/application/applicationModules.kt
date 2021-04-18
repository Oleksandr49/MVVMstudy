package com.mvvm.mvvmstudy.application


import androidx.room.Room
import com.mvvm.mvvmstudy.model.repository.DataObjectDatabase
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.model.useCases.crudUseCases.*
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel
import com.mvvm.mvvmstudy.viewmodel.DetailsFragmentViewModel
import com.mvvm.mvvmstudy.viewmodel.EditionFragmentViewModel
import com.mvvm.mvvmstudy.viewmodel.ListFragmentViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModules = module {

    viewModel { CreationFragmentViewModel(get()) }
    viewModel { DetailsFragmentViewModel(get()) }
    viewModel { EditionFragmentViewModel(get(), get()) }
    viewModel { ListFragmentViewModel(get(), get()) }

    factory { DeletionUseCase(get()) }
    factory { CreationUseCase(get()) }
    factory { DetailsUseCase(get()) }
    factory { GetAllUseCase(get()) }
    factory { UpdateUseCase(get()) }

    single { DataObjectRepository(get()) }

    single {
        Room.databaseBuilder(androidApplication(),
            DataObjectDatabase::class.java, "DataObjectsDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
            .dataObjectDAO()
    }
}