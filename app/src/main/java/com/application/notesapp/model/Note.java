package com.application.notesapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private long id; //default
    @ColumnInfo(name = "text")
    private String note_text;
    @ColumnInfo(name = "date")
    private long note_date;

    public Note() {
    }

    public Note( String note_text, long note_date){
        this.note_text=note_text;
        this.note_date=note_date;
    }

    public String getNote_text(){
        return note_text;
    }
    public void setNote_text(String  note_text){
        this.note_text = note_text;
    }
    public long getNote_date(){
        return note_date;
    }
    public void setNote_date(long note_date){
        this.note_date = note_date;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note_date=" + note_date +
                '}';
    }
}

