package com.laboratory.anyrandom.viewmolder

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.bean.DetailBean
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.bean.RandomResultBean
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _detailData = MutableLiveData<MutableList<HomeDetailBean>>()
    val detailData: LiveData<MutableList<HomeDetailBean>>
        get() = _detailData

    val resultLastData : MutableLiveData<RandomResultBean> = MutableLiveData()
    val resultData : MutableLiveData<MutableList<RandomResultBean>> = MutableLiveData()

    fun getData(context: Context) {
        GlobalScope.launch {
            _detailData.postValue(App.getDatabase(context)?.homeDao?.getHomeData())
        }
    }

    fun getRandomLastResult(context: Context){
        GlobalScope.launch {
            resultLastData.postValue(App.getDatabase(context)?.randomResultDao?.getLastRandomResult())
        }
    }

    fun getRandomResult(context: Context){
        GlobalScope.launch {
            resultData.postValue(App.getDatabase(context)?.randomResultDao?.getRandomResultData())
        }
    }
}