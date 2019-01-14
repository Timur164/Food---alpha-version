package com.libra.food.interactor

import com.libra.food.data.entities.Meal

interface IMealsInteractor {

    suspend fun getMealsByType(typeMeals: Int): List<Meal>

    suspend fun getMealsById(id:Int):Meal
}