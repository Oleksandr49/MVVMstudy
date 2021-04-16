package com.mvvm.mvvmstudy.application.dagger

import android.content.Context
import androidx.room.Room
import com.mvvm.mvvmstudy.model.repository.DataObjectDAO
import com.mvvm.mvvmstudy.model.repository.DataObjectDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object DaoModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(context: Context): DataObjectDAO {
        return Room.databaseBuilder(context, DataObjectDatabase::class.java, "DataObjectsDatabase")
                .fallbackToDestructiveMigration()
                .build()
                .dataObjectDAO()
    }
}