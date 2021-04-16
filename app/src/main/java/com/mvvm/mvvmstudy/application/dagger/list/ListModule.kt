package com.mvvm.mvvmstudy.application.dagger.list

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.application.dagger.ViewModelKey
import com.mvvm.mvvmstudy.viewmodel.ListFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    abstract fun bindViewModel(viewmodel: ListFragmentViewModel): ViewModel
}