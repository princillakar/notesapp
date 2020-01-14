package com.application.notesapp.model;

public class Note {
    private String note_text;
    private long note_date;


    public Note(String note_text, long note_date){
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



}
