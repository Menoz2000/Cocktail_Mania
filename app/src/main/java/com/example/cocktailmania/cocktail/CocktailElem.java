package com.example.cocktailmania.cocktail;

import android.os.Parcel;
import android.os.Parcelable;


public class CocktailElem implements Parcelable {
    private int id;
    private int img;
    private String nome;
    private String gradoAlcolico;

    public CocktailElem(int id, int img, String nome, String gradoAlcolico) {
        this.id = id;
        this.img = img;
        this.nome = nome;
        this.gradoAlcolico = gradoAlcolico;
    }

    public CocktailElem() {
    }

    protected CocktailElem(Parcel in) {
        id = in.readInt();
        img = in.readInt();
        nome = in.readString();
        gradoAlcolico = in.readString();
    }

    public static final Creator<CocktailElem> CREATOR = new Creator<CocktailElem>() {
        @Override
        public CocktailElem createFromParcel(Parcel in) {
            return new CocktailElem(in);
        }

        @Override
        public CocktailElem[] newArray(int size) {
            return new CocktailElem[size];
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

    public String getGradoAlcolico() {
        return gradoAlcolico;
    }

    public void setGradoAlcolico(String descrizione) {
        this.gradoAlcolico = descrizione;
    }

    @Override
    public String toString() {
        return "CocktailElem{" +
                "id=" + id +
                ", img=" + img +
                ", nome='" + nome + '\'' +
                ", gradoAlcolico=" + gradoAlcolico +
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
        dest.writeString(gradoAlcolico);
    }
}
