package com.example.cocktailmania.utility;

public class IngRow {
    int idIng;
    String ingName;
    String quantita;
    String unita_misura;

    public IngRow() {
    }

    public IngRow(int idIng, String quantita, String unita_misura, String ingName) {
        this.idIng = idIng;
        this.quantita = quantita;
        this.unita_misura = unita_misura;
        this.ingName = ingName;
    }

    public int getIdIng() {
        return idIng;
    }

    public void setIdIng(int idIng) {
        this.idIng = idIng;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String getUnita_misura() {
        return unita_misura;
    }

    public void setUnita_misura(String unita_misura) {
        this.unita_misura = unita_misura;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    @Override
    public String toString() {
        return "IngRow{" +
                "idIng=" + idIng +
                ", ingName='" + ingName + '\'' +
                ", quantita='" + quantita + '\'' +
                ", unita_misura='" + unita_misura + '\'' +
                '}';
    }
}
