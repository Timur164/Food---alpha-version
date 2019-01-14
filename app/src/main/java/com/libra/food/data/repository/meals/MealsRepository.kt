package com.libra.food.data.repository.meals

import com.libra.food.data.entities.Meal
import com.libra.food.room.IDaoMeals

class MealsRepository(private val iDaoMeals: IDaoMeals) : IMealsDao {

    override fun getAllMeals(): List<Meal> {
        return iDaoMeals.getAllMeals()
    }

    override fun getMealsByType(p: String): List<Meal> {
        return iDaoMeals.getMealsByType(p)
    }

    override fun insertMeals(meal: Meal) {
        return iDaoMeals.insertMeals(meal)
    }
}