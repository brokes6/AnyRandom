package com.laboratory.anyrandom.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.adapter.PersonsRandomAdapter
import com.laboratory.anyrandom.adapter.RamdomResultAdapter
import com.laboratory.anyrandom.base.BaseActivity
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.databinding.ActivityPersonsBinding
import com.laboratory.anyrandom.eventbus.MessageWrap
import com.laboratory.anyrandom.eventbus.REFRESH
import com.laboratory.anyrandom.view.fragment.ChooseDialog
import com.laboratory.anyrandom.view.fragment.OnDialogItemClickListener
import com.laboratory.anyrandom.viewmolder.HomeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class PersonsActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPersonsBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_persons)

        ImmersionBar.with(this)
            .statusBarColor(R.color.White)
            .fitsSystemWindows(true)
            .statusBarDarkFont(true)
            .init()
    }

    override fun initView() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val adapter = PersonsRandomAdapter().also {
            binding.userRandomList.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.userRandomList.adapter = it
            it.addChildClickViewIds(R.id.cardAdd)
            it.addChildClickViewIds(R.id.mainCrad)
            it.addChildLongClickViewIds(R.id.mainCrad)
            it.setOnItemChildClickListener { adapter, _, position ->
                if (position == 0) {
                    startActivity(Intent(this, AddCardActivity::class.java))
                } else {
                    Intent(this, PhotoDetailActivity::class.java).also { intent ->
                        intent.putExtra(
                            "image",
                            (adapter.data[position] as HomeDetailBean).cardImage
                        )
                        intent.putExtra("index", position - 1)
                        intent.putExtra(
                            "title",
                            (adapter.data[position] as HomeDetailBean).cardTitle
                        )
                        startActivity(intent)
                    }
                }
            }
            it.setOnItemChildLongClickListener { _, _, position ->
                ChooseDialog(this, position, viewModel).also { dialog ->
                    dialog.setDialogOnClickListener(object : OnDialogItemClickListener {
                        override fun itemClick() {
                            GlobalScope.launch {
                                App.getDatabase(this@PersonsActivity)?.homeDao?.deleteId(position)
                                App.getDatabase(this@PersonsActivity)?.randomDao?.deleteId(position - 1)
                                viewModel.getData(this@PersonsActivity)
                                dialog.dismiss()
                                EventBus.getDefault().post(MessageWrap(REFRESH))
                            }
                            Toast.makeText(this@PersonsActivity, "删除成功~", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                    dialog.show()
                }

                false
            }
        }
        val randomResult = RamdomResultAdapter().also {
            binding.userRandomResult.layoutManager = LinearLayoutManager(this)
            binding.userRandomResult.adapter = it
        }
        viewModel.detailData.observe(this, {
            it.add(0, HomeDetailBean(null, null, null, null, null, 1))
            adapter.setList(it)
        })
        viewModel.resultData.observe(this, {
            for (data in it) {
                randomResult.addData(0, data)
            }
        })
        binding.back.setOnClickListener(this)
    }

    override fun initData() {
        viewModel.getData(this)
        viewModel.getRandomResult(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.back -> {
                finish()
            }
        }
    }

}