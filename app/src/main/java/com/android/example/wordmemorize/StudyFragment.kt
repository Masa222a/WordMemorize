package com.android.example.wordmemorize

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.android.example.wordmemorize.databinding.FragmentStudyBinding
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlin.random.Random

class StudyFragment : Fragment() {
    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding!!

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyBinding.inflate(inflater, container, false)

        binding.jpWord.isVisible = false

        val wordRecords = realm.where<Word>().findAll()
        if (wordRecords.isNotEmpty()) {
            val num = Random.nextInt(wordRecords.size)
            val randomWord = realm.where<Word>().equalTo("id", num).findFirst()
            randomWord?.word = binding.engWord.text.toString()
            randomWord?.meaning = binding.jpWord.text.toString()

        }

        binding.japaneseButton.setOnClickListener {
            binding.jpWord.isVisible = true
        }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}