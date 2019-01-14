package com.libra.food.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.libra.food.data.entities.Meal

@Database(entities = [Meal::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract fun meals():IDaoMeals
}