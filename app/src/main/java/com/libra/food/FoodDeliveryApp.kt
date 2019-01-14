package com.libra.food

import android.app.Application
import com.libra.food.di.AppComponent
import com.libra.food.di.AppModule
import com.libra.food.di.DaggerAppComponent
import com.libra.food.di.meals.MealsComponent
import com.libra.food.di.meals.MealsModule
import com.libra.food.utils.TimberTree
import timber.log.Timber

class FoodDeliveryApp : Application() {
    private lateinit var appComponent: AppComponent
    private var mealsComponent: MealsComponent? = null

    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberTree())
        appComponent = buildComponent().build()
    }

    private fun buildComponent(): DaggerAppComponent.Builder {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
    }


    fun getAppComponent(): AppComponent {
        return appComponent
    }

    fun initMealsComponent(): MealsComponent {
        if (mealsComponent == null) {
            mealsComponent = appComponent.inject(MealsModule())
        }
        return mealsComponent!!
    }

    fun clearMealsComponent() {
        mealsComponent = null
    }

}