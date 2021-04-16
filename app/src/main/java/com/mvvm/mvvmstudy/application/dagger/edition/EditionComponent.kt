package com.mvvm.mvvmstudy.application.dagger.edition

import com.mvvm.mvvmstudy.view.fragments.EditionFragment
import dagger.Subcomponent

@Subcomponent(modules = [EditionModule::class])
interface EditionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditionComponent
    }

    fun inject(fragment: EditionFragment)
}