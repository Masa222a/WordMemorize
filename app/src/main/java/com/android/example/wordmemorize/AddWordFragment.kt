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
        binding.addButton.setOnClickListener {
        val en = binding.createEnword.text.toString()
        val jp = binding.createJpword.text.toString()
            if (en == "" || jp == "") {
                Snackbar.make(view, "文字を入力してください", Snackbar.LENGTH_SHORT).show()
            } else {
                RealmManager().saveWord(en, jp)
                Snackbar.make(view, "追加しました", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
        _binding = null
    }
}