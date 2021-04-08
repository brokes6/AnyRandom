package com.laboratory.anyrandom.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RandomResultBean() {

    @PrimaryKey
    var index: Int? = null
    var randomResultTitle: String? = null
    var randomResultCount: Int? = null
    var randomResultIntroduce: String? = null
    var randomResultClass: String? = null

    constructor(
        randomResult: String,
        randomResultCount: Int,
        introduce: String?,
        resultClass: String?
    ) : this() {
        this.randomResultTitle = randomResult
        this.randomResultCount = randomResultCount
        this.randomResultIntroduce = introduce
        this.randomResultClass = resultClass
    }
}