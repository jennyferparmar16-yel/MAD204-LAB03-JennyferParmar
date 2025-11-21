/**
 * Course: MAD204 – Java Development for MA
 * Lab 3 – Persistent Notes App
 *
 * Student Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 21st November 2025
 *
 * Description:
 * This adapter binds the list of notes to the RecyclerView.
 * It creates note items, displays each note, and handles long-press
 * deletion events by sending the selected note and its position back
 * to the MainActivity.
 */
package com.example.lab3
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for displaying notes in RecyclerView.
 * Handles click + long press events.
 */

class NotesAdapter(
    private val notes: MutableList<String>,
    private val onLongPress: (String, Int) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    /**ViewHolder class that holds references to UI elements in each note row.*/
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTextView: TextView = itemView.findViewById(R.id.noteTextView)
    }

    /**Inflates the item_row.xml layout for each note*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return NotesViewHolder(view)
    }
    /**Returns the number of notes in the list.*/
    override fun getItemCount(): Int = notes.size
    /**Binds each note's text to the TextView and sets up the long-press listener.*/
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.noteTextView.text = note

        holder.itemView.setOnLongClickListener {
            onLongPress(note, position)
            true
        }
    }
}