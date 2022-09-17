package br.unigran.banco_dados;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import br.unigran.lista_telefonica.Contato;

public class ContatoDB {
    DBHelper db;
    private SQLiteDatabase conexao;

    public ContatoDB(DBHelper db) {
        this.db = db;
    }

    public void inserir(Contato contato) {
        conexao = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("telefone", contato.getTelefone());
        valores.put("dataNascimento", contato.getDataNascimento().toString());
        conexao.insertOrThrow("contato", null, valores);
        conexao.close();
    }

    public void atualizar(Contato contato) {
        conexao = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("telefone", contato.getTelefone());
        valores.put("dataNascimento", contato.getDataNascimento().toString());
        conexao.update("contato", valores, "id = ?", new String[]{contato.getId().toString()});
        conexao.close();
    }

    
}
