package com.mvvm.mvvmstudy.model.repository

import io.reactivex.Completable
import io.reactivex.Single

interface BaseRepository <T> {

    fun getAll(): Single<List<T>>
    fun createOrUpdate(someObject:T) : Completable
    fun findById(id:Long) : Single<T>
    fun deleteById(id:Long) : Completable

}