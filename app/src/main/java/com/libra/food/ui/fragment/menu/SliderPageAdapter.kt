package com.libra.food.ui.fragment.menu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.libra.food.R
import com.libra.food.ui.fragment.meals.MealsFragment
import android.os.Parcelable



class SliderPageAdapter(fm: FragmentManager?, private val context: Context) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        val mealsFragment = MealsFragment()
        bundle.putInt("typeMeals", position)
        mealsFragment.arguments = bundle
        return mealsFragment
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.all_dishes)
            1 -> context.getString(R.string.combo)
            2 -> context.getString(R.string.pizza)
            3 -> context.getString(R.string.rolls)
            else -> ""
        }
    }

    override fun saveState(): Parcelable? {
        return null
    }
}