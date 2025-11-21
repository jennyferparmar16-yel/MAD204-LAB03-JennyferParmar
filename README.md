# MAD204-LAB03
Course: MAD204 – Java Development for MA  
Student: Jennyfer Parmar  
Student ID: A00201240  
Date: 21st November 2025  

Android app that allows users to add notes, delete them with a long press, undo deletions, and save notes permanently using SharedPreferences.

Project Overview
This project is a Persistent Notes App developed in Android Studio using Kotlin.  
The application allows users to create, view, delete, and restore notes, and ensures that all notes are saved permanently using SharedPreferences and GSON.

This lab demonstrates skills in:
- RecyclerView List Handling  
- Custom Adapter & ViewHolder  
- UI Interaction (Button, EditText, Long Press)  
- Snackbar UNDO actions  
- Persistent Storage using SharedPreferences  
- JSON Serialization using GSON  


Features

Add Notes  
Users can type a note in the input field and add it using the "Add Note" button.  
A Toast message confirms that the note was added.

Display Notes  
All notes appear instantly inside a **RecyclerView** using a custom adapter and layout.

Delete Notes  
Users can long-press any note to delete it.

Undo Delete  
After deletion, a Snackbar appears with an UNDO button.  
If pressed, the deleted note is fully restored to its original position.

Persistent Storage  
All notes are saved automatically when the app closes and loaded again at startup using:
- SharedPreferences  
- GSON (to convert the list to JSON and back)

Notes remain even after the application is restarted.

Technologies Used
- Kotlin  
- Android Studio  
- RecyclerView  
- LinearLayoutManager  
- SharedPreferences  
- GSON  
- Snackbar (Material Design)

File Structure
- MainActivity.kt – Handles all user interactions, adding/removing notes, saving & loading data.  
- NotesAdapter.kt – Custom adapter for displaying notes inside the RecyclerView.  
- activity_main.xml – Main UI layout with input box, button, and RecyclerView.  
- item_row.xml – Layout for a single note displayed in the list.



Description Summary
This Android app fulfills all requirements for Lab 3 by combining dynamic UI elements with persistent storage. It demonstrates core Android development concepts including RecyclerView, Adapters, user interaction, and data persistence.

