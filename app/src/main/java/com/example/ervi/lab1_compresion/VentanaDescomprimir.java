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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class VentanaDescomprimir extends AppCompatActivity {


    CaracteresArchivo miArbol = new CaracteresArchivo();
    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;
    private static String pathArchivo;
    Button agregarArchivo;
    Button Descomprimir;
    TextView tvPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_descomprimir);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }
        agregarArchivo =(Button) findViewById(R.id.btnBuscarArchivo);
        tvPath =(TextView) findViewById(R.id.tvPathArchivo);
        Descomprimir = (Button) findViewById(R.id.btnDescomprimir);
        agregarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                performFileSearch();
            }
        });

    }

    public void ComprimirArchivo(View view)
    {
        try
        {
            miArbol.SepararLinea(readText(pathArchivo));
        }
        catch(Exception ex)
        {
            Toast.makeText(this,"ERROR " + "Agregue un archivo " + ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private String readText(String input)
    {
        File file = new File(Environment.getExternalStorageDirectory(),input);
        StringBuilder text = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line=br.readLine())!=null)
            {
                text.append(line);
                text.append("\n");
            }
            br.close();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return text.toString();
    }
    private void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode== READ_REQUEST_CODE && resultCode == RESULT_OK)
        {
            if (data!=null)
            {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);
                Toast.makeText(this,"" + path,Toast.LENGTH_SHORT).show();

                pathArchivo = path;
                tvPath.setText(path);
                /*
                ;
                tvBinario.setText(miArbol.getBinarioVentana());
                tvAscii.setText(miArbol.getPasarAscii());
                */
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==PERMISSION_REQUEST_STORAGE)
        {
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"permission granted!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Permission not granted",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}
