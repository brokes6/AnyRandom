package com.laboratory.anyrandom.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laboratory.anyrandom.R
import com.laboratory.anyrandom.bean.HomeDetailBean
import com.laboratory.anyrandom.override.BlurTransformation
import com.laboratory.anyrandom.util.AnimationUtil
import com.laboratory.anyrandom.util.NORMAL
import com.laboratory.anyrandom.util.VERSE

class HomeAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: MutableList<HomeDetailBean> = ArrayList()
    private var onItemClickLinener: OnItemClickLinener? = null

    override fun getItemViewType(position: Int): Int {
        return when (list[position].itemTypes) {
            2 -> NORMAL
            100 -> VERSE
            else -> NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NORMAL -> {
                HomeRandomViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_viewpager, parent, false)
                )
            }
            VERSE -> {
                HomeViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_verse, parent, false)
                )
            }
            else -> HomeRandomViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            NORMAL -> {
                (holder as HomeRandomViewHolder).let { holder ->
                    Glide.with(holder.itemView)
                        .load(BitmapFactory.decodeFile(list[position].cardImage))
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 20)))
                        .into(holder.image)

                    holder.titleIntroduce.text = list[position].cardIntroduce
                    holder.randomName.text = list[position].cardTitle
                    holder.randomCount.text = "随机循环${list[position].cardRandomCount}次"
                    holder.randomCategory.text = list[position].cardType
                    holder.image.setOnClickListener {
                        onItemClickLinener!!.onClick(it,position)
                    }
                }
            }
            VERSE -> {
                (holder as HomeViewHolder).let {
                    it.tvText.alpha = 0f
                    it.tvAuthor.alpha = 0f
                    it.tvSource.alpha = 0f

                    it.tvText.text = list[position].text
                    it.tvAuthor.text = list[position].author
                    it.tvSource.text = list[position].sources
                    AnimationUtil.fadeIn(it.tvText, 1000, false)
                    AnimationUtil.fadeIn(it.tvAuthor, 1000, false)
                    AnimationUtil.fadeIn(it.tvSource, 1000, false)
                    it.mainCard.setOnClickListener { view ->
                        onItemClickLinener!!.onClick(view,position)
                    }
                }
            }
        }
    }

    fun setData(list: MutableList<HomeDetailBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setData(position: Int, list: HomeDetailBean) {
        this.list[position] = list
        notifyItemInserted(position)
//        notifyItemChanged(position)
    }

    fun addData(list: HomeDetailBean) {
        this.list.add(list)
        notifyDataSetChanged()
    }

    fun getData(): MutableList<HomeDetailBean> {
        return list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickLinener(onItemClickLinener: OnItemClickLinener) {
        this.onItemClickLinener = onItemClickLinener
    }

}

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mainCard: CardView = itemView.findViewById(R.id.mainCard)
    val tvText: TextView = itemView.findViewById(R.id.tvText)
    val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
    val tvSource: TextView = itemView.findViewById(R.id.tvSource)
}

class HomeRandomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)
    val randomCategory: TextView = itemView.findViewById(R.id.randomCategory)
    val randomName: TextView = itemView.findViewById(R.id.randomName)
    val randomCount: TextView = itemView.findViewById(R.id.randomCount)
    val titleIntroduce: TextView = itemView.findViewById(R.id.titleIntroduce)
}

interface OnItemClickLinener {
    fun onClick(item: View, position: Int)
}