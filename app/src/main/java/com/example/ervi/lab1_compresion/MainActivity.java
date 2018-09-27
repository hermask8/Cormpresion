package com.example.ervi.lab1_compresion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ventanaComprimir(View view)
    {
        Intent ventanaCompri = new Intent(this,ComprimirVista.class);
        startActivity(ventanaCompri);
    }

    public void ventanaDEscomprimir(View view)
    {
        Intent ventanaCompri = new Intent(this,VentanaDescomprimir.class);
        startActivity(ventanaCompri);
    }
}
