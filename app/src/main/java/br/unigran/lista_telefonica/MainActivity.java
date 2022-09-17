package br.unigran.lista_telefonica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    EditText nome;
    EditText telefone;
    EditText dataNascimento;

    Button salvar;

    ListView listagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.textNome);
        telefone = findViewById(R.id.textTelefone);
        dataNascimento = findViewById(R.id.textDataNascimento);
        salvar = findViewById(R.id.buttonSalvar);
        listagem = findViewById(R.id.lista);
    }
}