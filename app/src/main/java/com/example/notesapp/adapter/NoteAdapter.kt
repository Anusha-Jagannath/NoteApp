package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.Note

class NoteAdapter(private val noteList: ArrayList<Note>, private val listener: IClickListener) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        val viewHolder = NoteViewHolder(view)
        viewHolder.deleteBtn.setOnClickListener {
            listener.onClick(noteList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateList(newList: List<Note>) {
        noteList.clear()
        noteList.addAll(newList)
        notifyDataSetChanged()
    }
}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.findViewById<AppCompatTextView>(R.id.text)
    val deleteBtn = itemView.findViewById<AppCompatImageView>(R.id.deleteBtn)

    fun bind(note: Note) {
        title.text = note.title
    }
}

interface IClickListener {
    fun onClick(note: Note)
}