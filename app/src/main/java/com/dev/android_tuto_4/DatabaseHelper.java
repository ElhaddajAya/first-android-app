package com.dev.android_tuto_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "personnes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PERSONNE = "personne";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TELE = "tele";
    private static final String COLUMN_DATE_NAISS = "date_naiss";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PERSONNE_TABLE = "CREATE TABLE " + TABLE_PERSONNE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOM + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_TELE + " TEXT, " +
                COLUMN_DATE_NAISS + " TEXT)";
        db.execSQL(CREATE_PERSONNE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONNE);
        onCreate(db);
    }

    public boolean addPersonne(String nom, String email, String tele, String dateNaiss) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, nom);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_TELE, tele);
        values.put(COLUMN_DATE_NAISS, dateNaiss);

        long result = db.insert(TABLE_PERSONNE, null, values);
        db.close();
        return result != -1;
    }

    public Cursor getAllPersonnes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PERSONNE, null);
    }
}