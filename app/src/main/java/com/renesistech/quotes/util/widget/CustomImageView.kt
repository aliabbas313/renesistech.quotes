package com.renesistech.quotes.util.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.renesistech.quotes.R

class CustomImageView : ImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeCustomImageView(attrs, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initializeCustomImageView(attrs, context)
    }

    private fun initializeCustomImageView(attrs: AttributeSet?, context: Context) {
        if (attrs != null) {

            val attributesArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomImageView)

            var drawable: Drawable?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = attributesArray.getDrawable(R.styleable.CustomImageView_my_src_compat)
                setImageDrawable(drawable)
            } else {
                val drawableId = attributesArray.getResourceId(R.styleable.CustomImageView_my_src_compat, -1)
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                    setBackgroundDrawable(context.resources.getDrawable(drawableId))
                } else if(drawableId != 0) {
                    setBackgroundDrawable(VectorDrawableCompat.create(context.resources, drawableId,null))

//                    drawable = VectorDrawableCompat.create(context.resources, drawableId, null)
//                    setImageResource(drawableId)
//                    setBackgroundDrawable(drawable)
//                    setImageDrawable(drawable)
                }

            }


            attributesArray.recycle()
        }
    }

    companion object {

        private val LOG_TAG = "CustomImageView"
    }

}