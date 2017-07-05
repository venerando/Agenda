package com.geovanni.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geovanni.agenda.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geovanni on 30/06/2017.
 * CRIANDO BANCO DE DADOS SQLITE PARA ARMAZENAR OS DADOS DO ALUNO
 */

//Em casos de atualizações de campos na tabela sempre será necessário alterar a versão. nesse caso, o parametro ao lado do "null" (1)

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "AgendaBD", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = ("CREATE TABLE TB_ALUNO (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);");
        db.execSQL(sql);
    }


    //Nesse caso quando algum campo ou tabela, versão for atualizada o Android irá apagar a tabela existente e criar uma nova.

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TB_ALUNO";
        db.execSQL(sql);
        onCreate(db);

    }

    //Preparando o INSERT

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("site", aluno.getSite());
        dados.put("telefone", aluno.getTelefone());
        dados.put("nota", aluno.getNota());
        db.insert("TB_Aluno", null,dados);

    }

    /// SELECT NO BANCO

    public List<Aluno> buscaAluno() {
        String sql = "SELECT * FROM TB_ALUNO;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        while (c.moveToNext()){
            Aluno aluno = new Aluno();

            aluno.setIdAluno(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        //liberar memoria
        c.close();
        return alunos;
    }

    public void deletar(Aluno aluno) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {aluno.getIdAluno().toString()};
        db.delete("TB_ALUNO", "id = ?", params);
    }
}
