package com.example.cocktailmania.cocktail;

public class StepPrep {
    int cktId;
    int stepNum;
    String ing;
    String azione;
    String strumento;
    int azioneImg;

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
        this.ing = ing;
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
        this.ing = this.ing+" /n "+ing;
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
