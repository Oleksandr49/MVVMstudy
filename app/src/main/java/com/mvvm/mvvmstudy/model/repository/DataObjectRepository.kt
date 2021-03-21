package com.mvvm.mvvmstudy.model.repository

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import io.reactivex.Completable
import io.reactivex.Single

class DataObjectRepository : BaseRepository <DataObject>{

    private val database : DataObjectDatabase? = DataObjectDatabase.getInstance()

    override fun getAll(): Single<List<DataObject>> {
            return database!!.dataObjectDAO().getAll()
    }

    override fun createOrUpdate(someObject: DataObject) : Completable {
        return database!!.dataObjectDAO().createOrUpdate(someObject)
    }

    override fun findById(id: Long): Single<DataObject> {
        return database!!.dataObjectDAO().findById(id)
    }

    override fun deleteById(id: Long) : Completable {
        return database!!.dataObjectDAO().deleteById(id)
    }
}