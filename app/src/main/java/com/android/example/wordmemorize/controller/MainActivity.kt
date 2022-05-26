package com.android.example.wordmemorize.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.android.example.wordmemorize.model.RealmManager
import com.android.example.wordmemorize.R
import com.android.example.wordmemorize.view.AddWordFragment
import com.android.example.wordmemorize.view.StudyFragment
import com.android.example.wordmemorize.view.WordListFragment
import com.android.example.wordmemorize.databinding.ActivityMainBinding
import io.realm.Realm


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding
        setContentView(binding.root)

        binding.start.setOnClickListener {
            val wordRecords = RealmManager().getWords()

            if (wordRecords.isEmpty()) {
                AlertDialog.Builder(this)
                    .setMessage("単語を追加してください")
                    .setPositiveButton("OK") { _, _ -> }
                    .show()
            } else {
//                binding.start.isVisible = false
//                binding.addWord.isVisible = false
//                binding.wordList.isVisible = false

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.container, StudyFragment())
                    addToBackStack("MainActivity")
                    commit()
                    fragmentManager.popBackStack("MainActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
        }

        binding.addWord.setOnClickListener {
//            binding.start.isVisible = false
//            binding.addWord.isVisible = false
//            binding.wordList.isVisible = false

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, AddWordFragment())
                addToBackStack("MainActivity")
                commit()
                fragmentManager.popBackStack("MainActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }

        binding.wordList.setOnClickListener {
//            binding.start.isVisible = false
//            binding.addWord.isVisible = false
//            binding.wordList.isVisible = false


            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, WordListFragment(), "id")
                addToBackStack("MainActivity")
                commit()
                fragmentManager.popBackStack("MainActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
    }
}