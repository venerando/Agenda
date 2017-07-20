package com.geovanni.agenda.converter;

import com.geovanni.agenda.modelo.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by geovanni on 19/07/17.
 */

public class AlunoConverter {
    public String convertParaJSON(List<Aluno> alunos) {
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("List").array().object().key("aluno").array();
            for (Aluno aluno : alunos){
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }

            js.endArray().endObject().endArray().endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
