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

public class LZWeleccion extends AppCompatActivity {

    Button agregarArchivo;
    Button comprimir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lzweleccion);


        agregarArchivo =(Button) findViewById(R.id.btnAgregarArchivoLz);
        comprimir = (Button) findViewById(R.id.btnComprimirLz);
    }

    public void CambioDescomprimir(View view)
    {
        Intent ventanaDescomprimir = new Intent(this,VentanaDescomprimirLzw.class);
        startActivity(ventanaDescomprimir);
    }

    public void CambioComprimir(View view)
    {
        Intent ventanaComprimir = new Intent(this,LZWVentana.class);
        startActivity(ventanaComprimir);
    }
}
