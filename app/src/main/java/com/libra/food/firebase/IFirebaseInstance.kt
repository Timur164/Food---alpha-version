package com.libra.food.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface IFirebaseInstance {
    fun getFirebaseDatabase(): FirebaseDatabase
    fun getReference(): DatabaseReference
}