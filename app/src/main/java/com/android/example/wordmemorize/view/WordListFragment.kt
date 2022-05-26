package com.android.example.wordmemorize.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.wordmemorize.model.RealmManager
import com.android.example.wordmemorize.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {
    private lateinit var binding: FragmentWordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordListBinding.inflate(inflater, container, false)
        binding.list.layoutManager = LinearLayoutManager(context)
        val words = RealmManager().getWords()
        val adapter = WordAdapter(words)
        binding.list.adapter = adapter
        return binding.root
    }
}