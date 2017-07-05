package com.geovanni.agenda.modelo;

/**
 * Created by Geovanni on 29/06/2017.
 */

public class Aluno {
    private Long idAluno;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private Double nota;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    ///Nunca esquecer do tostring para listar os dados

    @Override
    public String toString() {
        return getNome();
    }

   /*
    Concatenar para exibir id + nome na lista
    @Override

    public String toString() {
        return getIdAluno() + " - " +getNome();
    }*/
}