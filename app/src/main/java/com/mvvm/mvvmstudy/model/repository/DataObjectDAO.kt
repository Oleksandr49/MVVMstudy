package com.mvvm.mvvmstudy.model.repository

import androidx.room.*
import com.mvvm.mvvmstudy.model.domainModel.DataObject
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DataObjectDAO {

    @Query("SELECT * FROM DataObjects")
    fun getAll(): Single<List<DataObject>>

    @Query("SELECT * FROM DataObjects WHERE id = :id")
    fun findById(id: Long): Single<DataObject>

    @Insert
    fun create(dataObject: DataObject): Single<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(dataObject: DataObject) : Completable

    @Query("DELETE FROM DataObjects WHERE id = :id")
    fun deleteById(id: Long): Completable
}