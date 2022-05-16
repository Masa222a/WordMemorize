package com.android.example.wordmemorize

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.android.example.wordmemorize.databinding.FragmentAddWordBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class AddWordFragment : Fragment() {
    private var _binding: FragmentAddWordBinding? = null
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
        _binding = FragmentAddWordBinding.inflate(layoutInflater, container, false)
        binding.backButton.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, StudyFragment())
                ?.commit()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener { saveWord(it) }
    }

    private fun saveWord(view: View) {
        realm.executeTransaction { db: Realm ->
            val maxId = db.where<Word>().max("id")
            val nextId = (maxId?.toLong() ?: 0L) + 1L
            val words = db.createObject<Word>(nextId)
            words.word = binding.createEnword.text.toString()
            words.meaning = binding.createJpword.text.toString()
        }
        Snackbar.make(view, "追加しました", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
        _binding = null
    }
}