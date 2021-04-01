package com.laboratory.anyrandom.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RandomBean() {
    @PrimaryKey
    var index: Int? = null
    var randomName: String? = null
    var randomNum: Int = 0

    constructor(index: Int, randomName: String, randomNum: Int) : this() {
        this.index = index
        this.randomNum = randomNum
        this.randomName = randomName
    }
}

