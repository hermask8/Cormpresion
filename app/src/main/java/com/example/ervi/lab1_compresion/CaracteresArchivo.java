package com.example.ervi.lab1_compresion;


import java.util.ArrayList;
import java.util.List;

public class CaracteresArchivo {

    private static List<Caracter> caracteres = new ArrayList<>();
    private static Arbol arbol = new Arbol();

    public void SepararLinea(String texto)
    {
        for (int i=0;i<texto.length();i++)
        {
            char letra = texto.charAt(i);
            Caracter miCaracter = new Caracter();
            miCaracter.setValorCaracter(letra);
            miCaracter.setConteo(1);
            boolean contiene = true;
            for (Caracter dato:caracteres)
            {
                if (dato.getValorCaracter().equals(miCaracter.getValorCaracter())==true)
                {
                    int conteo = dato.getConteo() + 1 ;
                    dato.setConteo(conteo);
                    contiene = false;
                }

            }
            if (contiene==true)
            {
                caracteres.add(miCaracter);
                contiene=true;
            }
        }

        arbol.AgregarListaNodo(caracteres);
        arbol.EstructurarArbol();
    }

}
