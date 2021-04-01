package com.laboratory.anyrandom.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HomeDetailBean() {
    @PrimaryKey
    var cardId: Int? = null
    var cardTitle: String? = null
    var cardType: String? = null
    var cardRandomCount: Int? = null
    var cardImage: String? = null
    var cardIntroduce: String? = null

    constructor(
        title: String,
        count: Int,
        type: String,
        introduce: String,
        image: String
    ) : this() {
        this.cardTitle = title
        this.cardRandomCount = count
        this.cardType = type
        this.cardIntroduce = introduce
        this.cardImage = image
    }
}