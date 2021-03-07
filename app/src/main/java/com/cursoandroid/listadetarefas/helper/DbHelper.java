package com.cursoandroid.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA = "tarefas";
    public static String ID = "id";
    public static String NOME = "nome";
    private SQLiteDatabase db;

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "";

        sql  = "CREATE TABLE IF NOT EXISTS "+TABELA+" ( ";
        sql += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += NOME+" TEXT NOT NULL );";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabela "+e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
