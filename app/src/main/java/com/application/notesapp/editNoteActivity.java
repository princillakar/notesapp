package com.application.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.application.notesapp.db.notesDB;
import com.application.notesapp.db.notesDoa;
import com.application.notesapp.model.Note;

import java.util.Date;

public class editNoteActivity extends AppCompatActivity {
    public EditText input_note;
    private notesDoa dao;
    private Note temp;
    public static final String NOTE_EXTRA_key = "note_id";
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        input_note = findViewById(R.id.input_note);
        dao = notesDB.getInstance(this).notesDao();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            status = bundle.getBoolean("EDIT", false);
        }
        long current_note_id = 0;
        if (bundle != null) {
            current_note_id = bundle.getLong(NOTE_EXTRA_key);
        }
        if (status) {
            //    int id = getIntent().getExtras().getInt(NOTE_EXTRA_key, 0);
            temp = dao.getNoteById((int) current_note_id);
            input_note.setText(temp.getNote_text());
        } else {
            temp = new Note();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        String text = input_note.getText().toString();
        if (!text.isEmpty()) {
            long date = new Date().getTime();
            temp.setNote_date(date);
            temp.setNote_text(text);
            if (status)
                dao.updateNote(temp);
            else {
                long id = notesDB.getInstance(this).notesDao().insertNote(temp);
                temp.setId(id);
                Log.e("Note_ID", String.valueOf(id));
            }
            finish();
        }
    }
}



