package com.example.roger.exercicio4;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Filme implements Serializable {


    private String filme;
    private String codigo;
    private String ano;
    private String diretor;
    private String dataLancamento;
    private Float rating;
    private Bitmap bitmap;
    private byte[] bytes;

    public Filme(){

    }

    public Filme(String titulo) {
        this.filme = titulo;
    }

    public Filme(String titulo, String diretor, byte[] bitmap) {
        this.filme = titulo;
        this.diretor = diretor;
        this.bytes = bitmap;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
