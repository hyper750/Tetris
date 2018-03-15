package com.example.raulm.tetris;

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

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yyyy");
        Date d = new Date(this.data);
        return nom + " " + punts + " " + sf.format(d);
    }

    public static List<Puntuacio> exemple(){
        List<Puntuacio> puntuacios = new ArrayList<>();
        puntuacios.add(new Puntuacio(1000, "Raulsito", System.currentTimeMillis()));
        puntuacios.add(new Puntuacio(500, "Raulsito", System.currentTimeMillis()));
        return puntuacios;
    }
}
