package com.laboratory.anyrandom.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.bean.RandomResultBean
import com.laboratory.anyrandom.override.BlurTransformation

class RamdomResultAdapter :
    BaseQuickAdapter<RandomResultBean, BaseViewHolder>(R.layout.item_random_result) {

    override fun convert(holder: BaseViewHolder, item: RandomResultBean) {
        holder.setText(R.id.randomResultTitle, item.randomResultIntroduce)
        holder.setText(R.id.randomResultIntroduce, item.randomResultTitle)
        holder.setText(R.id.randomResultCount, item.randomResultCount.toString())
        Glide.with(holder.itemView).load(item.randomResultClass)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 70)))
            .into(holder.getView(R.id.cardImage))
        //holder.setText(R.id.randomResultClass, item.randomResultClass)
    }
}