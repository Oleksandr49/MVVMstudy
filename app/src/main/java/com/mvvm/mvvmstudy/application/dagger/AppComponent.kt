package com.mvvm.mvvmstudy.application.dagger

import android.content.Context
import com.mvvm.mvvmstudy.application.dagger.creation.CreationComponent
import com.mvvm.mvvmstudy.application.dagger.details.DetailsComponent
import com.mvvm.mvvmstudy.application.dagger.edition.EditionComponent
import com.mvvm.mvvmstudy.application.dagger.list.ListComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DaoModule::class, ViewModelBuilderModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun listComponent(): ListComponent.Factory
    fun creationComponent(): CreationComponent.Factory
    fun detailsComponent(): DetailsComponent.Factory
    fun editionComponent(): EditionComponent.Factory

}

@Module(
        subcomponents = [
            ListComponent::class,
            CreationComponent::class,
            EditionComponent::class,
            DetailsComponent::class
        ]
)
object SubcomponentsModule