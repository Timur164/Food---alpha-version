package com.libra.food.ui.fragment.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.libra.food.R
import com.libra.food.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {
    lateinit var dataBinding: FragmentBasketBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        return dataBinding.root
    }
}