package com.geovanni.agenda;

import android.widget.EditText;
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
//FormularioActivity = Classe a qual será herdado o FindViewById

    public FormularioHelper(FormularioActivity activity ){

        //Recuperando informações digitadas pelo usuário

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_barra);

    }

    //Criando o método

    public Aluno pegarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));

        return aluno;

    }
}
