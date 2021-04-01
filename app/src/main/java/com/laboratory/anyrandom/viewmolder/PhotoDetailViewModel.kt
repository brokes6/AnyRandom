package com.laboratory.anyrandom.viewmolder

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laboratory.anyrandom.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotoDetailViewModel : ViewModel() {
    private var randomCount: Int = 0
    private var randomName: List<String>? = arrayListOf()
    private val _randomString = MutableLiveData<List<String>>()
    val randomString: LiveData<List<String>>
        get() = _randomString

    fun getRandom(context: Context, index: Int) {
        GlobalScope.launch {
            if (App.getDatabase(context)?.randomDao?.getRandomData(index)?.size!! <= 0) return@launch
            randomName =
                App.getDatabase(context)?.randomDao?.getRandomData(index)?.get(0)?.randomName?.split(
                    ","
                )
            randomCount = App.getDatabase(context)?.randomDao?.getRandomData(index)?.get(0)?.randomNum!!
            val stringList: ArrayList<String> = arrayListOf()
            for (name in 1..randomCount) {
                stringList.add(randomName!![(randomName!!.indices).random()])
            }
            _randomString.postValue(stringList)
        }

    }

    fun getRandomCount(): Int {
        return randomCount
    }

    fun setRandomCount(value: Int) {
        this.randomCount = value
    }

    fun getRandomName(): List<String>? {
        return randomName
    }
}