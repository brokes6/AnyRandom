package com.laboratory.anyrandom.view.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.angcyo.tablayout.delegate.ViewPager1Delegate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gyf.immersionbar.ImmersionBar
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.adapter.HomeTitleAdapter
import com.laboratory.anyrandom.base.BaseActivity
import com.laboratory.anyrandom.databinding.ActivityPhotoDetailBinding
import com.laboratory.anyrandom.override.BlurTransformation
import com.laboratory.anyrandom.view.fragment.RandomDetailFragment
import com.laboratory.anyrandom.view.fragment.RandomParameterFragment

class PhotoDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityPhotoDetailBinding
    private var fragmentList: ArrayList<Fragment> = arrayListOf()

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail)

        ImmersionBar.with(this)
            .transparentStatusBar()
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .titleBarMarginTop(binding.photoTab)
            .init()
    }

    override fun initView() {
        fragmentList.add(RandomDetailFragment(intent.getIntExtra("index", 0)))
        fragmentList.add(RandomParameterFragment(intent.getIntExtra("index", 0)))
        binding.photoViewPage.adapter =
            HomeTitleAdapter(supportFragmentManager, fragmentList)
        ViewPager1Delegate.install(binding.photoViewPage, binding.photoTab)
    }

    override fun initData() {
        Glide.with(this).load(BitmapFactory.decodeFile(intent.getStringExtra("image")))
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 70)))
            .into(binding.mainView)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}