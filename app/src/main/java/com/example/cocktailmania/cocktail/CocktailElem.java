package com.example.cocktailmania.cocktail;

import android.os.Parcel;
import android.os.Parcelable;


public class CocktailElem implements Parcelable {
    private int id;
    private String nome;
    private String fk_gradoAlcolico;
    private String fk_origine;
    private String fk_tipo;
    private boolean preferito;
    private boolean iconico;
    private boolean my_cocktail;
    private int img;

    public CocktailElem(int id, int img, String nome, String gradoAlcolico) {
        this.id = id;
        this.img = img;
        this.nome = nome;
        this.fk_gradoAlcolico = gradoAlcolico;
    }

    public CocktailElem(int id, String nome, String fk_gradoAlcolico, String fk_origine, String fk_tipo, boolean preferito, boolean iconico, boolean my_cocktail, int img) {
        this.id = id;
        this.nome = nome;
        this.fk_gradoAlcolico = fk_gradoAlcolico;
        this.fk_origine = fk_origine;
        this.fk_tipo = fk_tipo;
        this.preferito = preferito;
        this.iconico = iconico;
        this.my_cocktail = my_cocktail;
        this.img = img;
    }

    public CocktailElem() {
    }

    protected CocktailElem(Parcel in) {
        id = in.readInt();
        img = in.readInt();
        nome = in.readString();
        fk_gradoAlcolico = in.readString();
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

    public String getFk_gradoAlcolico() {
        return fk_gradoAlcolico;
    }

    public void setFk_gradoAlcolico(String fk_gradoAlcolico) {
        this.fk_gradoAlcolico = fk_gradoAlcolico;
    }

    public String getFk_origine() {
        return fk_origine;
    }

    public void setFk_origine(String fk_origine) {
        this.fk_origine = fk_origine;
    }

    public String getFk_tipo() {
        return fk_tipo;
    }

    public void setFk_tipo(String fk_tipo) {
        this.fk_tipo = fk_tipo;
    }

    public boolean isPreferito() {
        return preferito;
    }

    public void setPreferito(boolean preferito) {
        this.preferito = preferito;
    }

    public boolean isIconico() {
        return iconico;
    }

    public void setIconico(boolean iconico) {
        this.iconico = iconico;
    }

    public boolean isMy_cocktail() {
        return my_cocktail;
    }

    public void setMy_cocktail(boolean my_cocktail) {
        this.my_cocktail = my_cocktail;
    }

    /*@Override
    public String toString() {
        return "CocktailElem{" +
                "id=" + id +
                ", img=" + img +
                ", nome='" + nome + '\'' +
                ", gradoAlcolico=" + fk_gradoAlcolico +
                '}';
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(img);
        dest.writeString(nome);
        dest.writeString(fk_gradoAlcolico);
    }
}
