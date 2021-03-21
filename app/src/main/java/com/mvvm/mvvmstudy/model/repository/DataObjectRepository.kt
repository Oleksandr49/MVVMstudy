package com.mvvm.mvvmstudy.model.repository

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import io.reactivex.Completable
import io.reactivex.Single

class DataObjectRepository : BaseRepository <DataObject>{

    private val database : DataObjectDatabase? = DataObjectDatabase.getInstance()

    override fun getAll(): Single<List<DataObject>>{
        val dataList: Single<List<DataObject>>? = database?.let {this.database.dataObjectDAO().getAll()}
        if(dataList!=null) return  dataList
        else throw Exception("No data found")
    }

    override fun createOrUpdate(someObject: DataObject) : Single<Long> {
        val result: Single<Long>? = database?.let {this.database.dataObjectDAO().createOrUpdate(someObject)}
        if(result!=null) return  result
        else throw Exception("Creation/Update was not completed")
    }

    override fun findById(id: Long): Single<DataObject> {
        val dataObject: Single<DataObject>? = database?.let {this.database.dataObjectDAO().findById(id)}
        if(dataObject!=null) return  dataObject
        else throw Exception("No data found")
    }

    override fun deleteById(id: Long) : Completable{
        val result: Completable? = database?.let {this.database.dataObjectDAO().deleteById(id)}
        if(result!=null) return  result
        else throw Exception("No data was deleted")
    }
}