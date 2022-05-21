package com.android.example.wordmemorize

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class WordAdapter(data: OrderedRealmCollection<Word>) :
    RealmRecyclerViewAdapter<Word, WordAdapter.ViewHolder>(data, true) {

    init {
        setHasStableIds(true)
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val word: TextView = cell.findViewById(android.R.id.text1)
        val meaning: TextView = cell.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
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