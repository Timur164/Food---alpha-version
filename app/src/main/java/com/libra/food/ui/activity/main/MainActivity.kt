package com.libra.food.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.libra.food.FoodDeliveryApp
import com.libra.food.R
import com.libra.food.di.meals.MealsModule
import com.libra.food.di.navigation.NavigationModule
import com.libra.food.ui.viewmodel.NavigationViewModel
import com.libra.food.ui.viewmodel.ViewModelFactory
import com.libra.food.utils.inTransaction
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var navViewModel: NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as FoodDeliveryApp).getAppComponent().inject(NavigationModule()).plus(this)
        navViewModel = ViewModelProviders.of(this, viewModelFactory).get(NavigationViewModel::class.java)
        bvSelector.setOnNavigationItemSelectedListener(navViewModel)
        navViewModel.getNavigateToFragment().observe(this, Observer {
            supportFragmentManager.inTransaction {
                replace(R.id.fragmentContainer, it)
            }
        })

    }
}
