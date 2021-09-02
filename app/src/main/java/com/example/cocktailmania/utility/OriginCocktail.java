package com.example.cocktailmania.utility;

public class OriginCocktail {
    int id;
    String nazione;
    int img;

    public OriginCocktail(int id, String nazione, int img) {
        this.id = id;
        this.nazione = nazione;
        this.img = img;
    }

    public OriginCocktail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
