package com.example.cocktailmania.utility;

public class Strumento {
    int id;
    String nome;
    String capacita;
    String descrizione;
    int img;

    public Strumento() {
    }

    public Strumento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Strumento(int id, String nome, int img) {
        this.id = id;
        this.nome = nome;
        this.img = img;
    }

    public Strumento(int id, String nome, String capacita, String descrizione, int img) {
        this.id = id;
        this.nome = nome;
        this.capacita = capacita;
        this.descrizione = descrizione;
        this.img = img;
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

    public String getCapacita() {
        return capacita;
    }

    public void setCapacita(String capacita) {
        this.capacita = capacita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Strumento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacita='" + capacita + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", img=" + img +
                '}';
    }
}
