package com.libra.food.di

import android.content.Context
import androidx.room.Room
import com.libra.food.FoodDeliveryApp
import com.libra.food.firebase.FirebaseInstance
import com.libra.food.firebase.IFirebaseInstance
import com.libra.food.room.AppDatabase
import com.libra.food.room.IDaoMeals
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: FoodDeliveryApp) {

    private val appDAtaBase = Room.databaseBuilder(app, AppDatabase::class.java, "meals_db").build()
    private val firebase = FirebaseInstance()

    @Provides
    @Singleton
    fun getDataBase(): AppDatabase = appDAtaBase

    @Provides
    @Singleton
    fun getContext(): Context = app

    @Provides
    @Singleton
    fun getDaoMeals(): IDaoMeals = appDAtaBase.meals()

    @Provides
    @Singleton
    fun getFirebaseInstance(): IFirebaseInstance = firebase
}