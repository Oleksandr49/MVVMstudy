package com.mvvm.mvvmstudy.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvvm.mvvmstudy.model.domainModel.DataObject

@Database(entities = [DataObject::class], version = 1)
abstract class DataObjectDatabase : RoomDatabase() {

    abstract fun dataObjectDAO(): DataObjectDAO

}