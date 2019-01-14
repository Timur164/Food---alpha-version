package com.libra.food.data.repository.meals


interface IDao<P,O>{

    fun getAllMeals(): List<O>

    fun getMealsByType(p: P): List<O>

    fun insertMeals(o: O)

}