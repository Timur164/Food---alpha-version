package com.libra.food.ui.fragment.meals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.libra.food.R
import com.libra.food.databinding.ItemMealsBinding
import com.libra.food.data.entities.Meal
import androidx.recyclerview.widget.DiffUtil


class MealsAdapter(val onItemClick: OnItemClick) : RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {

    var meals = ArrayList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val mealsBidnding: ItemMealsBinding = DataBindingUtil.inflate(inflater, R.layout.item_meals, parent, false)
        return MealsViewHolder(mealsBidnding)
    }

    override fun getItemCount(): Int = meals.size

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    fun addMeals(newMeals: List<Meal>) {
        val diffResult = DiffUtil.calculateDiff(MealsDiffUtilCallBack(meals, newMeals), false)
        diffResult.dispatchUpdatesTo(this)
        meals.clear()
        this.meals.addAll(newMeals)
    }

    inner class MealsViewHolder(val mealsBidnding: ItemMealsBinding) : RecyclerView.ViewHolder(mealsBidnding.root) {

        fun bind(meal: Meal) {
            mealsBidnding.meal = meal
            mealsBidnding.onItemClick = onItemClick
            mealsBidnding.executePendingBindings()
        }
    }
}