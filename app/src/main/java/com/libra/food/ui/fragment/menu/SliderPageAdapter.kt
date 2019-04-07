package com.libra.food.ui.fragment.menu

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class SliderPageAdapter(fm: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fm) {
    private val listItem = MenuPageItem.values()

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        val mealsFragment = listItem[position].fragment
        bundle.putInt("typeMeals", position)
        mealsFragment.arguments = bundle
        return mealsFragment
    }

    override fun getCount(): Int = listItem.size


    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(listItem[position].stringId)
    }

    override fun saveState(): Parcelable? = null
}