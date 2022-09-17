package br.unigran.lista_telefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.unigran.banco_dados.ContatoDB;
import br.unigran.banco_dados.DBHelper;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText telefone;
    EditText dataNascimento;
    Button salvar;
    ListView listagem;

    List<Contato> dados;

    DBHelper db;
    ContatoDB contatoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.textNome);
        telefone = findViewById(R.id.textTelefone);
        dataNascimento = findViewById(R.id.textDataNascimento);
        salvar = findViewById(R.id.buttonSalvar);
        listagem = findViewById(R.id.lista);

        dados = new ArrayList();

        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listagem.setAdapter(adapter);

        db = new DBHelper(this);
        contatoDB = new ContatoDB(db);

        contatoDB.listar(dados);
    }
}