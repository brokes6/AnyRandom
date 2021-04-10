package com.laboratory.anyrandom.adapter

import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.bean.RandomResultBean
import com.laboratory.anyrandom.eventbus.MessageWrap
import com.laboratory.anyrandom.eventbus.REFRESHNUM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class PhotoDetailAdapter(private val title: String, private val ImageUri: String) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_random) {
    private var selectedIndex: Int? = null

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.randomIndex, (holder.adapterPosition + 1).toString())
        holder.setText(R.id.randomTile, item)
        if (selectedIndex == holder.adapterPosition) {
            holder.getView<RelativeLayout>(R.id.main)
                .setBackgroundResource(R.drawable.file_buttonstyle_background)
            setDataBase(item, holder)
        } else {
            holder.getView<RelativeLayout>(R.id.main)
                .setBackgroundResource(0)
        }
    }

    private fun setDataBase(
        item: String,
        holder: BaseViewHolder
    ) {
        GlobalScope.launch {
            App.getDatabase(context)?.randomResultDao?.also {
                if (it.getRandomResultData().size >= 50) {
                    it.deleteFirstData()
                    it.insertAll(
                        RandomResultBean(
                            item,
                            holder.adapterPosition,
                            title,
                            ImageUri
                        )
                    )
                } else {
                    it.insertAll(
                        RandomResultBean(
                            item,
                            holder.adapterPosition,
                            title,
                            ImageUri
                        )
                    )
                }
            }
            EventBus.getDefault().post(MessageWrap(REFRESHNUM))
        }
    }

    fun selectedIndex(value: Int) {
        this.selectedIndex = value
        notifyDataSetChanged()
    }
}