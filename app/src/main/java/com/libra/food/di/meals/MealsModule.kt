package com.libra.food.di.meals

import com.libra.food.data.repository.firebase.FireBaseRepository
import com.libra.food.data.repository.firebase.IFirebaseRepository
import com.libra.food.data.repository.meals.IMealsDao
import com.libra.food.data.repository.meals.MealsRepository
import com.libra.food.firebase.IFirebaseInstance
import com.libra.food.interactor.IMealsInteractor
import com.libra.food.interactor.MealsInteractor
import com.libra.food.room.IDaoMeals
import com.libra.food.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MealsModule {
    @MealsScope
    @Provides
    fun getModelFactory(iMealsInteractor: IMealsInteractor): ViewModelFactory {
        return ViewModelFactory(iMealsInteractor)
    }

    @MealsScope
    @Provides
    fun getInteractor(iMealsDao: IMealsDao, iFirebaseRepository: IFirebaseRepository): IMealsInteractor {
        return MealsInteractor(iMealsDao, iFirebaseRepository)
    }

    @MealsScope
    @Provides
    fun getMealsRepository(iDaoMeals: IDaoMeals): IMealsDao {
        return MealsRepository(iDaoMeals)
    }

    @MealsScope
    @Provides
    fun getFirebaseRepository(iFirebaseInstance: IFirebaseInstance): IFirebaseRepository {
        return FireBaseRepository(iFirebaseInstance)
    }
}