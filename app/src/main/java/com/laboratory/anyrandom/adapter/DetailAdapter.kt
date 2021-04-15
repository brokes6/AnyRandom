package com.laboratory.anyrandom.adapter


import android.graphics.BitmapFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.override.BlurTransformation

class DetailAdapter :
    BaseQuickAdapter<HomeDetailBean, BaseViewHolder>(R.layout.item_viewpager) {

    override fun convert(holder: BaseViewHolder, item: HomeDetailBean) {

        Glide.with(holder.itemView)
            .load(BitmapFactory.decodeFile(item.cardImage))
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 20)))
            .into(holder.getView(R.id.image))
        holder.setText(R.id.titleIntroduce, item.cardIntroduce)
        holder.setText(R.id.randomName, item.cardTitle)
        holder.setText(R.id.randomCount, "随机循环${item.cardRandomCount}次")
        holder.setText(R.id.randomCategory, item.cardType)
    }
}
