package com.laboratory.anyrandom.util

import com.laboratory.anyrandom.bean.VerseBean

/**
 * Foyou 库
 */
object FoyouLibrary {

    const val VERSION = "4.0.3"

    fun getSentence(): VerseBean {
        val sentence = when ((1..3).random()) {
            1 -> FoyouPoetry.getFoyouPoetry()
            2 -> FoyouLiterature.getLiterature()
            3 -> FoyouOthers.getFoyouOthers()
            else -> FoyouPoetry.getFoyouPoetry()
        }
        val text = OptimizeHitokoto.sentenceTextNewLine(sentence.text)
        return VerseBean(text, sentence.author, sentence.source)
    }

}