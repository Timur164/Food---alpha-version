package com.libra.food.ui.fragment.menu

import androidx.fragment.app.Fragment
import com.libra.food.R
import com.libra.food.ui.fragment.meals.MealsFragment

enum class MenuPageItem(val stringId: Int, val fragment: Fragment = MealsFragment()) {
    ALL_DISHES(R.string.all_dishes),
    COMBO(R.string.combo),
    PIZZA(R.string.pizza),
    ROLLS(R.string.rolls)
}