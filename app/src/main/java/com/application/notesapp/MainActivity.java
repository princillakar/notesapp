package com.application.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.notesapp.adapters.NotesAdapter;
import com.application.notesapp.callbacks.NoteEventListener;
import com.application.notesapp.db.notesDB;
import com.application.notesapp.db.notesDoa;
import com.application.notesapp.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.application.notesapp.editNoteActivity.NOTE_EXTRA_key;

public class MainActivity extends AppCompatActivity implements NoteEventListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    public ArrayList<Note> notes;
    private notesDoa dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dao = notesDB.getInstance(this).notesDao();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new note
                onAddNewNote();
            }
        });

    }

    public void loadNote() {
        this.notes = new ArrayList<>();
        List<Note> list = dao.getNotes();

        this.notes.addAll(list);
        NotesAdapter adapter = new NotesAdapter(this, notes);
        adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);
    }

    private void onAddNewNote() {
        Intent edit = new Intent(this, editNoteActivity.class);
        edit.putExtra("EDIT", false);
        startActivity(edit);
    }


    @Override
    public void onNoteClick(Note note, long id) {
        Intent edit = new Intent(this, editNoteActivity.class);
        edit.putExtra("EDIT", true);
        edit.putExtra(NOTE_EXTRA_key, id);
        startActivity(edit);
    }

    @Override
    public void onNoteLongClick(final Note note) {

        Log.d(TAG, "OnNoteClick" + note.getId());
        Toast.makeText(this, "LongPress", Toast.LENGTH_SHORT).show();

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
    public void onResume() {
        super.onResume();
        loadNote();
    }
}




