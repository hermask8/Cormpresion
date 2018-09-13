package com.example.ervi.lab1_compresion;

public class Nodo {

    String llave;
    int valor;
    Nodo father;
    Nodo right;
    Nodo left;

    public Nodo(String llave, int valor){
        this.llave = llave;
        this.valor = valor;
    }

    public Nodo getFather() {
        return father;
    }

    public void setFather(Nodo father) {
        this.father = father;
    }

    public Nodo getLeft() {
        return left;
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public Nodo getRight() {
        return right;
    }

    public void setRight(Nodo right) {
        this.right = right;
    }
}


