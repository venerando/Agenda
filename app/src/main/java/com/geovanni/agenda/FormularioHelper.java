package com.geovanni.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.geovanni.agenda.modelo.Aluno;

/**
 * Created by Geovanni on 29/06/2017.
 */

public class FormularioHelper {

    //Atributos
    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoSite;
    private final EditText campoTelefone;
    private final RatingBar campoNota;
    private Aluno aluno;
    private final ImageView campoFoto;


//FormularioActivity = Classe a qual será herdado o FindViewById

    public FormularioHelper(FormularioActivity activity ){

        //Recuperando informações digitadas pelo usuário

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_barra);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();

    }

    //Criando o método

    public Aluno pegarAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        aluno.setCaminhoFoto((String) campoFoto.getTag());

        return aluno;

    }

    ///Método para recuperar os dados do aluno no formulário
    public void preencherFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(aluno.getNota().intValue());
        carregaImagem(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {

        if (caminhoFoto !=null){

            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            //Limitando tamanho imagem
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            //Imagem ocupando espaço total do ImageView
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);

        }

    }
}
