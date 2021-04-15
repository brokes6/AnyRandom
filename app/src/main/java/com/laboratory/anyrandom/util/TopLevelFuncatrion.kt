package com.laboratory.anyrandom.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.laboratory.anyrandom.App

const val VERSE : Int = 100
const val RANDOM : Int = 200
const val ADD: Int = 1
const val NORMAL: Int = 2

fun toast(msg: String) {
    runOnMainThread {
        Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show()
    }
}

fun runOnMainThread(runnable: Runnable) {
    Handler(Looper.getMainLooper()).post(runnable)
}

/**
 * 判断是否是中文字符
 */
fun Char.isChinese(): Boolean {
    val unicodeBlock = Character.UnicodeBlock.of(this)
    if (unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
        || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
    ) // 中日韩象形文字
    {
        return true
    }
    return false
}
