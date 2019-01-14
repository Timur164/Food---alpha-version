package com.libra.food.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

const val REFERENCE = "fooddelivery-c93f9"

class FirebaseInstance : IFirebaseInstance {

    private lateinit var database: FirebaseDatabase

    override fun getFirebaseDatabase(): FirebaseDatabase {
        if (!::database.isInitialized) {
            database = FirebaseDatabase.getInstance()
            database.setPersistenceEnabled(true)
        }
        return database
    }

    override fun getReference(): DatabaseReference {
        if (!::database.isInitialized) {
            database = FirebaseDatabase.getInstance()
        }
        return database.reference
    }
}