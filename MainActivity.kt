/**
 * Course: MAD204 – Java Development for MA
 * Lab 3 – Persistent Notes App
 *
 * Student Name: Jennyfer Parmar
 * Student ID: A00201240
 * Date: 21st November 2025
 *
 * Description:
 * This activity manages the main screen of the Persistent Notes App.
 * It allows users to add new notes, displays all notes in a RecyclerView,
 * supports long-press deletion with an UNDO snackbar option, and saves/
 * loads all notes using SharedPreferences and GSON for persistence.
 */
package com.example.lab3
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    // RecyclerView that displays all notes
    private lateinit var recyclerView: RecyclerView
    // Input field where user types a note
    private lateinit var inputNote: EditText
    // "Add Note" button
    private lateinit var addButton: Button

    // Adapter for RecyclerView (your custom adapter)
    private lateinit var adapter: NotesAdapter

    // List that stores all notes
    private val notesList = mutableListOf<String>()

    // SharedPreferences keys
    private val PREFS_KEY = "notes_prefs"
    private val NOTES_KEY = "notes_json"

    /**Called when the activity is first created.
     Initializes UI components, loads saved notes, and sets up listeners.*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Match IDs from activity_main.xml
        inputNote = findViewById(R.id.editTextView)
        addButton = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerView)

        // Load saved notes before setting adapter
        loadNotes()

        // Adapter with long-press delete
        adapter = NotesAdapter(notesList) { note, position ->
            deleteNoteWithUndo(note, position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // When "Add Note" is clicked, add note to list
        addButton.setOnClickListener {
            val text = inputNote.text.toString()
            if (text.isNotEmpty()) {
                notesList.add(text)
                adapter.notifyItemInserted(notesList.size - 1)
                inputNote.text.clear()
                Toast.makeText(this, "Note added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a note first.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     Deletes a note and shows a Snackbar with UNDO option.
     @param note The text of the note being removed.
     @param position The position of the note in the list.*/
    private fun deleteNoteWithUndo(note: String, position: Int) {
        val removedNote = notesList.removeAt(position)
        adapter.notifyItemRemoved(position)

        Snackbar.make(recyclerView, "Note deleted", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                notesList.add(position, removedNote)
                adapter.notifyItemInserted(position)
            }
            .show()
    }

    /** Called when the app is minimized or closed.
        Saves the notes list using SharedPreferences and GSON.*/
    override fun onPause() {
        super.onPause()
        saveNotes()
    }

    /**Converts the notes list to JSON using GSON and stores it in SharedPreferences.*/
    private fun saveNotes() {
        val gson = Gson()
        val json = gson.toJson(notesList)

        val prefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(NOTES_KEY, json)
            .apply()
    }
    /** Loads saved notes from SharedPreferences and adds them to notesList.*/
    private fun loadNotes() {
        val prefs = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
        val json = prefs.getString(NOTES_KEY, null)

        if (json != null) {
            val type = object : TypeToken<MutableList<String>>() {}.type
            val savedList: MutableList<String> = Gson().fromJson(json, type)
            notesList.addAll(savedList)
        }
    }
}