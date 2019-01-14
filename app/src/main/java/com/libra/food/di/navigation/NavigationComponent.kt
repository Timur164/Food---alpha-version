package com.libra.food.di.navigation

import com.libra.food.ui.activity.main.MainActivity
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [NavigationModule::class])
interface NavigationComponent {
    fun plus(mainActivity: MainActivity)
}