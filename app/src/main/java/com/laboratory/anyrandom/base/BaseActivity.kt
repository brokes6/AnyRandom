package com.laboratory.anyrandom.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun onCreateView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateView(savedInstanceState)
        initView()
        initData()
    }
}