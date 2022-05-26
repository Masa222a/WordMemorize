package com.android.example.wordmemorize.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.wordmemorize.model.Word
import com.android.example.wordmemorize.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class WordAdapter(data: OrderedRealmCollection<Word>) :
    RealmRecyclerViewAdapter<Word, WordAdapter.ViewHolder>(data, true) {

    init {
        setHasStableIds(true)
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val word: TextView = cell.findViewById(R.id.word_name)
        val meaning: TextView = cell.findViewById(R.id.word_mean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word: Word? = getItem(position)
        holder.word.text = word?.word
        holder.meaning.text = word?.meaning
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?: 0
    }

}