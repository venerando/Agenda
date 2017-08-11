package com.geovanni.agenda;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.modelo.Aluno;
import com.geovanni.agenda.retrofit.RetrofitInicializador;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormularioActivity extends AppCompatActivity {


    public static final int codigo_camera = 567;
    private FormularioHelper helper;
    private String caminhoFoto;

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

        /*------------------------------------------------------------------------------------------
        - Tirando uma foto para o casdatro do aluno
         */

        Button botaoFoto = (Button) findViewById(R.id.formulario_botao_foto);

        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, codigo_camera);
            }
        });




        /*------------------------------------------------------------------------------------------*/
    }

    //Recuperando imagem
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == codigo_camera) {
            helper.carregaImagem(caminhoFoto);
            }

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

                AlunoDAO dao = new AlunoDAO(this);

                //Alterando dados de aluno já cadastrado
                if (aluno.getId()!= null){
                    dao.altera(aluno);
                //Exibe uma mensagem de sucesso ao alter os dados
                  Toast.makeText(FormularioActivity.this, "Aluno " +aluno.getNome() + " alterado!", Toast.LENGTH_LONG).show();

                } else{
                 //Invocando o metódo com o insert para inserir os dados digitados pelo usuário no banco
                dao.insere(aluno);
                //Exibe uma mensagem de sucesso ao cadastrar
                 Toast.makeText(FormularioActivity.this, "Aluno " +aluno.getNome() + " salvo!", Toast.LENGTH_LONG).show();
                }
                dao.close();


               // new InsereAlunoTask(aluno).execute();

                Call call = new RetrofitInicializador().getAlunoService().insere(aluno);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.i("onResponse", "Requsicao com sucesso");
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                        Log.e("onFailure", "Requisicao falhou" );

                    }
                });


                //Faz retornar a tela anterior ao salvar
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
