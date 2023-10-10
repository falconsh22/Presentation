package com.shahin.presentation.extensions

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.shahin.presentation.R
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import reactivecircus.flowbinding.common.checkMainThread
import reactivecircus.flowbinding.common.safeOffer

/**
 * extension function to set view visibility as VISIBLE
 */
fun <T: View> T.visible() {
    visibility = View.VISIBLE
}

/**
 * extension function to set view visibility as GONE
 */
fun <T: View> T.gone() {
    visibility = View.GONE
}

/**
 * extension function to set view visibility as INVISIBLE
 */
fun <T: View> T.hide() {
    visibility = View.INVISIBLE
}

/**
 * extension function to set view visibility as VISIBLE or GONE
 */
fun <T: View> T.visibleOrGone(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}

/**
 * extension function to set view visibility as VISIBLE or INVISIBLE
 */
fun <T: View> T.visibleOrHide(show: Boolean) {
    if (show) {
        visible()
    } else {
        hide()
    }
}

/**
 * extension function to register click listener on a view
 */
fun <T : View> T.onClick(block: (View) -> Unit) {
    return setOnClickListener {
        block.invoke(this)
    }
}

fun TextView.setTextColorOnBackground(@ColorInt backgroundColor: Int) {
    if (backgroundColor.isDark()) {
        setTextColor(ContextCompat.getColor(this.context, R.color.primary_light))
    } else {
        setTextColor(ContextCompat.getColor(this.context, R.color.primary_dark))
    }
}

fun SearchView.onTextChanges(): Flow<String?> =
    callbackFlow {
        checkMainThread()
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                safeOffer(newText)
                return true
            }
        }
        setOnQueryTextListener(listener)
        awaitClose { setOnQueryTextListener(null) }
    }
