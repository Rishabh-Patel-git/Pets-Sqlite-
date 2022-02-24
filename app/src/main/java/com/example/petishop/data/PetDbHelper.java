package com.example.petishop.data;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.petishop.CatalogActivity;
import com.example.petishop.data.PetsContract;

public class PetDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME   = "shelter.db";
    public static final int  DATABASE_VERSION   = 1;

    public PetDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(PetsContract.SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
