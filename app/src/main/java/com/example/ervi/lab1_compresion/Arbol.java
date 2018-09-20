package com.example.ervi.lab1_compresion;

import android.renderscript.Sampler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Arbol {
    List<Nodo> nodos = new ArrayList<>();
    Hashtable<String,String> valorCracter = new Hashtable<>();




    public void AgregarListaNodo(List<Caracter> listaCaracter)
    {

        for (Caracter caracter:listaCaracter)
        {
            Nodo miNodo = new Nodo();
            miNodo.setLlave(caracter.getValorCaracter().toString());
            miNodo.setValor(caracter.getConteo());
            nodos.add(miNodo);
        }

    }


    //DANILO: ordenamiento de burbuja para la lista de nodos

    private void Ordenar(){
        for (int i=0; i<nodos.size()-1; i++){
            for(int j=i+1; j<nodos.size();j++){
                if(nodos.get(j).getValor() <  nodos.get(i).getValor()){
                    Nodo aux = new Nodo();
                    aux = nodos.get(i);
                    nodos.set(i,nodos.get(j));
                    nodos.set(j, aux);
                }
            }
        }
    }

    //DANILO: método que toma los nodos con valor menor en frecuencia y les asigna un nuevo nodo padre

    public void EstructurarArbol(){
        while(nodos.size()>1){
            Ordenar();
            Nodo nuevo = new Nodo("", nodos.get(0).valor+nodos.get(1).valor);
            nuevo.setLeft(nodos.get(0));
            nuevo.setRight(nodos.get(1));
            nodos.get(0).setFather(nuevo);
            nodos.get(1).setFather(nuevo);
            nodos.add(nuevo);
            nodos.remove(0);
            nodos.remove(0);
        }
        LlenarValorCaracter(nodos.get(0), "");

    }

    //DANILO: contruye la tabla con el código prefijo de cada caracter

    private void LlenarValorCaracter(Nodo actual, String cadena){
        if (actual.getLeft() == null && actual.getRight() == null){
            valorCracter.put(actual.getLlave(), cadena);
        }
        else{
            LlenarValorCaracter(actual.getLeft(), cadena + "0");
            LlenarValorCaracter(actual.getRight(),cadena + "1");
        }
    }




    //DANILO: escribe el texto del archivo en un arreglo de 1s y 0s para posteriormente convertirlos a decimal y luego a su equivalente a caracter
    //Recibe el texto total del archivo

    public String TextoEnBits(String original) {
        StringBuilder textoNuevo = new StringBuilder();

        //LEER EL TEXTO ORIGINAL LETRA POR LETRA, LLAMAR A LA LLAVE CORRESPONDIENTE DICCIONARIO ValorCracter
        //Y ESCRIBIR EL VALOR COORESPONDIENTE


        
        for (int i=0;i<original.length();i++)
        {
            char letra = original.charAt(i);

            for (Hashtable.Entry<String,String> entry:valorCracter.entrySet())
            {
                String letra2 = Character.toString(letra);
                if (letra2.equals(entry.getKey())==true)
                {
                    textoNuevo.append(entry.getValue());
                }
            }
        }

      return textoNuevo.toString();
    }
}
