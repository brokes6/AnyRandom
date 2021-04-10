package com.laboratory.anyrandom.eventbus

const val REFRESH: Int = 100
const val NOTHING: Int = 200
const val REFRESHNUM: Int = 300

class MessageWrap(message: Int) {
    var message: Int = message
}