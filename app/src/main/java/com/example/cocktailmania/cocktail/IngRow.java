package com.example.cocktailmania.cocktail;

public class IngRow {
    double quantita;
    String unita_misura;
    String ingName;

    public IngRow() {
    }

    public IngRow(double quantita, String unita_misura, String ingName) {
        this.quantita = quantita;
        this.unita_misura = unita_misura;
        this.ingName = ingName;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
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
                "quantita=" + quantita +
                ", unita_misura='" + unita_misura + '\'' +
                ", ingName='" + ingName + '\'' +
                '}';
    }
}
