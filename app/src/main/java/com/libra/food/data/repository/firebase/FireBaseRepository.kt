package com.libra.food.data.repository.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.libra.food.data.entities.Meal
import com.libra.food.firebase.IFirebaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext


class FireBaseRepository(
    val iFirebaseInstance: IFirebaseInstance,
    override val coroutineContext: CoroutineContext = Default
) : IFirebaseRepository, CoroutineScope {

    override suspend fun readByChildName(childName: String): List<Meal> {
        val channel = Channel<Meal?>()
        val child = iFirebaseInstance.getReference().child(childName)
        Timber.e("read from firebase!")
        child.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnap: DataSnapshot) {
                launch {
                    for (i in 0 until dataSnap.childrenCount) {
                        val value = dataSnap.child(i.toString()).getValue(Meal::class.java)
                        channel.send(value)
                    }
                    channel.close()
                }
            }

            override fun onCancelled(dataError: DatabaseError) {
                Timber.e("DatabaseError: $dataError")
            }
        })
        return channel.toList()
            .filter { it != null }
            .map { it!! }
    }
}