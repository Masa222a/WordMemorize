package com.android.example.wordmemorize

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.android.example.wordmemorize.databinding.FragmentStudyBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.lang.IllegalArgumentException
import kotlin.random.Random

class StudyFragment : Fragment() {
    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding!!

    private lateinit var realm: Realm
    private val words = mutableListOf<Word>()
    private val currentWords = mutableListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudyBinding.inflate(inflater, container, false)

        val words = words.addAll(realm.where<Word>().findAll())
        try {
            setWord()
            binding.jpWord.isVisible = false
            binding.japaneseButton.setOnClickListener {
                binding.jpWord.isVisible = true
            }
        } catch (e: IllegalArgumentException) {
            print(e)
        }

        binding.nextButton.setOnClickListener {
            binding.jpWord.isVisible = false
            nextWord(it)
            binding.japaneseButton.setOnClickListener {
                binding.jpWord.isVisible = true
            }
        }

        return binding.root

    }

    private fun nextWord(view: View) {
        if (words.isNotEmpty()) {
            setWord()
        } else {
            Snackbar.make(view, "全ての単語を終えました。", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setWord() {
        val num = Random.nextInt(words.size)
        val word = words[num]
        binding.engWord.text = word.word
        binding.jpWord.text = word.meaning
        words.remove(word)
        currentWords.add(word)
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