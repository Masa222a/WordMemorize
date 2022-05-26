package com.android.example.wordmemorize.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.example.wordmemorize.model.RealmManager
import com.android.example.wordmemorize.R
import com.android.example.wordmemorize.databinding.FragmentAddWordBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm

class AddWordFragment : Fragment() {
    private lateinit var binding: FragmentAddWordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWordBinding.inflate(layoutInflater, container, false)
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
            if (en.isEmpty() || jp.isEmpty()) {
                Snackbar.make(view, "文字を入力してください", Snackbar.LENGTH_SHORT).show()
            } else {
                RealmManager().saveWord(en, jp)
                Snackbar.make(view, "追加しました", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Realm.getDefaultInstance().close()
    }
}