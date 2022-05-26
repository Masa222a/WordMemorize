package com.android.example.wordmemorize.model

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class WordMemorizeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true).build()
        Realm.setDefaultConfiguration(config)
    }
}