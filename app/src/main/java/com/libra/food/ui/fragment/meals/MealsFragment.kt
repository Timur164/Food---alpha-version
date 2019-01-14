package com.libra.food.ui.fragment.meals

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.libra.food.FoodDeliveryApp
import com.libra.food.R
import com.libra.food.data.entities.Meal
import com.libra.food.ui.activity.details.DetailsActivity
import com.libra.food.ui.fragment.meals.adapter.MealsAdapter
import com.libra.food.ui.fragment.meals.adapter.OnItemClick
import com.libra.food.ui.viewmodel.MealsViewModel
import com.libra.food.ui.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_meals.*
import timber.log.Timber
import javax.inject.Inject


class MealsFragment : Fragment(), OnItemClick {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mealViewModel: MealsViewModel
    private lateinit var mealsAdapter: MealsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_meals, container, false)
        (activity!!.applicationContext as FoodDeliveryApp).initMealsComponent().plus(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mealViewModel = ViewModelProviders.of(this, viewModelFactory).get(MealsViewModel::class.java)
        val typeMeals = arguments!!.getInt("typeMeals", 0)
        mealViewModel.getMealsByType(typeMeals)
        mealsAdapter = MealsAdapter(this)
        rvMeals.adapter = mealsAdapter
        rvMeals.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animator_down)
        rvMeals.scheduleLayoutAnimation()
        mealViewModel.getMealsLiveData().observe(this, Observer {
            mealsAdapter.addMeals(it)
            listIsEmpty(it.isEmpty())
        })
    }

    private fun listIsEmpty(isEmpty: Boolean) {
        pbLoading.visibility = View.GONE
        if (isEmpty) {
            tvEmptyList.visibility = View.VISIBLE
            rvMeals.visibility = View.VISIBLE
        } else {
            rvMeals.visibility = View.VISIBLE
            tvEmptyList.visibility = View.GONE
        }
    }

    override fun onClick(meal: Meal) {
        Timber.e("click on meal: $meal")
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("idMeal", meal.id)
        startActivity(intent)
    }
}