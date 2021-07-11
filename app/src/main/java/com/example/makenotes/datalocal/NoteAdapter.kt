package com.example.makenotes.datalocal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.makenotes.R

class NoteAdapter (private val context: Context, private val listener: iNoteAdapter): RecyclerView.Adapter<NoteAdapter.NoteVH>() {
    private val allNotes=ArrayList<Note>()

    inner class NoteVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val delBtn=itemView.findViewById<ImageView>(R.id.imagebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        val viewHolder = NoteVH(LayoutInflater.from(parent.context).inflate(R.layout.ittem_note,parent,false))
        viewHolder.delBtn.setOnClickListener{
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface iNoteAdapter{
    fun onItemClick(note: Note)
}