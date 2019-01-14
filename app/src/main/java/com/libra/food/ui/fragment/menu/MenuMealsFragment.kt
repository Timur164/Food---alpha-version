package com.libra.food.ui.fragment.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.libra.food.R
import com.libra.food.databinding.FragmentMenuMealsBinding
import com.libra.food.ui.fragment.meals.MealsFragment
import com.libra.food.utils.inTransaction
import kotlinx.android.synthetic.main.fragment_menu_meals.*

class MenuMealsFragment : Fragment() {

    lateinit var dataBind: FragmentMenuMealsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_menu_meals, container, false)
        return dataBind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewPager.adapter = SliderPageAdapter(fragmentManager, activity!!.applicationContext)
        tabLayout.setupWithViewPager(viewPager)
    }
}