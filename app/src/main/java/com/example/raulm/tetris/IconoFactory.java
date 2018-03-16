package com.example.raulm.tetris;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 16/03/2018.
 */

public class IconoFactory {
    private List<Integer> iconos;
    public IconoFactory(){
        iconos = new ArrayList<Integer>();
        iconos.add(R.drawable.i);
        iconos.add(R.drawable.j);
        iconos.add(R.drawable.l);
        iconos.add(R.drawable.o);
        iconos.add(R.drawable.s);
        iconos.add(R.drawable.t);
        iconos.add(R.drawable.z);
    }

    public Integer getIcono(){
        int num = (int)(Math.random() * iconos.size());
        return iconos.get(num);
    }
}
