package com.libra.food.interactor

import com.libra.food.data.entities.Meal
import com.libra.food.data.repository.firebase.IFirebaseRepository
import com.libra.food.data.repository.meals.IMealsDao
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class MealsInteractor(private val iMealsDao: IMealsDao, private val iFirebaseRepository: IFirebaseRepository) :
        IMealsInteractor {
    private val meals = ArrayList<Meal>()
    private var iter = 0

    override suspend fun getMealsByType(typeMeals: Int): List<Meal> {
        updateMeals()
        return when (typeMeals) {
            0 -> meals
            else -> meals.filter { it.type == typeMeals }.distinct()
        }
    }

    private suspend fun updateMeals() {
        if (isInternetConnected()) {
            if (meals.isEmpty() || iter == 0 || iter % 10 == 0) {
                val fMeals = iFirebaseRepository.readByChildName()
                insertToDB(fMeals)
                meals.clear()
                meals.addAll(fMeals)
            }
            updateMealsFromDB()
            iter++
        } else {
            updateMealsFromDB()
        }
    }

    override suspend fun getMealsById(id: Int): Meal {
        if (meals.isEmpty()) updateMeals()
        return meals.first { it.id == id }
    }

    private fun updateMealsFromDB() {
        if (meals.isEmpty()) {
            meals.clear()
            meals.addAll(iMealsDao.getAllMeals())
        }
    }

    private fun insertToDB(mealFromServer: List<Meal>) {
        if (mealFromServer.isNotEmpty()) {
            mealFromServer.forEach {
                iMealsDao.insertMeals(it)
            }
        }
    }

    private fun isInternetConnected(): Boolean {
        return try {
            val timeoutMs = 1500
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53) //Google DNS
            socket.connect(socketAddress, timeoutMs)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}