package com.mvvm.mvvmstudy.view.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.mvvmstudy.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var objectName: TextView = itemView.findViewById(R.id.objectName)
    var removeButton: Button = itemView.findViewById(R.id.removeButton)
    var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
}