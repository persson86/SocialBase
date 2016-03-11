package com.project2016.lfspersson.socialbasetest1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LFSPersson on 3/11/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static String[] CREATE_DDL = {
            "CREATE TABLE [inscritos] ( " +
                    "  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "  [nome] VARCHAR2(50), " +
                    "  [email] TEXT(255));"
    };

    private static String[] UPDATE_DDL = {

    };

    public SQLiteHelper(
            Context context, String name, SQLiteDatabase.CursorFactory factory, int version ) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        Log.i("ECO", "Novo Banco de Dados...");

        for ( int i = 0; i < CREATE_DDL.length; i++ ) {
            db.execSQL( CREATE_DDL[ i ] );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ECO", "Nova versao do Banco de Dados...");

        switch( oldVersion ) {
            case 1:
                for ( int i = 0; i < 1; i++ ) {
                    db.execSQL( UPDATE_DDL[ i ] );
                }
            case 2:
                for ( int i = 1; i < UPDATE_DDL.length; i++ ) {
                    db.execSQL( UPDATE_DDL[ i ] );
                }
        }
    }
}
