package com.application.notesapp;

import android.os.Bundle;

import com.application.notesapp.adapters.NotesAdapter;
import com.application.notesapp.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private ArrayList<Note> notes;
private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     recyclerView=findViewById(R.id.notes_list);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));



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
        this.notes = new ArrayList<Note>();
        for (int i=0; i<12;i++){
            notes.add(new Note("just demo of the following app ;;;;;;;;;;;;;;;;;;;................."
                    ,new Date().getTime()));

        }

       adapter =new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();




    }

    public void onAddNewNote() {
        if (notes!=null)
            notes.add(new Note("this is new note",new Date().getTime()));
        if(notes!=null)
            adapter.notifyDataSetChanged();
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
}
