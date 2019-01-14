package com.libra.food.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.libra.food.data.entities.Meal
import com.libra.food.interactor.IMealsInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MealsViewModel(
    private val iMealsInteractor: IMealsInteractor,
    override val coroutineContext: CoroutineContext = Default
) : ViewModel(), CoroutineScope {
    private val mealsLiveData = MutableLiveData<List<Meal>>()
    private val mealLiveData = MutableLiveData<Meal>()

    fun getMealsByType(typeMeals: Int) {
        launch {
            val mealsByType = iMealsInteractor.getMealsByType(typeMeals)
            mealsLiveData.postValue(mealsByType)
        }
    }

    fun getMealsById(id: Int) {
        launch {
            mealLiveData.postValue(iMealsInteractor.getMealsById(id))
        }
    }

    fun getMealsLiveData(): LiveData<List<Meal>> = mealsLiveData

    fun getMealLiveData(): LiveData<Meal> = mealLiveData

}