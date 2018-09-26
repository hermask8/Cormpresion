package com.example.ervi.lab1_compresion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LZWVentana extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;
    public LZW miCompresion = new LZW();
    public String pathArchivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lzwventana);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

    }

    private void performFileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);
                Toast.makeText(this, "" + path, Toast.LENGTH_SHORT).show();

                pathArchivo = path;

            /*
            ;
             pathArchivo2 = path;
                tvPath.setText(path);
                pathArchivo3 =path.substring(0,path.indexOf("."));
            tvBinario.setText(miArbol.getBinarioVentana());
            tvAscii.setText(miArbol.getPasarAscii());
            */
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void escribirArchivo() {


        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Pureba.txt");
        try {
            FileOutputStream fos2 = new FileOutputStream(file);

            String escribir = miCompresion.ComprimirTabla(readText(pathArchivo));
            fos2.write(escribir.getBytes());
            fos2.write("\n".getBytes());
            fos2.write((miCompresion.ComprimirTexto(readText(pathArchivo))).getBytes());
            fos2.close();
            Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"Archivo no encontrado",Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this,"ERROR Guardando",Toast.LENGTH_SHORT).show();
        }



    }


    private String readText(String input) {

        File file = new File(Environment.getExternalStorageDirectory(), input);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int contador = 1;
            while ((line = br.readLine()) != null) {

               text.append(line);
                text.append("\n");
            }
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return text.toString();
    }
}
/*
    public void escribirArchivo2()
    {

        String fileName = "Comprimido.txt";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);

            for (Caracter caracter: miArbol.getCaracteres())
            {
                if (caracter.getValorCaracter().equals('\n')==true)
                {
                    String guardar = "!n"+","+ String.valueOf(caracter.getConteo());
                    fos.write(guardar.getBytes());
                    fos.write("/".getBytes());
                }
                else
                {
                    String guardar = caracter.getValorCaracter() +","+ String.valueOf(caracter.getConteo());
                    fos.write(guardar.getBytes());
                    fos.write("/".getBytes());
                }
            }
            fos.write("\n".getBytes());
            fos.write(miArbol.getPasarAscii().getBytes());
            fos.close();
            Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"Archivo no encontrado",Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this,"ERROR Guardando",Toast.LENGTH_SHORT).show();
        }



    }

*/


