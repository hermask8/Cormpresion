package com.example.ervi.lab1_compresion;


import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CaracteresArchivo {

    public static List<Caracter> caracteres = new ArrayList<>();
    private static Arbol arbol = new Arbol();
    public String pasarAscii;
    public String descomprimir;
    public String binarioVentana;
    public Hashtable<String,String> valorCracter2 = new Hashtable<>();
    public void SepararLinea(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            Caracter miCaracter = new Caracter();
            miCaracter.setValorCaracter(letra);
            miCaracter.setConteo(1);
            boolean contiene = true;
            for (Caracter dato : caracteres) {
                if (dato.getValorCaracter().equals(miCaracter.getValorCaracter())) {
                    int conteo = dato.getConteo() + 1;
                    dato.setConteo(conteo);
                    contiene = false;
                }

            }
            if (contiene) {
                caracteres.add(miCaracter);
            }
        }
        StringBuilder decimales = new StringBuilder();
        StringBuilder ascii = new StringBuilder();
        arbol.AgregarListaNodo(caracteres);
        arbol.EstructurarArbol(true);
        String copiar = arbol.TextoEnBits(texto);
        binarioVentana = copiar;
        String textoSeparar = binarioByte(copiar);
        String[] separar = textoSeparar.split("-");
        for (int j = 0; j < separar.length; j++) {
            if (separar[j].length() == 8) {
                decimales.append(Decimal(separar[j]));
                decimales.append(",");
            } else {
                int faltantes = 8 - separar[j].length();
                for (int m = 0; m < faltantes; m++) {
                    separar[j] = separar[j] + "0";

                }
                decimales.append(Decimal(separar[j]));
                decimales.append(",");
            }
        }

        String[] decimal = decimales.toString().split(",");
        for (int l = 0; l < decimal.length; l++) {
            ascii.append(ConvertirAscii(Integer.parseInt(decimal[l])));

        }

        pasarAscii = ascii.toString();
/*
        StringBuilder asciiDesimal2 = new StringBuilder();
        String[] asciiDecimal = ascii.toString().split(",");
        for (int l = 0; l < asciiDecimal.length; l++) {
            int convertido = (int) asciiDecimal[l].charAt(0);
            asciiDesimal2.append(convertido);
            asciiDesimal2.append(",");

        }

        StringBuilder decimalBinario = new StringBuilder();
        String[] decimalbina = asciiDesimal2.toString().split(",");
        for (int l = 0; l < decimalbina.length; l++) {
            decimalBinario.append(Integer.toBinaryString(Integer.parseInt(decimalbina[l])));

        }
        String binario2 = decimalBinario.toString();
        StringBuilder binarioCAracter = new StringBuilder();
        String letra2 = "";

        for (int i = 0; i < binario2.length(); i++) {
            char letra = binario2.charAt(i);
            boolean bandera = false;
            if (letra2.equals("")) {
                letra2 = Character.toString(letra);
                bandera = true;
            }
            if (!bandera && !letra2.equals("")) {
                letra2 = letra2 + Character.toString(letra);
            }
            for (Hashtable.Entry<String, String> entry : arbol.getValorCracter().entrySet()) {

                if (letra2.equals(entry.getValue())) {
                    binarioCAracter.append(entry.getKey());
                    letra2 = "";
                    bandera = true;
                }

            }


        }
        descomprimir = binarioCAracter.toString();
        */

    }

    public char ConvertirAscii(int leido) {
        return (char) leido;
    }

    public String binarioByte(String bits) {
        StringBuilder Byte = new StringBuilder();
        int contador = 1;
        for (int i = 0; i < bits.length(); i++) {

            char letra = bits.charAt(i);
            String letra2 = Character.toString(letra);
            if (contador != 8) {
                Byte.append(letra2);

                contador++;
            } else {
                Byte.append(letra2);
                Byte.append("-");
                contador = 1;
            }

        }
        return Byte.toString();
    }

    public String retornoByteAscci(String bits) {
        StringBuilder textoEnAscci = new StringBuilder();
        for (int i = 0; i < bits.length(); i++) {

            char letra = bits.charAt(i);

        }
        return textoEnAscci.toString();
    }

    public Long Decimal(String binario) {
        long numero, aux, digito, decimal;
        int exponente;
        boolean esBinario;


        do {
            numero = Long.parseLong(binario);
            esBinario = true;
            aux = numero;
            while (aux != 0) {
                digito = aux % 10;
                if (digito != 0 && digito != 1) {
                    esBinario = false;
                }
                aux = aux / 10;
            }
        } while (!esBinario);


        exponente = 0;
        decimal = 0;
        while (numero != 0) {

            digito = numero % 10;
            decimal = decimal + digito * (int) Math.pow(2, exponente);
            exponente++;
            numero = numero / 10;
        }
        return decimal;
    }

    public  List<Caracter> getCaracteres() {
        return caracteres;
    }

    public String getDescomprimir() {
        return descomprimir;
    }

    public String getPasarAscii() {
        return pasarAscii;
    }

    public String getBinarioVentana() {
        return binarioVentana;
    }

    public Hashtable<String, String> getValorCracter2() {
        valorCracter2 = arbol.getValorCracter();
        return valorCracter2;
    }

    ////////////////////////////////////////////////////
    public String Descomprimir(String texto, List<Caracter> lista){
        arbol.AgregarListaNodo(lista);
        arbol.EstructurarArbol(false);
        return arbol.ConvertirAsciiATexto(texto);
    }
}