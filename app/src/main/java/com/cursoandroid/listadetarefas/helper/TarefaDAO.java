package com.cursoandroid.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cursoandroid.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NOME,tarefa.getNomeTarefa());

        try {
            escreve.insert(DbHelper.TABELA,null, contentValues);
            Log.i("INFO", "Tarefa salva com sucesso!");
        }catch (Exception e){
            Log.e("ERRO", "Erro ao salvar tarefa "+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.ID,tarefa.getId());
        contentValues.put(DbHelper.NOME,tarefa.getNomeTarefa());

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA,contentValues,"id=?",args);
            Log.i("INFO", "Tarefa atualizada com sucesso!");
        }catch (Exception e){
            Log.e("ERRO", "Erro ao atualizar tarefa "+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.delete(DbHelper.TABELA,"id=?",args);
            Log.i("INFO", "Tarefa removida com sucesso!");
        }catch (Exception e){
            Log.e("ERRO", "Erro ao remover tarefa "+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM "+DbHelper.TABELA+" ;";
        Cursor c = le.rawQuery(sql,null);

        while (c.moveToNext()){

            Tarefa tarefa = new Tarefa();

            Long id = c.getLong(c.getColumnIndex(DbHelper.ID));
            String nomeTarefa = c.getString(c.getColumnIndex(DbHelper.NOME));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefas.add(tarefa);

        }

        return tarefas;
    }

}
