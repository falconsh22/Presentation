package com.shahin.presentation.ui.activities.main

import android.os.Bundle
import com.shahin.presentation.databinding.ActivityMainBinding
import com.shahin.presentation.ui.activities.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
