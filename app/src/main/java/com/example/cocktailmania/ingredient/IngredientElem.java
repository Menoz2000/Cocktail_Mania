package com.example.cocktailmania.ingredient;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientElem implements Parcelable {
    private int id;
    private int img;
    private String nome;
    private String sottotitolo;

    public IngredientElem(int id, int img, String nome, String sottotitolo) {
        this.id = id;
        this.img = img;
        this.nome = nome;
        this.sottotitolo = sottotitolo;
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

    @Override
    public String toString() {
        return "IngredientElem{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", nome='" + nome + '\'' +
                ", sottotitolo='" + sottotitolo + '\'' +
                '}';
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
