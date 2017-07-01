package com.geovanni.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.modelo.Aluno;

import java.util.List;

import static android.R.attr.button;

public class ListaAlunos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        //Alterando busca statica no array para busca no BD.

        AlunoDAO dao = new AlunoDAO(this);
        List <Aluno> alunos = dao.buscaAluno();
        dao.close();

        //Refenciar a activity onde a lista será exibida.

        ListView viewListaAlunos = (ListView) findViewById(R.id.id_lista_alunos);

        //Adicionar os alunos na lista
        //android.R.layout.simple_list_item_1 = Lista Padrão Herdada do sys. Android.
        // alunos Array criado acima

        ArrayAdapter <Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        viewListaAlunos.setAdapter(adapter);

        //Navegando entre telas

        Button botaoAdicionar = (Button) findViewById(R.id.lista_aluno_adicionar);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunos.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }
}
