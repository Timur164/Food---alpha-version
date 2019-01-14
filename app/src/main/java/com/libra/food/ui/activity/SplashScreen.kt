package com.libra.food.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.libra.food.FoodDeliveryApp
import com.libra.food.R
import com.libra.food.ui.activity.main.MainActivity
import com.libra.food.ui.viewmodel.MealsViewModel
import com.libra.food.ui.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashScreen : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        (applicationContext as FoodDeliveryApp).initMealsComponent().plus(this)
        val mealViewModel = ViewModelProviders.of(this, viewModelFactory).get(MealsViewModel::class.java)
        mealViewModel.getMealsByType(0)
        mealViewModel.getMealsLiveData().observe(this, Observer {
            startAnimation()
            startMainActivity()
        })
    }

    private fun startAnimation() {
        tvFood.animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        ivRightCloud.animation = AnimationUtils.loadAnimation(this, R.anim.anim_in_left)
        ivLeftCloud.animation = AnimationUtils.loadAnimation(this, R.anim.anim_in_right)
        tvFood.visibility = View.VISIBLE
        ivRightCloud.visibility = View.VISIBLE
        ivLeftCloud.visibility = View.VISIBLE
    }

    private fun startMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }, 600)
    }
}