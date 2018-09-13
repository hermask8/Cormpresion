package com.example.ervi.lab1_compresion;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Hashtable;

public class CaracteresArchivo {

    private Hashtable<Character,Integer> caracteres = new Hashtable<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void SepararLinea(String texto)
    {
        for(int i=0 ;i <texto.length();i++)
        {
            char nuevo = texto.charAt(i);
            if (caracteres.containsValue(nuevo)==true)
            {

                caracteres.replace(nuevo,caracteres.get(nuevo).intValue(),(caracteres.get(nuevo).intValue()+1));
            }
            else
            {
                caracteres.put(nuevo,1);
            }
        }
    }
}
