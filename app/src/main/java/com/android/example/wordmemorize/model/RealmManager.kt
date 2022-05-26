package com.android.example.wordmemorize.model

import io.realm.Realm
import io.realm.Realm.*
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class RealmManager {
    fun getWords(): RealmResults<Word> {
        return getDefaultInstance()
        .where<Word>()
        .findAll()
    }

    fun saveWord(en: String, jp: String) {
        getDefaultInstance().executeTransaction { db: Realm ->
            val maxId = db.where<Word>().max("id")
            val nextId = (maxId?.toLong() ?: 0L) + 1L
            val words = db.createObject<Word>(nextId)
            words.word = en
            words.meaning = jp
        }
    }
}