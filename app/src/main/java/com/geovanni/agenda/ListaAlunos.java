package com.geovanni.agenda;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
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

import com.geovanni.agenda.adapter.AlunosAdapter;
import com.geovanni.agenda.converter.AlunoConverter;
import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.modelo.Aluno;

import java.util.List;
import java.util.jar.Manifest;

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

        ///////////////////////////////Tratando clique no item clicado na lista/////////////////////////////////

        viewListaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) viewListaAlunos.getItemAtPosition(position);

                //Abrindo tela do cadastro do aluno
                Intent intentVaiProFormulario = new Intent(ListaAlunos.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("aluno", aluno);
                startActivity(intentVaiProFormulario);
            }
        });


        //Adicionar os alunos na lista
        //android.R.layout.simple_list_item_1 = Lista Padrão Herdada do sys. Android.
        // alunos Array criado acima

        /*
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        */

         /*------------------------------------------------------------------------------------------
        - Usando o proprio Adpter criado
         */


        AlunosAdapter adapter = new AlunosAdapter(this, alunos);


        viewListaAlunos.setAdapter(adapter);


        /*------------------------------------------------------------------------------------------*/

        //Opção Para Deletar Aluno na Tela
        registerForContextMenu(viewListaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Método criado clicando botão direito Refactor > Extract > Method
        carregaLista();
    }

        /*------------------------------------------------------------------------------------------
         - Criacao do menu para enviar as notas
         */


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_lista_alunos,menu);

            return true;
        }

         @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.menu_enviar_notas:

                    AlunoDAO dao = new AlunoDAO(this);

                    List<Aluno> alunos = dao.buscaAluno();
                    dao.close();

                    AlunoConverter conversor = new AlunoConverter();

                    String json = conversor.convertParaJSON(alunos);

                    Toast.makeText(this, json,Toast.LENGTH_LONG).show();
                    break;
            }
            return super.onOptionsItemSelected(item);


        }

        /*------------------------------------------------------------------------------------------*/


    //Opção Para Deletar Aluno na Tela - segundo forma de popular o menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) viewListaAlunos.getItemAtPosition(info.position);

        /*------------------------------------------------------------------------------------------
        - Intent implicita
        - Chamando o navegador para acessar um site
         */

        MenuItem itemSite = menu.add("Acessar Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);

        //--------Adicionando http:// caso o usário não tenha digitado na hora do cadastro

        String site = aluno.getSite();
        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }

        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);

        /*------------------------------------------------------------------------------------------*/


        /*------------------------------------------------------------------------------------------
        -Enviando SMS
        - Abrindo endereço do aluno mapa
         */

        //SMS
        MenuItem itemSms = menu.add("Enviar SMS");
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:" + aluno.getTelefone()));
        itemSms.setIntent(intentSms);

        //Endereço

        MenuItem itemMapa = menu.add("Visualizar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0.0?q=" + aluno.getEndereco()));
        itemMapa.setIntent(intentMapa);

        /*------------------------------------------------------------------------------------------*/


        /*------------------------------------------------------------------------------------------
        - Fazendo ligações
        - Configurando as permissões necessárias para que seja efetuado a ligação.
         */

        //Ligações

        MenuItem itemLigar = menu.add("Ligar");

        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent intentLigar = new Intent(Intent.ACTION_CALL);
                intentLigar.setData(Uri.parse("tel: " + aluno.getTelefone()));
                if (ActivityCompat.checkSelfPermission(ListaAlunos.this, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ListaAlunos.this,
                            new String[]{android.Manifest.permission.CALL_PHONE},123);
                }
                else {
                    startActivity(intentLigar);
                }
                return false;
            }
        });


        /*------------------------------------------------------------------------------------------*/



        MenuItem deletar = menu.add("Excluir");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

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
