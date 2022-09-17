package br.unigran.banco_dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.List;

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
        valores.put("data_nascimento", contato.getDataNascimento().toString());
        conexao.insertOrThrow("contato", null, valores);
        conexao.close();
    }

    public void atualizar(Contato contato) {
        conexao = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("telefone", contato.getTelefone());
        valores.put("data_nascimento", contato.getDataNascimento().toString());
        conexao.update("contato", valores, "id = ?", new String[]{contato.getId().toString()});
        conexao.close();
    }

    public void remover(Integer id) {
        conexao = db.getWritableDatabase();
        conexao.delete("contato", "id=?", new String[]{ id+"" });
        conexao.close();
    }

    public void listar(List dados) {
        dados.clear();
        conexao = db.getReadableDatabase();
        String names[] = {"id", "nome", "telefone", "data_nascimento"};
        Cursor query = conexao.query("contato", names, null, null, null, null, "nome");
        while (query.moveToNext()) {
            Contato contato = new Contato();
            contato.setId(Integer.parseInt(query.getString(0)));
            contato.setNome(query.getString(1));
            contato.setTelefone(query.getString(2));
            contato.setDataNascimento(new Date(query.getString(3)));
            dados.add(contato);
        }
        conexao.close();
    }
}
