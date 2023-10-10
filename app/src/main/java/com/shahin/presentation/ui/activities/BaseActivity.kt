package com.shahin.presentation.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding>(bindingInflater: (LayoutInflater) -> T): AppCompatActivity() {

    protected val binding by lazy(LazyThreadSafetyMode.PUBLICATION) {
        bindingInflater.invoke(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}
