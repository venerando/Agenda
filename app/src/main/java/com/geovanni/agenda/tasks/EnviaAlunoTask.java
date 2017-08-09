package com.geovanni.agenda.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.geovanni.agenda.WebClient;
import com.geovanni.agenda.converter.AlunoConverter;
import com.geovanni.agenda.dao.AlunoDAO;
import com.geovanni.agenda.modelo.Aluno;

import java.util.List;

/**
 * Created by geovanni on 20/07/17.
 */

public class EnviaAlunoTask extends AsyncTask <Void,Void,String>{
    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunoTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando Alunos...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {

        AlunoDAO dao = new AlunoDAO(context);

        List<Aluno> alunos = dao.buscaAluno();
        dao.close();


        AlunoConverter conversor = new AlunoConverter();

        String json = conversor.convertParaJSON(alunos);

        WebClient client = new WebClient();

        String resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta ,Toast.LENGTH_LONG).show();
    }
}
