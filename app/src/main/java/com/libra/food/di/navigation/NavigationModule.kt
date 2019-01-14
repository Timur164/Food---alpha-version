package com.libra.food.di.navigation

import com.libra.food.di.meals.MealsScope
import com.libra.food.interactor.IMealsInteractor
import com.libra.food.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    fun getModelFactory(): ViewModelFactory {
        return ViewModelFactory()
    }
}