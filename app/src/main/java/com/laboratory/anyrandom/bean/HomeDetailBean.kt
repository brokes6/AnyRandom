package com.laboratory.anyrandom.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chad.library.adapter.base.entity.MultiItemEntity

@Entity
class HomeDetailBean() : MultiItemEntity {
    @PrimaryKey
    var cardId: Int? = null
    var cardTitle: String? = null
    var cardType: String? = null
    var cardRandomCount: Int? = null
    var cardImage: String? = null
    var cardIntroduce: String? = null
    var itemTypes : Int? = null

    constructor(
        title: String?,
        count: Int?,
        type: String?,
        introduce: String?,
        image: String?,
        itemTypes : Int
    ) : this() {
        this.cardTitle = title
        this.cardRandomCount = count
        this.cardType = type
        this.cardIntroduce = introduce
        this.cardImage = image
        this.itemTypes = itemTypes
    }

    override val itemType: Int
        get() = itemTypes!!
}