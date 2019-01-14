package com.libra.food.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.libra.food.data.entities.Meal
import com.libra.food.data.repository.meals.IMealsDao

@Dao
interface IDaoMeals: IMealsDao {

    @Query("SELECT * FROM Meal")
     override fun getAllMeals(): List<Meal>

    @Query("SELECT * FROM Meal WHERE type LIKE :p")
    override fun getMealsByType(p: String): List<Meal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertMeals(meal: Meal)

}