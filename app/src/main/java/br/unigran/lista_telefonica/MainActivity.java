package br.unigran.lista_telefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public void salvar(View view) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

            Contato contato = new Contato();

            contato.setNome(nome.getText().toString());
            contato.setTelefone(telefone.getText().toString());
            contato.setDataNascimento(formatter.parse(dataNascimento.getText().toString()));

            contatoDB.inserir(contato);

            Toast.makeText(this, "Contato salvo com sucesso.", Toast.LENGTH_SHORT).show();

            contatoDB.listar(dados);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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

        db = new DBHelper(this);
        contatoDB = new ContatoDB(db);

        contatoDB.listar(dados);
    }
}