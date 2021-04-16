package com.mvvm.mvvmstudy.application.dagger.creation

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.application.dagger.ViewModelKey
import com.mvvm.mvvmstudy.viewmodel.CreationFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreationModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreationFragmentViewModel::class)
    abstract fun bindViewModel(viewmodel: CreationFragmentViewModel): ViewModel
}