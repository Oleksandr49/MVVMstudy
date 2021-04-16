package com.mvvm.mvvmstudy.application.dagger.details

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.application.dagger.ViewModelKey
import com.mvvm.mvvmstudy.viewmodel.DetailsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsFragmentViewModel::class)
    abstract fun bindViewModel(viewmodel: DetailsFragmentViewModel): ViewModel
}