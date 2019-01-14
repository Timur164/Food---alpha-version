package com.libra.food.ui.fragment.meals.adapter

import com.libra.food.data.entities.Meal

interface OnItemClick {
    fun onClick(meal:Meal)
}