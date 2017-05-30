package com.example.roger.exercicio4;


import java.io.Serializable;

public class Diretor implements Serializable{

    private String nome;
    private int id;

    public Diretor(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
