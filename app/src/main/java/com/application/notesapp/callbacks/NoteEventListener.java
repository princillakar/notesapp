package com.application.notesapp.callbacks;

import com.application.notesapp.model.Note;

public interface NoteEventListener {
    void onNoteClick(Note note);
    void onNoteLongClick(Note note);


}
