package com.example.ervi.lab1_compresion;

import java.util.ArrayList;
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
            for(int j=i+1; j<nodos.size();i++){
                if(nodos.get(j).getValor()<nodos.get(i).getValor()){
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
            nodos.remove(0);
            nodos.remove(1);
        }
    }

}
