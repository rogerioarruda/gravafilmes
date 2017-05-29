package com.example.roger.exercicio4;

import java.io.Serializable;

public class Filme implements Serializable {


    private String filme;
    private String codigo;
    private String ano;
    private String diretor;
    private String dataLancamento;
    private Double rating;
    //private byte[] foto;

    public Filme(){

    }

    public Filme(String titulo) {
        this.filme = titulo;
    }

    public Filme(String titulo, String diretor, byte[] foto) {
        this.filme = titulo;
        this.diretor = diretor;
        //this.foto = foto;
    }

    public Filme(String titulo, String codigo, String ano, String diretor, String dataLancamento, byte[] foto) {
        this.filme = titulo;
        this.codigo = codigo;
        this.ano = ano;
        this.diretor = diretor;
        this.dataLancamento = dataLancamento;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
