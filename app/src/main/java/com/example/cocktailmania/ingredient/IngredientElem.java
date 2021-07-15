package com.example.cocktailmania.ingredient;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientElem implements Parcelable {
    private int id;
    private String nome;
    private int img;
    private String sottotitolo;
    private String descrizione;
    private String origine;
    private double grado_alcolico;


    public IngredientElem(int id, int img, String nome, String sottotitolo) {
        this.id = id;
        this.img = img;
        this.nome = nome;
        this.sottotitolo = sottotitolo;
    }

    public IngredientElem(int id, String nome, int img, String sottotitolo, String descrizione, String origine, double grado_alcolico) {
        this.id = id;
        this.nome = nome;
        this.img = img;
        this.sottotitolo = sottotitolo;
        this.descrizione = descrizione;
        this.origine = origine;
        this.grado_alcolico = grado_alcolico;
    }

    public IngredientElem() {
    }

    protected IngredientElem(Parcel in) {
        id = in.readInt();
        img = in.readInt();
        nome = in.readString();
        sottotitolo = in.readString();
    }

    public static final Creator<IngredientElem> CREATOR = new Creator<IngredientElem>() {
        @Override
        public IngredientElem createFromParcel(Parcel in) {
            return new IngredientElem(in);
        }

        @Override
        public IngredientElem[] newArray(int size) {
            return new IngredientElem[size];
        }
    };

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

    public String getSottotitolo() {
        return sottotitolo;
    }

    public void setSottotitolo(String sottotitolo) {
        this.sottotitolo = sottotitolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public double getGrado_alcolico() {
        return grado_alcolico;
    }

    public void setGrado_alcolico(double grado_alcolico) {
        this.grado_alcolico = grado_alcolico;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(img);
        dest.writeString(nome);
        dest.writeString(sottotitolo);
    }

}
