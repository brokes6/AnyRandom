package com.laboratory.anyrandom.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.adapter.HomeAdapter
import com.laboratory.anyrandom.adapter.OnItemClickLinener
import com.laboratory.anyrandom.base.BaseActivity
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.databinding.ActivityMainBinding
import com.laboratory.anyrandom.eventbus.MessageWrap
import com.laboratory.anyrandom.util.FoyouLibrary
import com.laboratory.anyrandom.viewmolder.HomeViewModel
import github.hellocsl.layoutmanager.gallery.GalleryLayoutManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


class MainActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ImmersionBar.with(this)
            .transparentStatusBar()
            .statusBarDarkFont(true)
            .titleBarMarginTop(binding.userImage)
            .init()
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    fun onRefreshCard(message: MessageWrap) {
        if (message.message == 100) {
            viewModel.getData(this)
        } else if (message.message == 300) {
            viewModel.getRandomLastResult(this)
        }
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
        adapter = HomeAdapter().also {
            binding.viewPager.adapter = it
            it.setOnItemClickLinener(object : OnItemClickLinener {
                override fun onClick(item: View, position: Int) {
                    if (item.id == R.id.mainCard) {
                        FoyouLibrary.getSentence().let { data ->
                            it.setData(
                                position,
                                HomeDetailBean(data.text, data.author, data.source, 100)
                            )
                        }
                    } else {
                        val intent = Intent(this@MainActivity, PhotoDetailActivity::class.java)
                        intent.putExtra(
                            "image",
                            it.getData()[position].cardImage
                        )
                        intent.putExtra("index", position)
                        intent.putExtra("title", it.getData()[position].cardTitle)
                        startActivity(intent)
                    }
                }

            })
        }
        EventBus.getDefault().register(this)
        binding.addRandomItem.setOnClickListener(this)
        binding.userImage.setOnClickListener(this)
    }

    override fun initData() {
        binding.dataTime.text = SimpleDateFormat("EEEE").format(Date(System.currentTimeMillis()))
        binding.dataTimeDetail.text =
            SimpleDateFormat("MM月dd日").format(Date(System.currentTimeMillis()))
        viewModel.also {
            it.getData(this)
            it.getRandomLastResult(this)
            it.detailData.observe(this, { itHome ->
                FoyouLibrary.getSentence().let { data ->
                    itHome.add(HomeDetailBean(data.text, data.author, data.source, 100))
                }
                adapter.setData(itHome)
            })
            it.resultLastData.observe(this, { itResult ->
                if (itResult == null) {
                    binding.randomResult.text = "还没有运行结果哦~"
                } else {
                    binding.randomResult.text = "上次随机结果: ${itResult.randomResultTitle} (๑•̀ㅂ•́)و✧"
                }
            })
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.addRandomItem -> {
                startActivity(Intent(this, AddCardActivity::class.java))
            }
            R.id.userImage -> {
                startActivity(Intent(this, PersonsActivity::class.java))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}