package com.betacom.car.models;

public class Macchina{

    private int idMacchina;
    private int idVeicolo;
    private int numeroPorte;
    private String Targa;
    private int cilindrata;

    public Macchina() {
        super();
    }

    public Macchina(int idMacchina, int idVeicolo, int numeroPorte, String targa, int cilindrata) {
        super();
        this.idMacchina = idMacchina;
        this.idVeicolo = idVeicolo;
        this.numeroPorte = numeroPorte;
        Targa = targa;
        this.cilindrata = cilindrata;
    }

    public int getIdMacchina() {
        return idMacchina;
    }

    public void setIdMacchina(int idMacchina) {
        this.idMacchina = idMacchina;
    }

    public int getIdVeicolo() {
        return idVeicolo;
    }

    public void setIdVeicolo(int idVeicolo) {
        this.idVeicolo = idVeicolo;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public String getTarga() {
        return Targa;
    }

    public void setTarga(String targa) {
        Targa = targa;
    }

    public int getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(int cilindrata) {
        this.cilindrata = cilindrata;
    }

    @Override
    public String toString() {
        return "idMacchina=" + idMacchina + ", idVeicolo=" + idVeicolo + ", numero porte=" + numeroPorte
                + ", Targa=" + Targa + ", cilindrata=" + cilindrata ;
    }



}