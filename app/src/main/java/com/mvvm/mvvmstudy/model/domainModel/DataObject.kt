package com.mvvm.mvvmstudy.model.domainModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DataObjects")
data class DataObject(@ColumnInfo(name = "name")var name: String,  @ColumnInfo(name = "details")var details: String)
{
        @PrimaryKey(autoGenerate = true)
        var id : Long? = null
}