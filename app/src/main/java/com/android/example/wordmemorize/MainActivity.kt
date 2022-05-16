package com.android.example.wordmemorize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.android.example.wordmemorize.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding
        setContentView(binding.root)

        realm = Realm.getDefaultInstance()

        binding.start.setOnClickListener {
            val wordRecords = realm.where<Word>().findAll()

            if (wordRecords.isEmpty()) {
                val dialog = AlertDialog.Builder(this)
                    .setMessage("単語を追加してください")
                    .setPositiveButton("OK") { dialog, which -> }
                    .show()
            } else {

                binding.start.isVisible = false
                binding.addWord.isVisible = false
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.container, StudyFragment())
                    addToBackStack(null)
                    commit()
                }
            }
        }

        binding.addWord.setOnClickListener {
            binding.start.isVisible = false
            binding.addWord.isVisible = false

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, AddWordFragment())
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}