package com.libra.food.ui.viewmodel

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.libra.food.R.id.actionBasket
import com.libra.food.R.id.actionFood
import com.libra.food.ui.fragment.basket.BasketFragment
import com.libra.food.ui.fragment.menu.MenuMealsFragment

class NavigationViewModel : ViewModel(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val navigateToFragment = MutableLiveData<Fragment>()

    init {
        navigateToFragment.postValue(MenuMealsFragment())
    }

    fun getNavigateToFragment(): LiveData<Fragment> = navigateToFragment

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            actionFood -> navigateToFragment.postValue(MenuMealsFragment())
            actionBasket -> navigateToFragment.postValue(BasketFragment())
        }
        return true
    }

}