package com.libra.food.ui.fragment.meals.adapter

import androidx.recyclerview.widget.DiffUtil
import com.libra.food.data.entities.Meal

class MealsDiffUtilCallBack(private val oldList: List<Meal>, private val newList: List<Meal>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title &&
                oldList[oldItemPosition].text == newList[newItemPosition].text &&
                oldList[oldItemPosition].price == newList[newItemPosition].price
    }
}