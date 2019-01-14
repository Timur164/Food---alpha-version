package com.libra.food.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.libra.food.R
import com.libra.food.data.entities.Meal
import com.squareup.picasso.Picasso

@BindingAdapter("setBackground")
fun setBackground(view: ImageView, meal: Meal?) {
    if (meal != null) {
        val drawable = when (meal.type) {
            1 -> view.context.getDrawable(R.drawable.ic_combo)
            2 -> view.context.getDrawable(R.drawable.ic_pizza)
            3 -> view.context.getDrawable(R.drawable.ic_rolls)
            else -> view.context.getDrawable(R.drawable.ic_pizza)
        }
        if (drawable != null) {
            Picasso.get().load(meal.image)
                    .error(drawable)
                    .into(view)
        }
    }
}


@BindingAdapter("setVisibility")
fun setVisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }

}