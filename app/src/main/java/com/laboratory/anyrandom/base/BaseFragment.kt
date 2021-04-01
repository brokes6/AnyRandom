package com.laboratory.anyrandom.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var isFirstLoad = true
    private var mView: View? = null

    protected abstract fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    protected abstract fun initView()

    protected abstract fun initData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            mView = createView(inflater, container, savedInstanceState)
            initView()
            initData()
        }
        return mView
    }

//    override fun onResume() {
//        super.onResume()
//        if (isFirstLoad) {
//            initData()
//            isFirstLoad = false
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = true
    }

}