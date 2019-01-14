package com.libra.food.di.meals

import com.libra.food.ui.activity.SplashScreen
import com.libra.food.ui.activity.details.DetailsActivity
import com.libra.food.ui.fragment.meals.MealsFragment
import dagger.Subcomponent

@MealsScope
@Subcomponent(modules = [MealsModule::class])
interface MealsComponent {
    fun plus(mealsFragment: MealsFragment)
    fun plus(detailsActivity: DetailsActivity)
    fun plus(splashScreen: SplashScreen)
}