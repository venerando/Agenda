package com.geovanni.agenda.dto;

import com.geovanni.agenda.modelo.Aluno;

import java.util.List;

/**
 * Created by geovanni on 10/08/17.
 */

public class AlunoSync {

    private List<Aluno> alunos;
    private String momentoDaUltimaModificacao;


    public String getMomentoDaUltimaModificacao() {
        return momentoDaUltimaModificacao;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
