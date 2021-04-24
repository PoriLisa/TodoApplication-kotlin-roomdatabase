package com.lissa.myapplicationtodoapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lissa.myapplicationtodoapplication.`interface`.OnClickDataInterface
import com.lissa.myapplicationtodoapplication.database.Word

class WordListAdapter : ListAdapter<Word, WordListAdapter.WordViewHolder>(WORDS_COMPARATOR) {
    lateinit var onClickData: OnClickDataInterface
    lateinit var setonclik: SetOnClickListner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word, current.description, current.finishBy, current.finished)
        if (current.finished) {
            holder.textViewStatus.text = "Completed"
        } else {
            holder.textViewStatus.text = "Not Completed"
        }

        holder.itemView.setOnClickListener {

            if (::setonclik.isInitialized) {
                Log.e("TAG", "onBindViewHolder: "+current.id )
                setonclik.listner(current.word,current.description,current.finishBy,current.finished,current.id)
            }
        }

    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val descriptions: TextView = itemView.findViewById(R.id.tv_desc)
        val finshedBy: TextView = itemView.findViewById(R.id.textViewFinishBy)
        val textViewStatus: TextView = itemView.findViewById(R.id.textViewStatus)

        fun bind(text: String?, description: String?, finishBys: String?, finished: Boolean) {
            wordItemView.text = text
            descriptions.text = description
            finshedBy.text = finishBys

        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.word == newItem.word
            }
        }
    }
}