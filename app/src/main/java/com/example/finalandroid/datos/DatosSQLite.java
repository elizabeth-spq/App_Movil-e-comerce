package com.example.finalandroid.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatosSQLite extends SQLiteOpenHelper {
    public DatosSQLite(@Nullable Context context) {
        super(context, "mundo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE paises(" +
                "idpais INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT,"+
                "capital TEXT,"+
                "poblacion TEXT," +
                "area TEXT)");

    }
    public int paisInsert(DatosSQLite datosSQLite,String nombre, String capital,String poblacion,String area){
        SQLiteDatabase sqLiteDatabase = datosSQLite.getWritableDatabase();
        //sqLiteDatabase.execSQL(ISERT INTO...);
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",nombre);
        contentValues.put("capital",capital);
        contentValues.put("poblacion",poblacion);
        contentValues.put("area",area);

        int autonumerico = (int) sqLiteDatabase.insert("paises",null,contentValues);
        return autonumerico;
    }

    public Cursor paisesSelect(DatosSQLite datosSQLite){
        SQLiteDatabase sqLiteDatabase = datosSQLite.getReadableDatabase();
        String sql = "SELECT * FROM paises";
        return sqLiteDatabase.rawQuery(sql,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
