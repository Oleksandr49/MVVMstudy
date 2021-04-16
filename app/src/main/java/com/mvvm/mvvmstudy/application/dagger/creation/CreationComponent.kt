package com.mvvm.mvvmstudy.application.dagger.creation

import com.mvvm.mvvmstudy.view.fragments.CreationFragment
import dagger.Subcomponent

@Subcomponent(modules = [CreationModule::class])
interface CreationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreationComponent
    }

    fun inject(fragment: CreationFragment)
}