package com.application.notesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.notesapp.R;
import com.application.notesapp.callbacks.NoteEventListener;
import com.application.notesapp.model.Note;
import com.application.notesapp.utils.NoteUtils;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {
    public Context context;
    private ArrayList<Note> notes;
    private NoteEventListener listener;


    public NotesAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }


    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);


        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        final Note note = getNote(position);
        if (note != null) {
            holder.note_text.setText(note.getNote_text());
            holder.note_date.setText(NoteUtils.dateFromLong(note.getNote_date()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNoteClick(note, note.getId());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onNoteLongClick(note);
                    return false;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getNote(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView note_text, note_date;


        public NoteHolder(@NonNull View itemView) {

            super(itemView);
            note_date = itemView.findViewById(R.id.note_date);
            note_text = itemView.findViewById(R.id.note_text);
        }
    }

    public void setListener(NoteEventListener listener) {
        this.listener = listener;
    }
}
