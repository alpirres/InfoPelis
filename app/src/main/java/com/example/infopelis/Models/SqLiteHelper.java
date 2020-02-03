package com.example.infopelis.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqLiteHelper extends SQLiteOpenHelper {

    private static SqLiteHelper sqliteInstance = null;

    String sqlCreate = "CREATE TABLE Peliculas ( codigo INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "titulo TEXT, fecha TEXT, categoria TEXT, duracion TEXT, director TEXT, comentario TEXT, estrenada TEXT, imagen TEXT)";


    public static SqLiteHelper getSqliteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        if (sqliteInstance == null) {
            sqliteInstance = new SqLiteHelper(contexto,  nombre,factory, version);
        }

        return sqliteInstance;
    }

    private SqLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Peliculas");

        sqLiteDatabase.execSQL(sqlCreate);
    }
}
