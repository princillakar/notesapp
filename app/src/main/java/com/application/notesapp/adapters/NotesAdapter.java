package com.application.notesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.notesapp.R;
import com.application.notesapp.model.Note;
import com.application.notesapp.utils.NoteUtils;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder>{
public Context context;
    private ArrayList <Note> notes;

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }


    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent ,false);


        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(  NoteHolder holder, int position) {
Note note = getNote(position);
if (note!=null ){
    holder.note_text.setText(note.getNote_text());
    holder.note_date.setText(NoteUtils.dateFromLong(note.getNote_date()));
}
    }
@Override
    public int getItemCount() {
        return notes.size();
    }
    public   Note getNote(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
TextView note_text , note_date;


public NoteHolder(@NonNull View itemView) {

    super(itemView);
    note_date=itemView.findViewById(R.id.note_date);
    note_text=itemView.findViewById(R.id.note_text);
        }
    }

}
