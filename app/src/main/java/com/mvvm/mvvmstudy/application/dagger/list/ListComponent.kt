package com.mvvm.mvvmstudy.application.dagger.list

import com.mvvm.mvvmstudy.view.fragments.ListFragment
import dagger.Subcomponent

@Subcomponent(modules = [ListModule::class])
interface ListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListComponent
    }

    fun inject(fragment: ListFragment)
}