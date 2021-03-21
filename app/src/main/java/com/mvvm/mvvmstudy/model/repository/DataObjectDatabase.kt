package com.mvvm.mvvmstudy.model.repository

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.mvvmstudy.MyApplication
import com.mvvm.mvvmstudy.model.domainModel.DataObject

@Database(entities = [DataObject::class], version = 1)
abstract class DataObjectDatabase : RoomDatabase() {

    abstract fun dataObjectDAO(): DataObjectDAO

    companion object {

        private const val DB_Name = "DataObjectsDatabase"
        private var instance: DataObjectDatabase? = null

        @Synchronized
        fun getInstance(): DataObjectDatabase {
            return instance ?: Room.databaseBuilder(MyApplication.getAppContext(),
                    DataObjectDatabase::class.java, DB_Name)
                    .fallbackToDestructiveMigration()
                    .build()

        }
    }
}