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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class VentanaDescomprimirLzw extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;
    public LZW miDescompresion= new LZW();
    String pathArchivo;
    String tabla;
    String valor;
    Button agregarArchivo;
    Button comprimir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_descomprimir_lzw);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }

        agregarArchivo =(Button) findViewById(R.id.btnBuscarArchivolz);
        comprimir = (Button) findViewById(R.id.btnDescomprimirLZW);
        agregarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                performFileSearch();
            }
        });
        comprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                escribirArchivo();

            }
        });
    }

    private void performFileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
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

        String nombre =pathArchivo.substring(pathArchivo.indexOf("."));
        String nuevo = readText(pathArchivo);
        String[] llaveValor = nuevo.split("////");
        tabla = llaveValor[0];
        valor = llaveValor[1];
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"PurebaDesco.txt");
        try {
            FileOutputStream fos2 = new FileOutputStream(file);
            String escribir = miDescompresion.Descomprimir(tabla,valor);
            fos2.write(escribir.getBytes());
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
        byte[] values = new byte[(int)file.length()];
        StringBuilder text = new StringBuilder();
        try {

            FileInputStream fileStream = new FileInputStream(file);

            fileStream.read(values);
            fileStream.close();
            String content = new String(values,"UTF-8");
            /*
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder miColeccion2 = new StringBuilder();
            String line;
            int contador = 1;

            while ((line = br.readLine()) != null) {

                miColeccion2.append(line);

                if (contador==1)
                {
                    for (int i = 0; i<line.length();i++)
                    {
                        if ("/".equals(String.valueOf(line.charAt(i))))
                        {
                            if (String.valueOf(line.charAt(i+1)).equals("n"))
                            {
                                i++;
                                miColeccion2.append("\n");
                            }
                            else
                            {
                                miColeccion2.append(line.charAt(i));
                            }

                        }
                        else
                        {
                            miColeccion2.append(line.charAt(i));
                        }
                    }
                    tabla = miColeccion2.toString();
                }
                else
                {
                    valor = line;
                }

                contador++;
            }

            br.close();
            */
            return  content;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return values.toString();
    }
}
