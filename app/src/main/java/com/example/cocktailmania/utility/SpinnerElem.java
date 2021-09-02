package com.example.cocktailmania.utility;

public class SpinnerElem {
    int id;
    int img;
    String nome;

    public SpinnerElem() {
    }

    public SpinnerElem(int id, String nome, int img) {
        this.id = id;
        this.img = img;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "SpinnerElem{" +
                "id=" + id +
                ", img=" + img +
                ", nome='" + nome + '\'' +
                '}';
    }
}
