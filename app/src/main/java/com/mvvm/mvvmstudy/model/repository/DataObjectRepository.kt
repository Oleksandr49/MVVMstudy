package com.mvvm.mvvmstudy.model.repository

import com.mvvm.mvvmstudy.model.domainModel.DataObject
import io.reactivex.Completable
import io.reactivex.Single

class DataObjectRepository : BaseRepository <DataObject>{

    private val database : DataObjectDatabase = DataObjectDatabase.getInstance()

    override fun getAll(): Single<List<DataObject>> = database.dataObjectDAO().getAll()

    override fun create(someObject: DataObject) : Single<Long> = database.dataObjectDAO().create(someObject)

    override fun findById(id: Long): Single<DataObject> = database.dataObjectDAO().findById(id)

    override fun deleteById(id: Long) : Completable = database.dataObjectDAO().deleteById(id)

    override fun update(dataObject: DataObject) : Completable = database.dataObjectDAO().update(dataObject)
}