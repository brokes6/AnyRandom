package com.laboratory.anyrandom.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.base.BaseActivity
import com.laboratory.anyrandom.databinding.ActivityPersonsBinding

class PersonsActivity : BaseActivity() {
    private lateinit var binding : ActivityPersonsBinding


    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_persons)
    }

    override fun initView() {

    }

    override fun initData() {

    }

}