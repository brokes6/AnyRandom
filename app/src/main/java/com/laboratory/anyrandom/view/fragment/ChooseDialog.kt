package com.laboratory.anyrandom.view.fragment

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.laboratory.anyrandom.R

class ChooseDialog(context: Context) : Dialog(context, R.style.dialog) {
    private lateinit var onDialogItemClickListener: OnDialogItemClickListener

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_choose_dialog, null, false)
        setContentView(view)

        val deleteCard = view.findViewById<TextView>(R.id.sharePhoto)
        val cencel = view.findViewById<TextView>(R.id.downloadPhoto)
        deleteCard.setOnClickListener {
            onDialogItemClickListener.itemClick()
        }
        cencel.setOnClickListener {
            dismiss()
        }

        val dialogWindow = window
        dialogWindow!!.setGravity(Gravity.CENTER)
        val lp = dialogWindow.attributes
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        //设置弹窗宽度
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        dialogWindow.attributes = lp
    }

    fun setDialogOnClickListener(listener: OnDialogItemClickListener) {
        onDialogItemClickListener = listener
    }

}

interface OnDialogItemClickListener {
    fun itemClick()
}