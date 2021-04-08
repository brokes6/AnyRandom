package com.laboratory.anyrandom.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserBean() {
    @PrimaryKey
    var userId : Int? = null
    var userName: String? = null
    var imageUri: String? = null

    constructor(name: String, uri: String) : this() {
        this.userName = name
        this.imageUri = uri
    }
}