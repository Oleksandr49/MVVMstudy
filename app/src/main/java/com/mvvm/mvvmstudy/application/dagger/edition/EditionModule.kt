package com.mvvm.mvvmstudy.application.dagger.edition

import androidx.lifecycle.ViewModel
import com.mvvm.mvvmstudy.application.dagger.ViewModelKey
import com.mvvm.mvvmstudy.viewmodel.EditionFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EditionModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditionFragmentViewModel::class)
    abstract fun bindViewModel(viewmodel: EditionFragmentViewModel): ViewModel
}