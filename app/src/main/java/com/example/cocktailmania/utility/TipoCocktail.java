package com.example.cocktailmania.utility;

public class TipoCocktail {
    int id;
    String nome;
    int img;

    public TipoCocktail(int id, String nome, int img) {
        this.id = id;
        this.nome = nome;
        this.img = img;
    }

    public TipoCocktail() {
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
