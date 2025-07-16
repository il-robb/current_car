package com.betacom.car.models;

public class Bici {

    private int idBici;
    private int idVeicolo;
    private int numeroMarce;
    private int idSospenzione;
    private boolean pieghevole;

 
    public Bici(int idBici, int idVeicolo, int numeroMarce, int idSospenzione, boolean pieghevole) {
        super();
        this.idBici = idBici;
        this.idVeicolo = idVeicolo;
        this.numeroMarce = numeroMarce;
        this.idSospenzione = idSospenzione;
        this.pieghevole = pieghevole;
    }

 
    public int getIdBici() {
        return idBici;
    }
    public void setIdBici(int idBici) {
        this.idBici = idBici;
    }

    public int getIdVeicolo() {
        return idVeicolo;
    }
    public void setIdVeicolo(int idVeicolo) {
        this.idVeicolo = idVeicolo;
    }

    public int getNumeroMarce() {
        return numeroMarce;
    }
    public void setNumeroMarce(int numeroMarce) {
        this.numeroMarce = numeroMarce;
    }

    public int getIdSospenzione() {
        return idSospenzione;
    }
    public void setIdSospenzione(int idSospenzione) {
        this.idSospenzione = idSospenzione;
    }

    public boolean isPieghevole() {
        return pieghevole;
    }
    public void setPieghevole(boolean pieghevole) {
        this.pieghevole = pieghevole;
    }

    @Override
    public String toString() {
        return "Bici [idBici=" + idBici + ", idVeicolo=" + idVeicolo + ", numeroMarce=" + numeroMarce
                + ", idSospenzione=" + idSospenzione + ", pieghevole=" + pieghevole + "]";
    }

}
