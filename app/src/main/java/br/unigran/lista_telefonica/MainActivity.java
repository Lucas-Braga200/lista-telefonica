package br.unigran.lista_telefonica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    Integer atualizando;

    public void salvar(View view) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

            Contato contato = new Contato();

            contato.setNome(nome.getText().toString());
            contato.setTelefone(telefone.getText().toString());
            contato.setDataNascimento(formatter.parse(dataNascimento.getText().toString()));

            if (atualizando != null) {
                contato.setId(atualizando);
            }

            if (atualizando == null) {
                contatoDB.inserir(contato);
                mostrarMensagem("Contato salvo com sucesso.");
            } else {
                contatoDB.atualizar(contato);
                mostrarMensagem("Contato atualizado com sucesso.");
            }

            toggleEstadoAtualizacao(null);
            limparCampos();
            contatoDB.listar(dados);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void toggleEstadoAtualizacao(Integer id) {
        if (id == null) {
            atualizando = null;
        } else {
            atualizando = id;
        }
    }

    public void limparCampos() {
        nome.setText("");
        telefone.setText("");
        dataNascimento.setText("");
    }

    public void cancelarAtualizacao() {
        toggleEstadoAtualizacao(null);
        limparCampos();
        mostrarMensagem("Edição cancelada.");
    }

    public void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
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

        listagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int j, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setMessage("Realmente quer remover?");
                alert.setPositiveButton("remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mostrarMensagem("Contato deletado com sucesso.");
                        contatoDB.remover(dados.get(j).getId());
                        contatoDB.listar(dados);
                    }
                });
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
                return false;
            }
        });
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setMessage("Realmente quer editar?");
                alert.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                            toggleEstadoAtualizacao(dados.get(i).getId());
                            nome.setText(dados.get(i).getNome());
                            telefone.setText(dados.get(i).getTelefone());
                            dataNascimento.setText(formatter.format(dados.get(i).getDataNascimento()));
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        cancelarAtualizacao();
    }
}