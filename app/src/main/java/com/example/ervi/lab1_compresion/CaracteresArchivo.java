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
        StringBuilder decimales = new StringBuilder();
        StringBuilder ascii = new StringBuilder();
        arbol.AgregarListaNodo(caracteres);
        arbol.EstructurarArbol();
        String copiar = arbol.TextoEnBits(texto);
        String textoSeparar = binarioByte(copiar);
        String[] separar = textoSeparar.split("-");
        for (int j=0;j<separar.length;j++)
        {
            if(separar[j].length()==8)
            {
                decimales.append(Decimal(separar[j]));
                decimales.append(",");
            }
            else
            {
                int faltantes = 8-separar[j].length();
                for (int m =0;m<faltantes;m++)
                {
                    separar[j]=separar[j]+"0";

                }
                decimales.append(Decimal(separar[j]));
                decimales.append(",");
            }
        }

        String[] decimal = decimales.toString().split(",");
        for (int l =0; l<decimal.length;l++)
        {
            ascii.append(ConvertirAscii(Integer.parseInt(decimal[l])));
        }

    }
    public char ConvertirAscii(int leido){
        return (char)leido;
    }

    public String binarioByte(String bits)
    {
        StringBuilder Byte = new StringBuilder();
        int contador =1;
        for (int i=0;i<bits.length();i++)
        {

            char letra = bits.charAt(i);
            String letra2 = Character.toString(letra);
            if (contador!=8)
            {
                Byte.append(letra2);

                contador++;
            }
            else
            {
                Byte.append(letra2);
                Byte.append("-");
                contador=1;
            }

        }
        return Byte.toString();
    }

    public String retornoByteAscci(String bits)
    {
        StringBuilder textoEnAscci = new StringBuilder();
        for (int i=0;i<bits.length();i++)
        {

            char letra = bits.charAt(i);

        }
        return textoEnAscci.toString();
    }

    public Long Decimal(String binario)
    {
        long numero, aux, digito, decimal;
        int exponente;
        boolean esBinario;

        //Leer un número por teclado y comprobar que es binario
        do {
            numero = Long.parseLong(binario);
            //comprobamos que sea un número binario es decir
            //que este formado solo por ceros y unos
            esBinario = true;
            aux = numero;
            while (aux != 0) {
                digito = aux % 10; //última cifra del números
                if (digito != 0 && digito != 1) { //si no es 0 ó 1
                    esBinario = false; //no es un número binario
                }
                aux = aux / 10; //quitamos la última cifra para repetir el proceso
            }
        } while (!esBinario); //se vuelve a pedir si no es binario

        //proceso para pasar de binario a decimal
        exponente = 0;
        decimal = 0; //será el equivalente en base decimal
        while (numero != 0) {
            //se toma la última cifra
            digito = numero % 10;
            //se multiplica por la potencia de 2 correspondiente y se suma al número
            decimal = decimal + digito * (int) Math.pow(2, exponente);
            //se aumenta el exponente
            exponente++;
            //se quita la última cifra para repetir el proceso
            numero = numero / 10;
        }
        return decimal;
    }

}
