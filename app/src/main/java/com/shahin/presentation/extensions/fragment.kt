package com.shahin.presentation.extensions

import android.graphics.Color
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

fun Fragment.setMaterialSharedElementTransition() {
    sharedElementEnterTransition = MaterialContainerTransform().apply {
        scrimColor = Color.TRANSPARENT
        fadeMode = MaterialContainerTransform.FADE_MODE_IN
        interpolator = DecelerateInterpolator(1.2f)
        setPathMotion(MaterialArcMotion())
        setAllContainerColors(Color.TRANSPARENT)
    }
}
