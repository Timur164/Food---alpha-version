package com.libra.food.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.libra.food.interactor.IMealsInteractor
import java.lang.ClassCastException

class ViewModelFactory : ViewModelProvider.Factory {

    private lateinit var iMealsInteractor: IMealsInteractor

    constructor() : super()
    constructor(iMealsInteractor: IMealsInteractor) : super() {
        this.iMealsInteractor = iMealsInteractor
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NavigationViewModel::class.java) -> NavigationViewModel() as T
            modelClass.isAssignableFrom(MealsViewModel::class.java) -> MealsViewModel(iMealsInteractor) as T
            else -> throw ClassCastException("ViewModelFactory error")
        }
    }
}