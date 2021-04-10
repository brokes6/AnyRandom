package com.laboratory.anyrandom.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.adapter.PhotoDetailAdapter
import com.laboratory.anyrandom.base.BaseFragment
import com.laboratory.anyrandom.databinding.FragmentRandomDetailBinding
import com.laboratory.anyrandom.override.RecyclerViewScrollHelper
import com.laboratory.anyrandom.view.activity.PhotoDetailActivity
import com.laboratory.anyrandom.viewmolder.PhotoDetailViewModel

class RandomDetailFragment(private val index: Int) : BaseFragment() {
    private lateinit var binding: FragmentRandomDetailBinding
    private lateinit var adapter: PhotoDetailAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: PhotoDetailViewModel

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_random_detail, container, false)
        return binding.root
    }

    override fun initView() {
        viewModel =
            ViewModelProvider(activity as PhotoDetailActivity).get(PhotoDetailViewModel::class.java)
        adapter = PhotoDetailAdapter(viewModel.getRandomTitle()!!, viewModel.getImageUri()!!)
        layoutManager = LinearLayoutManager(context)

        binding.randomItem.adapter = adapter
        binding.randomItem.layoutManager = layoutManager
        binding.startRandom.setOnClickListener {
            val index = (0..viewModel.getRandomCount()).random()
            RecyclerViewScrollHelper.scrollToPosition(binding.randomItem, index)
            adapter.selectedIndex(index)
        }

    }

    override fun initData() {
        viewModel.getRandom(requireContext(), index)

        viewModel.randomString.observe(this, {
            adapter.setList(it)
        })
    }
}