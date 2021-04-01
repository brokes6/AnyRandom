package com.laboratory.anyrandom.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RandomResultBean() {

    @PrimaryKey
    var index: Int? = null
    var randomResult: String? = null
    var randomResultCount: Int? = null

    constructor(index : Int,randomResult: String, randomResultCount: Int) : this(){
        this.index = index
        this.randomResult = randomResult
        this.randomResultCount = randomResultCount
    }
}