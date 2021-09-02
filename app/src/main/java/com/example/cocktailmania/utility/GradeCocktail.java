package com.example.cocktailmania.utility;

public class GradeCocktail {
    int id;
    String nome;

    public GradeCocktail(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public GradeCocktail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
