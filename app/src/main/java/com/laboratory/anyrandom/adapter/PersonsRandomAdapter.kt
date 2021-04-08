package com.laboratory.anyrandom.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.override.BlurTransformation

const val ADD: Int = 1
const val NORMAL: Int = 2

class PersonsRandomAdapter :
    BaseMultiItemQuickAdapter<HomeDetailBean, BaseViewHolder>() {

    init {
        addItemType(ADD, R.layout.item_random_top_add)
        addItemType(NORMAL, R.layout.item_persons_random)
    }

    override fun convert(holder: BaseViewHolder, item: HomeDetailBean) {
        when (holder.itemViewType) {
            ADD -> {

            }
            NORMAL -> {
                //holder.setText(R.id.cardRandomTitle, item.cardIntroduce)
                holder.setText(R.id.cardRandomResult,item.cardTitle)
                holder.setText(R.id.cardRandomCount, "循环${item.cardRandomCount.toString()}次")
                holder.setText(R.id.cardRandomClass, item.cardType)
                Glide.with(holder.itemView).load(item.cardImage)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 20)))
                    .into(holder.getView(R.id.cardImage))
            }
        }

    }


}