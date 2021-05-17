package com.laboratory.anyrandom.viewmolder

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.bean.RandomResultBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _detailData = MutableLiveData<MutableList<HomeDetailBean>>()
    val detailData: LiveData<MutableList<HomeDetailBean>>
        get() = _detailData

    val resultLastData: MutableLiveData<RandomResultBean> = MutableLiveData()
    val resultData: MutableLiveData<MutableList<RandomResultBean>> = MutableLiveData()

    suspend fun getData(context: Context) {
        _detailData.postValue(App.getDatabase(context)?.homeDao?.getHomeData())
    }

    suspend fun getRandomLastResult(context: Context) {
        resultLastData.postValue(App.getDatabase(context)?.randomResultDao?.getLastRandomResult())
    }

    fun getRandomResult(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            resultData.postValue(App.getDatabase(context)?.randomResultDao?.getRandomResultData())
        }
    }
}