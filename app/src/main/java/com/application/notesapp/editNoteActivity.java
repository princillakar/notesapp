package com.application.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.application.notesapp.db.notesDB;
import com.application.notesapp.db.notesDoa;
import com.application.notesapp.model.Note;

import java.util.Date;

public class editNoteActivity extends AppCompatActivity {
public EditText input_note;
private notesDoa dao;
private Note temp;
public static final String NOTE_EXTRA_key = "note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        input_note = findViewById(R.id.input_note);
        dao =notesDB.getInstance(this).notesDao();
         if (getIntent().getExtras()!=null){
             int id =getIntent().getExtras().getInt(NOTE_EXTRA_key,0);
             temp=dao.getNoteById(id);

             input_note.setText(temp.getNote_text());
         }
         else temp =new Note();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.edit_note_menu,menu);
    return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        String text = input_note.getText().toString();
        if(!text.isEmpty())
        {
            long date =new Date().getTime();
temp.setNote_date(date);
temp.setNote_text(text);
if (temp.getId() == -1)

    dao.insertNote(temp);
else dao.updateNote(temp);

            finish();//returns
        }
    }
}



