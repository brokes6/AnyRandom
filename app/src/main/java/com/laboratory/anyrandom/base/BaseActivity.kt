package com.laboratory.anyrandom.base

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun onCreateView(savedInstanceState: Bundle?)

    protected abstract fun initView()

    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateView(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImmersionBar.with(this)
                .transparentNavigationBar()
                .init()
        }

        initView()
        initData()
    }
}