package com.application.notesapp.callbacks;

import com.application.notesapp.model.Note;

public interface NoteEventListener {
    void onNoteClick(Note note, long id);
    void onNoteLongClick(Note note);


}
