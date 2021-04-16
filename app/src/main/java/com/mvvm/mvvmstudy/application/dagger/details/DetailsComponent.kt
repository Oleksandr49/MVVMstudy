package com.mvvm.mvvmstudy.application.dagger.details

import com.mvvm.mvvmstudy.view.fragments.DetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(fragment: DetailsFragment)
}