package com.application.notesapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.application.notesapp.model.Note;


@Database(entities = Note.class, version = 1)

public abstract class notesDB extends RoomDatabase {

    public abstract notesDoa notesDao();

    private static final String DATABASE_NAME = "notesDB";
    private static notesDB instance;

    public static notesDB getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, notesDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();

        return instance;
    }
}
