package com.mvvm.mvvmstudy.application


import androidx.room.Room
import com.mvvm.mvvmstudy.model.repository.DataObjectDatabase
import com.mvvm.mvvmstudy.model.repository.DataObjectRepository
import com.mvvm.mvvmstudy.useCases.completabeUseCases.DataObjectDeleteUseCase
import com.mvvm.mvvmstudy.useCases.completabeUseCases.DataObjectUpdateUseCase
import com.mvvm.mvvmstudy.useCases.singleUseCases.DataObjectCreationSingleUseCase
import com.mvvm.mvvmstudy.useCases.singleUseCases.DataObjectDetailsUseCase
import com.mvvm.mvvmstudy.useCases.singleUseCases.DataObjectGetListUseCase
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel
import com.mvvm.mvvmstudy.viewmodel.DetailsFragmentViewModel
import com.mvvm.mvvmstudy.viewmodel.EditionFragmentViewModel
import com.mvvm.mvvmstudy.viewmodel.ListFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModules = module {

    viewModel { CreationFragmentViewModel(get()) }
    viewModel { DetailsFragmentViewModel(get()) }
    viewModel { EditionFragmentViewModel(get(), get()) }
    viewModel { ListFragmentViewModel(get(), get()) }

    single { DataObjectDeleteUseCase(get(), Schedulers.io(), AndroidSchedulers.mainThread()) }
    single { DataObjectCreationSingleUseCase(get(), Schedulers.io(), AndroidSchedulers.mainThread()) }
    single { DataObjectDetailsUseCase(get(), Schedulers.io(), AndroidSchedulers.mainThread()) }
    single { DataObjectGetListUseCase(get(), Schedulers.io(), AndroidSchedulers.mainThread()) }
    single { DataObjectUpdateUseCase(get(), Schedulers.io(), AndroidSchedulers.mainThread()) }
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