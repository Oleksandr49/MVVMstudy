package com.mvvm.mvvmstudy.view.adapter

interface ViewCallback {
    fun removePosition(positionID: Long)
    fun positionDetails(positionID: Long)
}