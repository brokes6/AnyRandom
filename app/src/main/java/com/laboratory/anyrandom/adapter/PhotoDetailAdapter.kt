package com.laboratory.anyrandom.adapter

import android.widget.RelativeLayout
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.laboratory.anyrandom.App
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.bean.RandomResultBean
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


        }
    }

    fun selectedIndex(value: Int) {
        this.selectedIndex = value
        notifyDataSetChanged()
    }
}

class DiffDemoCallback : DiffUtil.ItemCallback<String>() {
    /**
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem

    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return (oldItem == newItem)
    }

    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     *
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    override fun getChangePayload(oldItem: String, newItem: String): Any? {
        return null
    }
}