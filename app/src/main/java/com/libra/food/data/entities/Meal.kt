package com.libra.food.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
        @PrimaryKey
        val id: Int = 0,
        val title: String = "",
        val text: String = "",
        val price: Int = 0,
        val type: Int = 0,
        val description: String = "",
        val rating: Float = 0f,
        val weight: Float = 0f,
        val image:String=""
)
