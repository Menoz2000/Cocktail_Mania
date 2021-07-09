package com.example.cocktailmania.ingredient;

public class IngredientElem {
    private int id;
    private String img;
    private String nome;
    private String sottotitolo;

    public IngredientElem(int id, String img, String nome, String sottotitolo) {
        this.id = id;
        this.img = img;
        this.nome = nome;
        this.sottotitolo = sottotitolo;
    }

    public IngredientElem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSottotitolo() {
        return sottotitolo;
    }

    public void setSottotitolo(String sottotitolo) {
        this.sottotitolo = sottotitolo;
    }

    @Override
    public String toString() {
        return "IngredientElem{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", nome='" + nome + '\'' +
                ", sottotitolo='" + sottotitolo + '\'' +
                '}';
    }
}
