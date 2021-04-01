package com.laboratory.anyrandom.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.adapter.DetailAdapter
import com.laboratory.anyrandom.base.BaseActivity
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.databinding.ActivityMainBinding
import com.laboratory.anyrandom.viewmolder.HomeViewModel
import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


class MainActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: DetailAdapter

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ImmersionBar.with(this)
            .transparentStatusBar()
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .titleBarMarginTop(binding.search)
            .init()
    }

    override fun initView() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val manager = GalleryLayoutManager(GalleryLayoutManager.VERTICAL)
        manager.setItemTransformer { _, item, fraction ->
            item.pivotX = item.width / 1.5f
            item.pivotY = item.height / 1.5f
            val scale = 1 - 0.100f * abs(fraction)
            item.scaleX = scale
            item.scaleY = scale
        }
        manager.attach(binding.viewPager, 0)
        adapter = DetailAdapter().also {
            binding.viewPager.adapter = it
            it.addChildClickViewIds(R.id.image)
        }
        adapter.setOnItemChildClickListener { adapter, _, position ->
            val intent = Intent(this, PhotoDetailActivity::class.java)
            intent.putExtra("image", (adapter.data[position] as HomeDetailBean).cardImage)
            intent.putExtra("index", position)
            startActivity(intent)
        }
        binding.dataTime.text = SimpleDateFormat("EEEE").format(Date(System.currentTimeMillis()))
        binding.dataTimeDetail.text =
            SimpleDateFormat("MM月dd日").format(Date(System.currentTimeMillis()))
        binding.addRandomItem.setOnClickListener(this)
        binding.refreshRandom.setOnClickListener(this)
    }

    override fun initData() {
        viewModel.also {
            it.getData(this)
            it.getRandomResult(this)
            it.detailData.observe(this, { itHome ->
                adapter.setList(itHome)
            })
            it.resultData.observe(this, { itResult ->
                if (itResult.size == 0) {
                    binding.randomResult.text = "还没有运行结果哦~"
                } else {
                    binding.randomResult.text = "上次随机结果: ${itResult[0].randomResult} (๑•̀ㅂ•́)و✧"
                }
            })
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.addRandomItem -> {
                startActivity(Intent(this, AddCardActivity::class.java))
            }
            R.id.refreshRandom -> {
                viewModel.getData(this)
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getRandomResult(this)
    }
}