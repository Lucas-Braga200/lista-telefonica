package br.unigran.lista_telefonica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Contato {
    private Integer id;
    private String nome;
    private String telefone;
    private Date dataNascimento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            return nome + " " + telefone + " " + formatter.format(dataNascimento);
        } catch (Exception e) {
            System.out.println(e);
            return nome + " " + telefone + " " + dataNascimento;
        }
    }
}
