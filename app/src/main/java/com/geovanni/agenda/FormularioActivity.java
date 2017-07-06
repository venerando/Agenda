package com.geovanni.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.modelo.Aluno;

import java.util.zip.Inflater;

public class FormularioActivity extends AppCompatActivity {


    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        //Instaciando FormulárioHelper Criado
        //Create Field "Helper"
        helper = new FormularioHelper(this);

        //Recuperando dados do formulário de aluno já cadastrado
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null){
            helper.preencherFormulario(aluno);
        }

    }

    //Dgitar apenas onCreateOptionsMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }


    //Salvado com botão de confirmação no menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                //Recuperando informações digitadas pelo usuário através da Classe Aluno (Modelo)
                Aluno aluno = helper.pegarAluno();

                //Invocando o metódo com o insert para inserir os dados digitados pelo usuário no banco
                AlunoDAO dao = new AlunoDAO(this);
                dao.insere(aluno);
                dao.close();

                //Exibe uma mensagem de sucesso ao cadastrar
                Toast.makeText(FormularioActivity.this, "Aluno " +aluno.getNome() + " salvo!", Toast.LENGTH_LONG).show();

                //Faz retornar a tela anterior ao salvar
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
