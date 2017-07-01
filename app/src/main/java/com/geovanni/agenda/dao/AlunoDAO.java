package com.geovanni.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Geovanni on 30/06/2017.
 * CRIANDO BANCO DE DADOS SQLITE PARA ARMAZENAR OS DADOS DO ALUNO
 */

//Em casos de atualizações de campos na tabela sempre será necessário alterar a versão. nesse caso, o parametro ao lado do "null" (1)

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "AgendaBD", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = ("CREATE TABLE TB_ALUNO (id INTERGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);");
        db.execSQL(sql);
    }


    //Nesse caso quando algum campo ou tabela, versão for atualizada o Android irá apagar a tabela existente e criar uma nova.

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TB_ALUNO";
        db.execSQL(sql);
        onCreate(db);

    }
}
