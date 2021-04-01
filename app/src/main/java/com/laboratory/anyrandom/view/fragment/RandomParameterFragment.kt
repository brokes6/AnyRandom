package com.laboratory.anyrandom.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.base.BaseFragment
import com.laboratory.anyrandom.bean.RandomBean
import com.laboratory.anyrandom.databinding.FragmentRandomParameterBinding
import com.laboratory.anyrandom.view.activity.PhotoDetailActivity
import com.laboratory.anyrandom.viewmolder.PhotoDetailViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RandomParameterFragment(private val index: Int) : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentRandomParameterBinding
    private lateinit var viewModel: PhotoDetailViewModel

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_random_parameter, container, false)
        return binding.root
    }

    override fun initView() {
        viewModel =
            ViewModelProvider(activity as PhotoDetailActivity).get(PhotoDetailViewModel::class.java)
        binding.Refresh.setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.Refresh -> {
                if (binding.RandomNameInput.text.toString().trim().isNotEmpty()
                    &&
                    binding.RandomCountInput.text.toString().trim().isNotEmpty()
                ) {
                    GlobalScope.launch {
                        App.getDatabase(requireContext())?.randomDao?.insertAll(
                            RandomBean(
                                index,
                                binding.RandomNameInput.text.toString(),
                                binding.RandomCountInput.text.toString().toInt()
                            )
                        )
                        viewModel.getRandom(requireContext(), index)
                    }
                    Toast.makeText(context, "刷新成功！", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "请将参数填完哦！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}