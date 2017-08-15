package com.geovanni.agenda.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Geovanni on 29/06/2017.
 */

//@JsonIgnoreProperties  (ignoreUnknown = true) para ignorar propriedades que existem no JSON, mas nao existe no modelo

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aluno implements Serializable{

    private String id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private Double nota;
    private String caminhoFoto;
    private int desativado;
    private int sincronizado;

    public int getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(int sincronizado) {
        this.sincronizado = sincronizado;
    }

    public void setDesativado(int desativado) {
        this.desativado = desativado;
    }

    public int getDesativado() {
        return desativado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public boolean estaDesativado() {

        return desativado ==1;
    }

    /*
    Concatenar para exibir id + nome na lista
    @Override

    public String toString() {
        return getIdAluno() + " - " +getNome();
    }*/
}
