package com.example.raulm.tetris;

import java.util.List;

/**
 * Created by RaulM on 15/03/2018.
 */

public interface GuardarPuntuacions {
    void guardar(String nom, int puntuacio, long data);
    List<String> getPuntuacio(int quants);
}
