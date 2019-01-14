package com.libra.food.ui.activity.details

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.libra.food.FoodDeliveryApp
import com.libra.food.R
import com.libra.food.data.entities.Meal
import com.libra.food.databinding.ActivityDetailBinding
import com.libra.food.ui.viewmodel.MealsViewModel
import com.libra.food.ui.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import timber.log.Timber
import javax.inject.Inject


class DetailsActivity : AppCompatActivity(), View.OnClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var defaultCoef = 1.5f
    private lateinit var meal: Meal
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        (applicationContext as FoodDeliveryApp).initMealsComponent().plus(this)
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == STATE_EXPANDED) {
                    bottom_sheet.setBackgroundResource(R.color.light)
                } else {
                    bottom_sheet.setBackgroundResource(R.drawable.cl_corners)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                btnBasket.alpha = 1 - slideOffset
            }
        })

        val mealsViewModel = ViewModelProviders.of(this, viewModelFactory).get(MealsViewModel::class.java)
        val intExtra = intent.getIntExtra("idMeal", 0)
        mealsViewModel.getMealsById(intExtra)
        mealsViewModel.getMealLiveData().observe(this, Observer {
            if (it != null) {
                binding.meal = it
                meal = it
                val textWeight = "${it.weight} * $defaultCoef = ${it.weight * defaultCoef} гр."
                tvWeight.text = textWeight
            }
        })

        Handler().postDelayed({
            val intArray = IntArray(2)
            view.getLocationInWindow(intArray)
            view.requestLayout()
            val lp = view.layoutParams as ViewGroup.MarginLayoutParams
            bottomSheetBehavior.peekHeight = intArray[1] + lp.bottomMargin
            bottom_sheet.animation = AnimationUtils.loadAnimation(this, R.anim.anim_in_top)
            bottom_sheet.visibility = View.VISIBLE
            flFavorite.animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
            flFavorite.show()
        }, 150)

        rgSize.setOnCheckedChangeListener { _, checkedId ->
            val coef = when (checkedId) {
                R.id.large -> 2f
                R.id.middle -> defaultCoef
                R.id.small -> 0.75f
                else -> defaultCoef
            }
            val weight = meal.weight
            val textWeight = "$weight * $coef = ${weight * coef} гр."
            tvWeight.text = textWeight
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.flClose -> finish()
            R.id.flFavorite -> {
                if (isFavorite) {
                    flFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star))
                } else {
                    flFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star_yellow))
                }
                isFavorite = !isFavorite
            }
            R.id.ivMinus -> changeCount(false, tvCount, 1)
            R.id.ivPlus -> changeCount(true, tvCount, 1)
            R.id.ivMinusCheese -> changeCount(false, tvCountCheese, 0)
            R.id.ivPlusCheese -> changeCount(true, tvCountCheese, 0)
            R.id.ivMinusMustard -> changeCount(false, tvCountMustard, 0)
            R.id.ivPlusMustard -> changeCount(true, tvCountMustard, 0)
            R.id.ivMinusHot -> changeCount(false, tvCountHot, 0)
            R.id.ivPlusHot -> changeCount(true, tvCountHot, 0)
            R.id.btnBasket -> bottomSheetBehavior.state = STATE_EXPANDED
        }
    }

    private fun changeCount(isPlus: Boolean, view: TextView, minimum: Int) {
        var count = view.text.toString().toInt()
        if (isPlus) {
            count++
        } else if (count > minimum) {
            count--
        }
        view.text = count.toString()
    }

}
