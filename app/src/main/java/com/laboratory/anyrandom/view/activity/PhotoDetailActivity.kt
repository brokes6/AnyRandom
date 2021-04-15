package com.laboratory.anyrandom.view.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.angcyo.tablayout.delegate.ViewPager1Delegate
import com.bumptech.glide.Glide
import com.gyf.immersionbar.ImmersionBar
import com.laboratory.anyrandom.adapter.HomeTitleAdapter
import com.laboratory.anyrandom.base.BaseActivity
import com.laboratory.anyrandom.databinding.ActivityPhotoDetailBinding
import com.laboratory.anyrandom.view.fragment.RandomDetailFragment
import com.laboratory.anyrandom.view.fragment.RandomParameterFragment
import com.laboratory.anyrandom.viewmolder.PhotoDetailViewModel

class PhotoDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityPhotoDetailBinding
    private var fragmentList: ArrayList<Fragment> = arrayListOf()
    private lateinit var viewModel: PhotoDetailViewModel

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = ActivityPhotoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ImmersionBar.with(this)
            .transparentStatusBar()
//            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .titleBarMarginTop(binding.randomTab)
            .init()
    }

    override fun initView() {
        viewModel = ViewModelProvider(this).get(PhotoDetailViewModel::class.java)
        fragmentList.add(RandomDetailFragment(intent.getIntExtra("index", 0)))
        fragmentList.add(RandomParameterFragment(intent.getIntExtra("index", 0)))
        binding.photoViewPage.adapter =
            HomeTitleAdapter(supportFragmentManager, fragmentList)
        ViewPager1Delegate.install(binding.photoViewPage, binding.photoTab)
        binding.randomTab.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initData() {
        Glide.with(this).load(BitmapFactory.decodeFile(intent.getStringExtra("image")!!))
//            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 20)))
            .into(binding.randomImage)
        viewModel.setRandomTitle(intent.getStringExtra("title")!!)
        viewModel.setImageUri(intent.getStringExtra("image")!!)

        binding.randomTab.title = intent.getStringExtra("title")!!
    }
}