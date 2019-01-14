package com.libra.food.data.repository.firebase

import com.libra.food.data.entities.Meal
import com.libra.food.utils.CHILD_REF

interface IFirebaseRepository {

    suspend fun readByChildName(childName: String = CHILD_REF): List<Meal>
}