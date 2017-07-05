package com.geovanni.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.modelo.Aluno;

import java.util.List;

import static android.R.attr.button;



public class ListaAlunos extends AppCompatActivity {

    private ListView viewListaAlunos;
    private Button botaoAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        //Navegando entre telas

         botaoAdicionar = (Button) findViewById(R.id.lista_aluno_adicionar);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunos.this, FormularioActivity.class);
                startActivity(intent);
                ;
            }
        });


    }

    //Alterando busca statica no array para busca no BD.
    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAluno();
        dao.close();

        //Refenciar a activity onde a lista será exibida.

       viewListaAlunos = (ListView) findViewById(R.id.id_lista_alunos);
        //Adicionar os alunos na lista
        //android.R.layout.simple_list_item_1 = Lista Padrão Herdada do sys. Android.
        // alunos Array criado acima

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        viewListaAlunos.setAdapter(adapter);

        //Opção Para Deletar Aluno na Tela
        registerForContextMenu(viewListaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Método criado clicando botão direito Refactor > Extract > Method
        carregaLista();
    }


    //Opção Para Deletar Aluno na Tela - segundo forma de popular o menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Excluir");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) viewListaAlunos.getItemAtPosition(info.position);

                AlunoDAO dao = new AlunoDAO(ListaAlunos.this);
                dao.deletar(aluno);
                dao.close();

                Toast.makeText(ListaAlunos.this, "Aluno " + aluno.getNome() + " deletado!", Toast.LENGTH_SHORT).show();

                carregaLista();
                return false;
            }
        });
    }
}
