package com.example.cocktailmania.utility;

import java.util.ArrayList;

public class StepPrep {
    int cktId;
    int stepNum;
    int idIngrediente;
    String ing;
    int idAzione;
    String azione;
    int idStrumento;
    String strumento;
    int azioneImg;
    ArrayList<Integer> ingStep;

    public StepPrep() {
    }

    public StepPrep(int cktId, int stepNum, String ing, String azione, String strumento, int azioneImg) {
        this.cktId = cktId;
        this.stepNum = stepNum;
        this.ing = ing;
        this.azione = azione;
        this.strumento = strumento;
        this.azioneImg = azioneImg;
    }

    public StepPrep(int stepNum, String ing, int idAzione, String azione, int idStrumento, String strumento, int azioneImg, ArrayList<Integer> ingStep) {
        this.stepNum = stepNum;
        this.ing = ing;
        this.idAzione = idAzione;
        this.azione = azione;
        this.idStrumento = idStrumento;
        this.strumento = strumento;
        this.azioneImg = azioneImg;
        this.ingStep = ingStep;
    }

    public ArrayList<Integer> getIngStep() {
        return ingStep;
    }

    public void setIngStep(ArrayList<Integer> ingStep) {
        this.ingStep = ingStep;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getIdAzione() {
        return idAzione;
    }

    public void setIdAzione(int idAzione) {
        this.idAzione = idAzione;
    }

    public int getIdStrumento() {
        return idStrumento;
    }

    public void setIdStrumento(int idStrumento) {
        this.idStrumento = idStrumento;
    }

    public int getCktId() {
        return cktId;
    }

    public void setCktId(int cktId) {
        this.cktId = cktId;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        if (ing == null) {
            this.ing = ing;
        } else {
            this.ing = "-" + ing;
        }

    }

    public String getAzione() {
        return azione;
    }

    public void setAzione(String azione) {
        this.azione = azione;
    }

    public String getStrumento() {
        return strumento;
    }

    public void setStrumento(String strumento) {
        this.strumento = strumento;
    }

    public int getAzioneImg() {
        return azioneImg;
    }

    public void setAzioneImg(int azioneImg) {
        this.azioneImg = azioneImg;
    }

    public void addIng(String ing) {
        this.ing = this.ing + "\n" + "-" + ing;
    }

    @Override
    public String toString() {
        return "StepPrep{" +
                "cktId=" + cktId +
                ", stepNum=" + stepNum +
                ", ing='" + ing + '\'' +
                ", azione='" + azione + '\'' +
                ", strumento='" + strumento + '\'' +
                ", azioneImg=" + azioneImg +
                '}';
    }
}