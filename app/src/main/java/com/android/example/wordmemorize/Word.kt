package com.android.example.wordmemorize

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Word : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var word: String = ""
    var meaning: String = ""
}