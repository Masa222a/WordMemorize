package com.android.example.wordmemorize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.android.example.wordmemorize.databinding.ActivityMainBinding
import io.realm.Realm


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding
        setContentView(binding.root)

        binding.start.setOnClickListener {
            val wordRecords = RealmManager().getWords()

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
        Realm.getDefaultInstance().close()
    }
}