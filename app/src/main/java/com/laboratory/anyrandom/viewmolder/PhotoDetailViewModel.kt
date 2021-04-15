package com.laboratory.anyrandom.viewmolder

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.bean.RandomBean
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotoDetailViewModel : ViewModel() {
    private var randomCount: Int = 0
    private var randomName: List<String>? = arrayListOf()
    private val _randomString = MutableLiveData<List<String>>()
    val randomString: LiveData<List<String>>
        get() = _randomString
    private var imageUri: String? = null
    private var randomTitle: String? = null

    fun getRandom(context: Context, index: Int) {
        GlobalScope.launch {
            if (App.getDatabase(context)?.randomDao?.getRandomData(index)?.size!! <= 0) return@launch
            randomName =
                App.getDatabase(context)?.randomDao?.getRandomData(index)
                    ?.get(0)?.randomName?.split(
                        ","
                    )
            randomCount =
                App.getDatabase(context)?.randomDao?.getRandomData(index)?.get(0)?.randomNum!!
            val stringList: ArrayList<String> = arrayListOf()
            for (name in 1..randomCount) {
                stringList.add(randomName!![(randomName!!.indices).random()])
            }
            _randomString.postValue(stringList)
        }
    }

    fun insertUP(context: Context, index: Int, numText: String, countText: Int) {
        GlobalScope.launch {
            App.getDatabase(context)?.randomDao?.getRandomData(index).let {
                if (it?.size == 0) {
                    App.getDatabase(context)?.randomDao?.insertAll(
                        RandomBean(
                            index,
                            numText,
                            countText
                        )
                    )
                } else {
                    App.getDatabase(context)?.randomDao?.upData(
                        RandomBean(
                            index,
                            numText,
                            countText
                        )
                    )
                }
                getRandom(context, index)
            }
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

    fun setImageUri(image: String) {
        this.imageUri = image
    }

    fun getImageUri(): String? {
        return imageUri
    }

    fun setRandomTitle(title: String) {
        this.randomTitle = title
    }

    fun getRandomTitle(): String? {
        return randomTitle
    }
}