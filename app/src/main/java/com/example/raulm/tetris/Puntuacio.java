package com.example.raulm.tetris;

import android.os.StrictMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RaulM on 15/03/2018.
 */

public class Puntuacio {
    int punts;
    String nom;
    long data;

    public Puntuacio(){

    }

    public Puntuacio(int punts, String nom, long data){
        this.punts = punts;
        this.nom = nom;
        this.data = data;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    private String getDataFormat(){
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(this.data);
        return sf.format(d);
    }

    @Override
    public String toString() {
        return punts + " " + nom + " " + getDataFormat();
    }
}
