package com.libra.food.di

import com.libra.food.di.meals.MealsComponent
import com.libra.food.di.meals.MealsModule
import com.libra.food.di.navigation.NavigationComponent
import com.libra.food.di.navigation.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(mealsModule: MealsModule): MealsComponent
    fun inject(navigationModule: NavigationModule): NavigationComponent
}