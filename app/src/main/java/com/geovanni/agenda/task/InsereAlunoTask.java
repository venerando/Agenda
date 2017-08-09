package com.geovanni.agenda.task;

import android.os.AsyncTask;

import com.geovanni.agenda.WebClient;
import com.geovanni.agenda.converter.AlunoConverter;
import com.geovanni.agenda.modelo.Aluno;

/**
 * Created by geovanni on 08/08/17.
 */

public class InsereAlunoTask extends AsyncTask{
    private Aluno aluno;

    public InsereAlunoTask(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String json = new AlunoConverter().convertParaJSONCompleto(aluno);
        new WebClient().insere(json);
        return null;
    }
}
