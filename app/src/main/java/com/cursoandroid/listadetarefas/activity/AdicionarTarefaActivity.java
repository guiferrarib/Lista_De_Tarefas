package com.cursoandroid.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cursoandroid.listadetarefas.R;
import com.cursoandroid.listadetarefas.helper.TarefaDAO;
import com.cursoandroid.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.editTarefa);

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if (tarefaAtual != null) {

            editTarefa.setText(tarefaAtual.getNomeTarefa());

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                String nomeTarefa = editTarefa.getText().toString();

                if (tarefaAtual != null){
                    if (!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        if(tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar tarefa!",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao atualizar tarefa!",Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(getApplicationContext(),"Digite uma tarefa...",Toast.LENGTH_LONG).show();
                    }

                }else{
                    if (!nomeTarefa.isEmpty()) {

                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                       if ( tarefaDAO.salvar(tarefa)){
                           finish();
                           Toast.makeText(getApplicationContext(),"Sucesso ao salvar tarefa!",Toast.LENGTH_LONG).show();
                       }else{
                           Toast.makeText(getApplicationContext(),"Erro ao salvar tarefa!",Toast.LENGTH_LONG).show();
                       }
                    }else {
                        Toast.makeText(getApplicationContext(),"Digite uma tarefa...",Toast.LENGTH_LONG).show();
                    }
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}