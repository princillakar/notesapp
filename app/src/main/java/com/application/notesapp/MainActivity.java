package com.application.notesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.application.notesapp.adapters.NotesAdapter;
import com.application.notesapp.callbacks.NoteEventListener;
import com.application.notesapp.db.notesDB;
import com.application.notesapp.db.notesDoa;
import com.application.notesapp.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.application.notesapp.editNoteActivity.NOTE_EXTRA_key;

public class MainActivity extends AppCompatActivity implements NoteEventListener {
    private static final String TAG ="MainActivity";
    private RecyclerView recyclerView;
    public ArrayList<Note> notes;
    private NotesAdapter adapter;
    private notesDoa dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new note
                onAddNewNote();
            }


        });
      dao = notesDB.getInstance(this).notesDao();
    }

    public void loadNote() {
        this.notes = new ArrayList<>();
        List<Note> list =dao.getNotes();
        this.notes.addAll(list);
        this.adapter = new NotesAdapter(this , notes);
        this.adapter.setListener(this);


        this.recyclerView.setAdapter(adapter);
    }

    private void onAddNewNote() {

        startActivity(new Intent(this, editNoteActivity.class));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume(){
        super.onResume();
        loadNote();


    }

    @Override
    public void onNoteClick(Note note) {

Intent edit =new Intent(this,editNoteActivity.class);
edit.putExtra(NOTE_EXTRA_key ,note.getId());
startActivity(edit);
    }

    @Override
    public void onNoteLongClick(final Note note)
    {

      Log.d( TAG, "OnNoteClick"+note.getId());


    }
}




